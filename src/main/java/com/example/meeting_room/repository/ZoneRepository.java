package com.example.meeting_room.repository;

import com.example.meeting_room.model.ZoneTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<ZoneTO, Integer> {
}
