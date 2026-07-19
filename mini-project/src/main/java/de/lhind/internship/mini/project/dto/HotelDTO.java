package de.lhind.internship.mini.project.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelDTO {

    private Long Id;
    @NotBlank
    private String name;

    private String city;
    @NotBlank
    private String address;

    private float starRating;

}