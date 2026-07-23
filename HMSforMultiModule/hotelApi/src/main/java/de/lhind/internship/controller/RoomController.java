package de.lhind.internship.controller;

import de.lhind.internship.dto.RoomDTO;
import de.lhind.internship.entity.RoomStatus;
import de.lhind.internship.service.RoomService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping
public class RoomController{

    private final RoomService roomService;


    @PostMapping("/api/hotels/{hotelId}/rooms")
    public ResponseEntity<Void> createRoom(@PathVariable Long hotelId, @Valid @RequestBody RoomDTO roomDTO) {
        roomService.createRoom(hotelId, roomDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/hotels/{hotelId}/rooms")
    public ResponseEntity<List<RoomDTO>> getRooms(@PathVariable Long hotelId) {
        List<RoomDTO> roomDTOS = roomService.getRooms(hotelId);
        return new ResponseEntity<>(roomDTOS, HttpStatus.OK);
    }
    @GetMapping("/api/rooms/{roomId}")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable Long roomId) {
        Optional<RoomDTO> roomDTO = roomService.getRoom(roomId);
        if (roomDTO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(roomDTO.get(), HttpStatus.OK);
    }

    @PatchMapping("/api/rooms/{roomId}/status")
    public ResponseEntity<RoomDTO> updateRoomStatus(@PathVariable Long roomId, @RequestParam RoomStatus status) {
        Optional<RoomDTO> roomDTOOpt = roomService.getRoom(roomId);
        if (roomDTOOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        boolean updated= roomService.updateRoomStatus(roomId,status);
        if (!updated) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);


    }

    @PutMapping("/api/rooms/{roomId}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable Long roomId, @RequestBody RoomDTO roomDTO) {
        Optional<RoomDTO> roomDTOs = roomService.getRoom(roomId);
        if (roomDTOs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean updated= roomService.updateRoom(roomId,roomDTO);
        if (!updated) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/api/rooms/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
        Optional<RoomDTO> roomDTO = roomService.getRoom(roomId);
        if (roomDTO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean deleted= roomService.deleteRoom(roomId);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/api/rooms/search")
    public ResponseEntity<List<RoomDTO>> searchRoomByHotelAndStatus(@RequestParam Long hotelId, @RequestParam RoomStatus status) {
        List<RoomDTO> roomDTOS = roomService.getRoomsByHotelAndStatus(hotelId, status);
        return new ResponseEntity<>(roomDTOS,HttpStatus.OK);
    }



}