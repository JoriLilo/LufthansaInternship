package de.lhind.internship.repository;


import de.lhind.internship.entity.Hotel;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    public List<Hotel> findHotelByCityIgnoreCase(@NotBlank String city);


}
