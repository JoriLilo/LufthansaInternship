package de.lhind.internship.dto;

import de.lhind.internship.entity.RoomStatus;
import de.lhind.internship.entity.RoomType;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDTO {

    private Long id;
    private int roomNumber;
    private RoomType roomType;
    @Positive
    private int capacity;
    private float pricePerNight;
    private RoomStatus status;


}
