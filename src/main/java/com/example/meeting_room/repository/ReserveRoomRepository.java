package com.example.meeting_room.repository;

import com.example.meeting_room.model.ReserveRoomTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<ReserveRoomTO, Integer> {
    List<ReserveRoomTO> findByZone_IdAndStatus(Integer zoneId, String status); // ค้นหาจาก zoneId และ status
    List<ReserveRoomTO> findByZone_Id(Integer zoneId); // ค้นหาจาก zoneId อย่างเดียว
}


