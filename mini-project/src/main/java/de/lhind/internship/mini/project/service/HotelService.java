package de.lhind.internship.mini.project.service;

import de.lhind.internship.mini.project.dto.HotelDTO;
import de.lhind.internship.mini.project.entity.Hotel;
import de.lhind.internship.mini.project.repository.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;



    public void createHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotel.setCity(hotelDTO.getCity());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setStarRating(hotelDTO.getStarRating());
        hotelRepository.save(hotel);
    }

    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(hotel -> HotelDTO.builder()
                        .Id(hotel.getId())
                        .name(hotel.getName())
                        .city(hotel.getCity())
                        .address(hotel.getAddress())
                        .starRating(hotel.getStarRating())
                        .build())
                .toList();
    }

    public Optional<HotelDTO> getHotel(Long hotelId) {
        Optional<Hotel> hotelOpt = hotelRepository.findById(hotelId);
        if (hotelOpt.isEmpty()) {
            return Optional.empty();
        }

        Hotel hotel = hotelOpt.get();
        HotelDTO hotelDTO = HotelDTO.builder()
                .Id(hotel.getId())
                .name(hotel.getName())
                .city(hotel.getCity())
                .address(hotel.getAddress())
                .starRating(hotel.getStarRating())
                .build();

        return Optional.of(hotelDTO);
    }

    public boolean updateHotel(Long hotelId, HotelDTO hotelDTO) {
        Optional<Hotel> hotelOpt = hotelRepository.findById(hotelId);
        if (hotelOpt.isEmpty()) {
            return false;
        }
        Hotel hotel = hotelOpt.get();
        hotel.setName(hotelDTO.getName());
        hotel.setCity(hotelDTO.getCity());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setStarRating(hotelDTO.getStarRating());
        hotelRepository.save(hotel);
        return true;
    }

    public boolean deleteHotel(Long hotelId) {
        Optional<Hotel> hotelOpt = hotelRepository.findById(hotelId);
        if (hotelOpt.isEmpty()) {
            return false;
        }
        hotelRepository.delete(hotelOpt.get());
        return true;
    }



    public List<HotelDTO> getHotelsByCity(String city) {
        return hotelRepository.findHotelByCityIgnoreCase(city).stream()
                .map(hotel -> HotelDTO.builder()
                        .Id(hotel.getId())
                        .name(hotel.getName())
                        .city(hotel.getCity())
                        .address(hotel.getAddress())
                        .starRating(hotel.getStarRating())
                        .build())
                .toList();
    }
}