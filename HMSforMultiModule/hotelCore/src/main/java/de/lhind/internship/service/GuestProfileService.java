package de.lhind.internship.service;

import de.lhind.internship.dto.GuestProfileDTO;
import de.lhind.internship.entity.Guest;
import de.lhind.internship.entity.GuestProfile;
import de.lhind.internship.repository.GuestProfileRepository;
import de.lhind.internship.repository.GuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GuestProfileService {

    private final GuestProfileRepository guestProfileRepository;
    private final GuestRepository guestRepository;

    public GuestProfileDTO createGuestProfile(Long guestId, GuestProfileDTO guestProfileDTO) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found with id " + guestId));

        if (guest.getGuestProfile() != null) {
            throw new RuntimeException("Guest with id " + guestId + " already has a profile");
        }

        GuestProfile guestProfile = new GuestProfile();
        guestProfile.setGuest(guest);
        guestProfile.setAddress(guestProfileDTO.getAddress());
        guestProfile.setDateOfBirth(guestProfileDTO.getDateOfBirth());
        guestProfile.setNationality(guestProfileDTO.getNationality());
        guestProfile.setPreferredLanguage(guestProfileDTO.getPreferredLanguage());

        GuestProfile saved = guestProfileRepository.save(guestProfile);

        return GuestProfileDTO.builder()
                .id(saved.getId())
                .address(saved.getAddress())
                .dateOfBirth(saved.getDateOfBirth())
                .nationality(saved.getNationality())
                .preferredLanguage(saved.getPreferredLanguage())
                .build();
    }

    public GuestProfileDTO getGuestProfile(Long guestId) {
        Guest guest = guestRepository.findById(guestId).orElse(null);
        if (guest == null || guest.getGuestProfile() == null) {
            return null;
        }
        GuestProfile guestProfile = guest.getGuestProfile();
        return GuestProfileDTO.builder()
                .id(guestProfile.getId())
                .address(guestProfile.getAddress())
                .dateOfBirth(guestProfile.getDateOfBirth())
                .nationality(guestProfile.getNationality())
                .preferredLanguage(guestProfile.getPreferredLanguage())
                .build();
    }
}