package com.example.meeting_room.controller;

import com.example.meeting_room.model.ZoneTO;
import com.example.meeting_room.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    @PostMapping
    public ZoneTO addZone(@RequestBody ZoneTO zone) {
        return zoneService.addZone(zone);
    }

    @GetMapping
    public List<ZoneTO> getAllZone() {
        return zoneService.getAllZone();
    }
}
