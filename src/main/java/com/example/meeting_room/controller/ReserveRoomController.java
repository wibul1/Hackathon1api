package com.example.meeting_room.controller;

import com.example.meeting_room.model.ReserveRoomTO;
import com.example.meeting_room.model.ReserveRoomDTO;
import com.example.meeting_room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ReserveRoomTO addRoom(@RequestBody ReserveRoomTO room) {
        return roomService.addRoom(room);
    }

    @GetMapping
    public List<ReserveRoomTO> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ReserveRoomDTO>> getRoomsByZoneIdAndStatus(
            @RequestParam("zone_id") Integer zoneId,
            @RequestParam(value = "status", required = false) String status) {

        List<ReserveRoomDTO> rooms = roomService.getRoomsByZoneIdAndStatus(zoneId, status);
        return ResponseEntity.ok(rooms);
    }




    @PutMapping("/{id}")
    public Optional<ReserveRoomTO> updateRoom(@PathVariable int id, @RequestBody ReserveRoomTO room) {
        return roomService.updateRoom(id, room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable int id) {
        roomService.deleteRoom(id);
    }
}
