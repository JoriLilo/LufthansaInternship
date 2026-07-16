package de.lhind.internship.mini.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "room")
@Getter
@Setter
public class Room {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private int roomNumber;
    @Setter
    @Getter
    private RoomType roomType;
    @Setter
    @Getter
    private  int capacity;
    @Setter
    @Getter
    private float pricePerNight;
    @Setter
    @Getter
    private RoomStatus status;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "Id")
    private Hotel hotel;

    public Room(Long id, int roomNumber, RoomType roomType, int capacity, float pricePerNight, RoomStatus status) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
        this.status = status;
    }



    public Room() {
    }


}
