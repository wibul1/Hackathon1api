package com.example.meeting_room.service;

import com.example.meeting_room.model.RoomTO;
import com.example.meeting_room.repository.RoomRepository;
import com.example.meeting_room.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository RoomRepository;

    public RoomTO addRoom(RoomTO zone) {
        return RoomRepository.save(zone);
    }

    public List<RoomTO> getRoomByZoneId(Integer zoneId) {
        return RoomRepository.findByZone_IdAndStatus(zoneId,"A");
    }
}
