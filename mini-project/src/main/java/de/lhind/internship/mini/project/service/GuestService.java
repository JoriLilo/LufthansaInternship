package de.lhind.internship.mini.project.service;

import de.lhind.internship.mini.project.dto.GuestDTO;
import de.lhind.internship.mini.project.entity.Guest;
import de.lhind.internship.mini.project.repository.GuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;

    public void createGuest(GuestDTO guestDTO) {

        Guest guest = new Guest();
        guest.setFirstName(guestDTO.getFirstName());
        guest.setLastName(guestDTO.getLastName());
        guest.setEmail(guestDTO.getEmail());
        guest.setPhone(guestDTO.getPhone());
        guestRepository.save(guest);


    }

    public List<GuestDTO> getAllGuests() {

        List<Guest> guests = guestRepository.findAll();
        List<GuestDTO> guestDTOs = new ArrayList<>();
        for (Guest guest : guests) {
            GuestDTO guestDTO = GuestDTO.builder()
                    .id(guest.getId())
                    .firstName(guest.getFirstName())
                    .lastName(guest.getLastName())
                    .email(guest.getEmail())
                    .phone(guest.getPhone())
                    .build();
            guestDTOs.add(guestDTO);

        }
        return guestDTOs;
    }

    public GuestDTO getGuestById(Long id) {
        Guest guest = guestRepository.findById(id).orElse(null);
        if (guest == null) {
            return null;
        }

        GuestDTO guestDTO = GuestDTO.builder()
                .id(guest.getId())
                .firstName(guest.getFirstName())
                .lastName(guest.getLastName())
                .email(guest.getEmail())
                .phone(guest.getPhone())
                .build();


        return guestDTO;
    }

    public boolean updateGuest(GuestDTO guestDTO) {

        Guest guest = guestRepository.findById(guestDTO.getId()).orElse(null);
        if (guest == null) {
            return false;
        }
        guest.setFirstName(guestDTO.getFirstName());
        guest.setLastName(guestDTO.getLastName());
        guest.setEmail(guestDTO.getEmail());
        guest.setPhone(guestDTO.getPhone());
        guestRepository.save(guest);
        return true;

    }

}
