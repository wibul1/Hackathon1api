package com.example.meeting_room.controller;

import com.example.meeting_room.model.ReserveRoomTO;
import com.example.meeting_room.model.biz.ReserveRoomReqDTO;
import com.example.meeting_room.model.biz.ReserveRoomResDTO;
import com.example.meeting_room.service.ReserveRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reserve_rooms")
public class ReserveRoomController {

    @Autowired
    private ReserveRoomService reserveRoomService;

//    @PostMapping
//    public ReserveRoomTO addRoom(@RequestBody ReserveRoomTO room) {
//        return reserveRoomService.addRoom(room);
//    }
//
//    @GetMapping
//    public List<ReserveRoomTO> getAllRooms() {
//        return reserveRoomService.getAllRooms();
//    }

//    @GetMapping("/search")
//    public ResponseEntity<List<ReserveRoomDTO>> getRoomsByZoneIdAndStatus(
//            @RequestParam("zone_id") Integer zoneId,
//            @RequestParam(value = "status", required = false) String status) {
//
//        List<ReserveRoomDTO> rooms = reserveRoomService.getRoomsByZoneIdAndStatus(zoneId, status);
//        return ResponseEntity.ok(rooms);
//    }

    @GetMapping("/searchRoomByZone")
    public ResponseEntity<List<ReserveRoomResDTO>> getRoomAvailabilityByZone(@RequestParam("zone_id") Integer zoneId) {
        List<ReserveRoomResDTO> result = reserveRoomService.checkRoomAvailabilityByZone(zoneId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/searchTimeByRoomId")
    public ResponseEntity<List<Map<String, String>>> getTimeSlots(@RequestParam("roomId") Integer roomId) {
        List<Map<String, String>> times = reserveRoomService.getTimeSlotsByRoomId(roomId);
        return ResponseEntity.ok(times);
    }

    @PostMapping("/addReserveRoom")
    public ResponseEntity<ReserveRoomTO> createReservation(@RequestBody ReserveRoomReqDTO req) {

        ReserveRoomTO saved = reserveRoomService.createReservation(req);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/changStatusReserveRoom")
    public ResponseEntity<ReserveRoomTO> changStatus(@RequestBody ReserveRoomReqDTO req) {

        ReserveRoomTO saved = reserveRoomService.changStatus(req);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/searchReserveRoom")
    public ResponseEntity<List<ReserveRoomResDTO>> getReserveRoom(
            @RequestParam("zone_id") Integer zoneId,
            @RequestParam(value = "room_id", required = false) Integer roomId) {
        List<ReserveRoomResDTO> result = reserveRoomService.searchReserveRoom(zoneId,roomId);
        return ResponseEntity.ok(result);
    }





//    @PutMapping("/{id}")
//    public Optional<ReserveRoomTO> updateRoom(@PathVariable int id, @RequestBody ReserveRoomTO room) {
//        return reserveRoomService.updateRoom(id, room);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteRoom(@PathVariable int id) {
//        reserveRoomService.deleteRoom(id);
//    }
}
