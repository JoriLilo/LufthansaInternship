package de.lhind.internship.controller;

import de.lhind.internship.dto.ReservationDTO;

import de.lhind.internship.entity.ReservationStatus;

import de.lhind.internship.service.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/reservations")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Void> createReservation(@Valid  @RequestBody ReservationDTO reservationDTO) {

        reservationService.createReservation(reservationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {

        List<ReservationDTO> reservationDTOS = reservationService.getAllReservations();
        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationDTO> getReservation(@PathVariable Long reservationId) {
        ReservationDTO reservationDTO = reservationService.getReservationById(reservationId);
        if (reservationDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reservationDTO, HttpStatus.OK);
    }
    @PatchMapping("/{reservationId}/status")
    public ResponseEntity<Void> updateReservationStatus(@PathVariable Long reservationId, @RequestParam ReservationStatus status) {
        boolean updated = reservationService.updateReservationByStatus(reservationId, status);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) {
        boolean deleted = reservationService.deleteReservationById(reservationId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByGuest(@PathVariable Long guestId) {
        List<ReservationDTO> reservations = reservationService.getReservationsByGuest(guestId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}
