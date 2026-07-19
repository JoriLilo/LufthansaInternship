package de.lhind.internship.mini.project.controller;


import de.lhind.internship.mini.project.dto.GuestProfileDTO;
import de.lhind.internship.mini.project.entity.GuestProfile;
import de.lhind.internship.mini.project.service.GuestProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guests/{guestId}/profile")
@AllArgsConstructor
public class GuestProfileController {

    private final GuestProfileService guestProfileService;

    @PostMapping
    public ResponseEntity<Void> save(@PathVariable Long guestId , @Valid  @RequestBody GuestProfileDTO guestProfileDTO) {
        guestProfileService.createGuestProfile(guestId,guestProfileDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<GuestProfileDTO> getGuest(@PathVariable Long guestId) {
        GuestProfileDTO dto = guestProfileService.getGuestProfile(guestId);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }
}