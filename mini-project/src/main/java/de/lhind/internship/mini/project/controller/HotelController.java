package de.lhind.internship.mini.project.controller;

import de.lhind.internship.mini.project.dto.HotelDTO;
import de.lhind.internship.mini.project.service.HotelService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotels")
@AllArgsConstructor
public class HotelController {

    private final HotelService hotelService;


    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody HotelDTO hotelDTO) {
        hotelService.createHotel(hotelDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAll() {
        return new ResponseEntity<>(hotelService.getAllHotels(), HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDTO> getHotel(@PathVariable Long hotelId) {
        Optional<HotelDTO> hotelDTO = hotelService.getHotel(hotelId);
        if (hotelDTO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hotelDTO.get(), HttpStatus.OK);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<Void> updateHotel(@PathVariable Long hotelId, @Valid @RequestBody HotelDTO hotelDTO) {
        boolean updated = hotelService.updateHotel(hotelId, hotelDTO);
        return new ResponseEntity<>(updated ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long hotelId) {
        boolean deleted = hotelService.deleteHotel(hotelId);
        return new ResponseEntity<>(deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }


    @GetMapping("/search")
    public ResponseEntity<List<HotelDTO>> searchByCity(@RequestParam String city) {
        List<HotelDTO> hotels = hotelService.getHotelsByCity(city);
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

}