package com.example.meeting_room.repository;

import com.example.meeting_room.model.RoomTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomTO, Integer> {
    List<RoomTO> findByZone_IdAndStatus(Integer zoneId, String status);
    List<RoomTO> findByZone_Id(Integer zoneId);
}
