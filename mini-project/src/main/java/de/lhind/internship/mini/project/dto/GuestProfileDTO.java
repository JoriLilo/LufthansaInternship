package de.lhind.internship.mini.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuestProfileDTO {


    private Long id;
    @NotBlank
    private String address;
    @NotBlank
    private LocalDate dateOfBirth;
    @NotBlank
    private String nationality;
    @NotBlank
    public String preferredLanguage;
}
