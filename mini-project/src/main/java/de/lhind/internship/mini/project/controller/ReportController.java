package de.lhind.internship.mini.project.controller;

import de.lhind.internship.mini.project.repository.RoomReservationCount;
import de.lhind.internship.mini.project.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@AllArgsConstructor
public class ReportController {

    private final ReservationService reservationService;

    @GetMapping("/most-reserved-rooms")
    public ResponseEntity<List<RoomReservationCount>> getMostReservedRooms() {
        return new ResponseEntity<>(reservationService.getMostReservedRooms(), HttpStatus.OK);
    }
}