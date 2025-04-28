package com.example.meeting_room.repository;

import com.example.meeting_room.model.ReserveRoomTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReserveRoomRepository extends JpaRepository<ReserveRoomTO, Integer> {

    List<ReserveRoomTO> findByRoom_IdAndStatusIn(Integer roomId, List<String> statuses);
    List<ReserveRoomTO> findByRoomIdInAndStatusIn(List<Integer> roomIds, List<String> statuses);
}


