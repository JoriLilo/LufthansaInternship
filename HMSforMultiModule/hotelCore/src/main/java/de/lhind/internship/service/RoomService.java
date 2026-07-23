package de.lhind.internship.service;

import de.lhind.internship.dto.RoomDTO;
import de.lhind.internship.entity.Hotel;
import de.lhind.internship.entity.Room;
import de.lhind.internship.entity.RoomStatus;
import de.lhind.internship.repository.HotelRepository;
import de.lhind.internship.repository.RoomRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public void createRoom(Long hotelId, RoomDTO roomDTO) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id " + hotelId));

        Room room = new Room();
        room.setHotel(hotel);
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setRoomType(roomDTO.getRoomType());
        room.setCapacity(roomDTO.getCapacity());
        room.setPricePerNight(roomDTO.getPricePerNight());
        room.setStatus(roomDTO.getStatus());

        roomRepository.save(room);
    }
    public List<RoomDTO> getRooms(Long hotelId) {

        if (!hotelRepository.existsById(hotelId)) {
            throw new RuntimeException("Hotel not found with id " + hotelId);
        }

        List<Room> rooms = roomRepository.findByHotelId(hotelId);
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room : rooms) {
            RoomDTO roomDTO = RoomDTO.builder()
                    .id(room.getId())
                    .roomNumber(room.getRoomNumber())
                    .roomType(room.getRoomType())
                    .capacity(room.getCapacity())
                    .pricePerNight(room.getPricePerNight())
                    .status(room.getStatus())
                    .build();
            roomDTOS.add(roomDTO);
        }
        return roomDTOS;
    }

    public Optional<RoomDTO> getRoom(Long roomId) {
        Optional<Room> roomOpt = roomRepository.findById(roomId);
        if (roomOpt.isEmpty()) {
            return Optional.empty();
        }
        Room room = roomOpt.get();
        RoomDTO roomDTO = RoomDTO.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .roomType(room.getRoomType())
                .capacity(room.getCapacity())
                .pricePerNight(room.getPricePerNight())
                .status(room.getStatus())
                .build();
        return Optional.of(roomDTO);
    }

    public boolean updateRoomStatus(Long roomId, RoomStatus status) {
        Optional<Room> roomOpt = roomRepository.findById(roomId);
        if (roomOpt.isEmpty()) {
            return false;
        }
        Room room = roomOpt.get();
        room.setStatus(status);
        roomRepository.save(room);

        return true;
    }
    public boolean updateRoom(Long roomId, RoomDTO roomDTO) {
        Optional<Room> roomOpt = roomRepository.findById(roomId);
        if (roomOpt.isEmpty()) {
            return false;
        }
        Room room = roomOpt.get();
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setRoomType(roomDTO.getRoomType());
        room.setCapacity(roomDTO.getCapacity());
        room.setPricePerNight(roomDTO.getPricePerNight());
        room.setStatus(roomDTO.getStatus());
        roomRepository.save(room);
        return true;
    }

    public boolean deleteRoom(Long roomId) {
        Optional<Room> roomOpt = roomRepository.findById(roomId);
        if (roomOpt.isEmpty()) {
            return false;
        }
        Room room = roomOpt.get();
        roomRepository.delete(room);
        return true;
    }

    public List<RoomDTO> getRoomsByHotelAndStatus(Long hotelId, RoomStatus status) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id " + hotelId));

        List<RoomDTO> roomDTOS = new ArrayList<>();
        List<Room> rooms = roomRepository.findRoomByHotelAndStatus(hotelRepository.findById(hotelId).get(), status);
        for (Room room : rooms) {
            RoomDTO roomDTO = RoomDTO.builder()
                    .roomNumber(room.getRoomNumber())
                    .roomType(room.getRoomType())
                    .capacity(room.getCapacity())
                    .pricePerNight(room.getPricePerNight())
                    .status(room.getStatus())
                    .build();
            roomDTOS.add(roomDTO);
        }
        return roomDTOS;
    }

}
