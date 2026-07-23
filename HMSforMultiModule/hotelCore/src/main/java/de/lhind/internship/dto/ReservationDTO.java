package de.lhind.internship.dto;

import de.lhind.internship.entity.ReservationStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDTO {

    private Long id;
    private Long guestId;
    private Long roomId;
    @NotNull
    private LocalDate checkInDate;
    @NotNull
    private LocalDate checkOutDate;
    @Positive
    private int numberOfGuests;
    private double totalPrice;
    private ReservationStatus status;

    private LocalDate createdAt;
}