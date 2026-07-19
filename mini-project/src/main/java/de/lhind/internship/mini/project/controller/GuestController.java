package de.lhind.internship.mini.project.controller;

import de.lhind.internship.mini.project.dto.GuestDTO;
import de.lhind.internship.mini.project.service.GuestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
@AllArgsConstructor
public class GuestController {

    private final GuestService guestService;

    @PostMapping
    public ResponseEntity<Void> save(@Valid  @RequestBody GuestDTO guestDTO) {
          guestService.createGuest(guestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GuestDTO>> getAll() {

        List<GuestDTO> guestDTOS = guestService.getAllGuests();
        if (guestDTOS.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(guestDTOS, HttpStatus.OK);


    }

    @GetMapping("/{guestId}")
    public ResponseEntity<GuestDTO> getGuest(@PathVariable Long guestId) {

        GuestDTO guestDTO = guestService.getGuestById(guestId);
        if (guestDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(guestDTO, HttpStatus.OK);
    }

    @PutMapping("/{guestId}")
    public ResponseEntity<Void> updateGuest(@PathVariable Long guestId,@Valid @RequestBody GuestDTO guestDTO) {
        boolean updated = guestService.updateGuest(guestDTO);
        return new ResponseEntity<>(updated ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }


}
