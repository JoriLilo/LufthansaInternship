package de.lhind.internship.mini.project.controller;

import de.lhind.internship.mini.project.dto.RoomDTO;
import de.lhind.internship.mini.project.entity.Room;
import de.lhind.internship.mini.project.repository.RoomRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("/hotels/{hotelId}/rooms")
public class RoomController{

    private final RoomRepository roomRepository;
    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @PostMapping
    public ResponseEntity<Void> createRoom(@PathVariable Long hotelId , @Valid @RequestBody RoomDTO roomDTO) {
        Room room = new Room();

        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setRoomType(roomDTO.getRoomType());
        room.setCapacity(roomDTO.getCapacity());
        room.setPricePerNight(roomDTO.getPricePerNight());
        room.setStatus(roomDTO.getStatus());

        room = roomRepository.save(room);

        return new ResponseEntity<>(HttpStatus.CREATED);



    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getRooms(@PathVariable Long hotelId) {
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room : roomRepository.findAll()) {
            roomDTOS.add(new RoomDTO
                    (room.getRoomNumber(),
                            room.getRoomType(),
                            room.getCapacity(),
                            room.getPricePerNight(),
                            room.getStatus()));
        }
    }


}
