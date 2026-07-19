package de.lhind.internship.mini.project.service;

import de.lhind.internship.mini.project.dto.ReservationDTO;
import de.lhind.internship.mini.project.entity.*;
import de.lhind.internship.mini.project.exception.RoomNotAvailableException;
import de.lhind.internship.mini.project.repository.GuestRepository;
import de.lhind.internship.mini.project.repository.ReservationRepository;
import de.lhind.internship.mini.project.repository.RoomRepository;
import de.lhind.internship.mini.project.repository.RoomReservationCount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;

    public void createReservation(ReservationDTO reservationDTO) {

        Guest guest = guestRepository.findById(reservationDTO.getGuestId())
                .orElseThrow(() -> new RuntimeException("Guest not found"));

        Room room = roomRepository.findById(reservationDTO.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (!reservationDTO.getCheckOutDate().isAfter(reservationDTO.getCheckInDate())) {
            throw new RuntimeException("Check-out date must be after check-in date");
        }

        if(reservationDTO.getNumberOfGuests() > room.getCapacity()){
            throw new RuntimeException("Number of guests is greater than room capacity");
        }
        if (room.getStatus() == RoomStatus.MAINTENANCE) {
            throw new RuntimeException("Room is maintenance");
        }

        if(reservationRepository.countOverlappingReservations(reservationDTO.getRoomId(), reservationDTO.getCheckInDate(), reservationDTO.getCheckOutDate()) > 0){
                throw new RuntimeException("Reservation already exists");
        }
        if (reservationRepository.countOverlappingReservations(
                reservationDTO.getRoomId(), reservationDTO.getCheckInDate(), reservationDTO.getCheckOutDate()) > 0) {
            throw new RoomNotAvailableException();
        }


        long nights = ChronoUnit.DAYS.between(
                reservationDTO.getCheckInDate(), reservationDTO.getCheckOutDate());

        Reservation reservation = new Reservation();
        reservation.setGuest(guest);
        reservation.setRoom(room);
        reservation.setCheckInDate(reservationDTO.getCheckInDate());
        reservation.setCheckOutDate(reservationDTO.getCheckOutDate());
        reservation.setNumberOfGuests(reservationDTO.getNumberOfGuests());
        reservation.setTotalPrice(nights * room.getPricePerNight());
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setCreatedAt(LocalDate.now());

        reservationRepository.save(reservation);
    }



    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDTO> reservationDTOs = new ArrayList<>();

        for (Reservation reservation : reservations) {
            ReservationDTO reservationDTO = ReservationDTO.builder()
                    .id(reservation.getId())
                    .checkInDate(reservation.getCheckInDate())
                    .checkOutDate(reservation.getCheckOutDate())
                    .status(reservation.getStatus())
                    .createdAt(reservation.getCreatedAt())
                    .numberOfGuests(reservation.getNumberOfGuests())
                    .totalPrice(reservation.getTotalPrice())
                    .guestId(reservation.getGuest().getId())
                    .roomId(reservation.getRoom().getId())
                    .build();
            reservationDTOs.add(reservationDTO);

        }

        return reservationDTOs;
    }

    public ReservationDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation == null) {
            return null;
        }
        ReservationDTO reservationDTO = ReservationDTO.builder()
                .id(reservation.getId())
                .checkInDate(reservation.getCheckInDate())
                .checkOutDate(reservation.getCheckOutDate())
                .status(reservation.getStatus())
                .createdAt(reservation.getCreatedAt())
                .numberOfGuests(reservation.getNumberOfGuests())
                .totalPrice(reservation.getTotalPrice())
                .guestId(reservation.getGuest().getId())
                .roomId(reservation.getRoom().getId())
                .build();

        return reservationDTO;
    }

    public boolean updateReservationByStatus(Long id, ReservationStatus status) {
        if (status == null) {
            throw new RuntimeException("Status must not be null");
        }
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation == null) {
            return false;
        }
        reservation.setStatus(status);
        reservationRepository.save(reservation);
        return true;

    }

    public boolean deleteReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation == null) {
            return false;
        }
        if (reservation.getStatus() == ReservationStatus.CANCELLED) {
            return false;
        }
        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
        return true;
    }

    public List<ReservationDTO> getReservationsByGuest(Long guestId) {

        List<Reservation>  reservations = (List<Reservation>) reservationRepository.findByGuestId(guestId);
        if (reservations == null) {
            return null;
        }
        List<ReservationDTO> reservationDTOs = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ReservationDTO reservationDTO = ReservationDTO.builder()
                    .id(reservation.getId())
                    .checkInDate(reservation.getCheckInDate())
                    .checkOutDate(reservation.getCheckOutDate())
                    .status(reservation.getStatus())
                    .createdAt(reservation.getCreatedAt())
                    .numberOfGuests(reservation.getNumberOfGuests())
                    .totalPrice(reservation.getTotalPrice())
                    .guestId(reservation.getGuest().getId())
                    .roomId(reservation.getRoom().getId())
                    .build();
            reservationDTOs.add(reservationDTO);
        }
        return reservationDTOs;
    }

    public List<RoomReservationCount> getMostReservedRooms() {
        return reservationRepository.findMostReservedRooms();
    }


}
