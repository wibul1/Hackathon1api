package com.example.meeting_room.service;

import com.example.meeting_room.model.ZoneTO;
import com.example.meeting_room.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    public ZoneTO addZone(ZoneTO zone) {
        return zoneRepository.save(zone);
    }

    public List<ZoneTO> getAllZone() {
        return zoneRepository.findAll();
    }

}
