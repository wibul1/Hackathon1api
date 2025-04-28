package com.example.meeting_room.controller;

import com.example.meeting_room.model.RoomTO;
import com.example.meeting_room.model.biz.ReserveRoomResDTO;
import com.example.meeting_room.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService Service;

    @PostMapping
    public RoomTO addRoom(@RequestBody RoomTO room) {
        return Service.addRoom(room);
    }

    @GetMapping("/adminSearchRoomByZone")
    public ResponseEntity<List<RoomTO>> getRoomByZoneId(@RequestParam("zone_id") Integer zoneId) {
        List<RoomTO> result = Service.getRoomByZoneId(zoneId);
        return ResponseEntity.ok(result);
    }


}
