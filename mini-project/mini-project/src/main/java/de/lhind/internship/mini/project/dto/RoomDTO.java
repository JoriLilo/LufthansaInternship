package de.lhind.internship.mini.project.dto;

import de.lhind.internship.mini.project.entity.RoomStatus;
import de.lhind.internship.mini.project.entity.RoomType;

public class RoomDTO {

    private Long id;
    private int roomNumber;
    private RoomType roomType;
    private int capacity;
    private float pricePerNight;
    private RoomStatus status;

    public RoomDTO(Long id, int roomNumber, RoomType roomType, int capacity, float pricePerNight, RoomStatus status) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
        this.status = status;
    }

    public RoomDTO(int roomNumber, RoomType roomType, int capacity, float pricePerNight, RoomStatus status) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
        this.status = status;
    }

    public RoomDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public float getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(float pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }
}
