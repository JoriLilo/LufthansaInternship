package de.lhind.internship.repository;

import de.lhind.internship.entity.Hotel;
import de.lhind.internship.entity.Room;
import de.lhind.internship.entity.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findByHotelIdAndStatus(Long hotelId, RoomStatus status);

    List<Room> findRoomByHotelAndStatus(Hotel hotel, RoomStatus status);

    List<Room> findByHotelId(Long hotelId);
}