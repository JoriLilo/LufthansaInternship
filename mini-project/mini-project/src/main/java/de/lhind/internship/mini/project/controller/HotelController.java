package de.lhind.internship.mini.project.controller;

import de.lhind.internship.mini.project.dto.HotelDTO;
import de.lhind.internship.mini.project.entity.Hotel;
import de.lhind.internship.mini.project.repository.HotelRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/hotel")

public class HotelController {

    private final HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid  @RequestBody HotelDTO hotelDTO) {

        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotel.setCity(hotelDTO.getCity());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setStarRating(hotelDTO.getStarRating());

        hotelRepository.save(hotel);
        return new ResponseEntity<>(HttpStatus.CREATED);


    }

    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAll() {
        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelDTO> hotelDTOs = new ArrayList<>();
        for (Hotel hotel : hotels) {
            hotelDTOs.add(new HotelDTO
                    (hotel.getId()
                            ,hotel.getName()
                            ,hotel.getCity()
                            ,hotel.getAddress()
                            ,hotel.getStarRating()));

        }
        return new ResponseEntity<>(hotelDTOs, HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDTO> getHotel(@PathVariable Long hotelId) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if(hotel.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HotelDTO hotelDTO = new HotelDTO(hotel.get().getName(), hotel.get().getCity(), hotel.get().getAddress(), hotel.get().getStarRating());


        return new ResponseEntity<>(hotelDTO, HttpStatus.OK);

    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<Void> updateHotel(@PathVariable Long hotelId, @Valid @RequestBody HotelDTO hotelDTO) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if(hotel.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        hotel.get().setName(hotelDTO.getName());
        hotel.get().setCity(hotelDTO.getCity());
        hotel.get().setAddress(hotelDTO.getAddress());
        hotel.get().setStarRating(hotelDTO.getStarRating());

        hotelRepository.save(hotel.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long hotelId) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if(hotel.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        hotelRepository.delete(hotel.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
