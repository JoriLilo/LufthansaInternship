package de.lhind.internship.mini.project.dto;

import de.lhind.internship.mini.project.entity.RoomStatus;
import de.lhind.internship.mini.project.entity.RoomType;
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
