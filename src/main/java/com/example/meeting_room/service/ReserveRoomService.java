package com.example.meeting_room.service;

import com.example.meeting_room.model.ReserveRoomTO;
import com.example.meeting_room.model.ReserveRoomDTO;
import com.example.meeting_room.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public ReserveRoomTO addRoom(ReserveRoomTO room) {
        return roomRepository.save(room);
    }

    public List<ReserveRoomTO> getAllRooms() {
        return roomRepository.findAll();
    }

    public List<ReserveRoomDTO> getRoomsByZoneIdAndStatus(Integer zoneId, String status) {
        List<ReserveRoomTO> rooms;

        // ถ้า status เป็น null หรือค่าว่าง ให้ค้นหาทั้งหมดของ zoneId
        if (status == null || status.isEmpty()) {
            rooms = roomRepository.findByZone_Id(zoneId); // ค้นหาตาม zoneId อย่างเดียว
        } else {
            rooms = roomRepository.findByZone_IdAndStatus(zoneId, status); // ค้นหาตาม zoneId และ status
        }

        // แปลงจาก RoomTO เป็น RoomDTO
        return rooms.stream().map(room -> {
            ReserveRoomDTO dto = new ReserveRoomDTO();
            dto.setId(room.getId());
            dto.setRoomNumber(room.getRoomNumber());
            dto.setStatus(room.getStatus());
            dto.setCreateBy(room.getCreateBy());
            dto.setCreateDate(room.getCreateDate());
            dto.setTimeFrom(room.getTimeFrom());
            dto.setTimeTo(room.getTimeTo());
            dto.setZoneName(room.getZone().getZoneName()); // ดึง zone_name จาก zoneTO
            return dto;
        }).collect(Collectors.toList());
    }





    public Optional<ReserveRoomTO> updateRoom(int id, ReserveRoomTO updatedRoom) {
        return roomRepository.findById(id).map(room -> {
            room.setRoomNumber(updatedRoom.getRoomNumber());
            room.setStatus(updatedRoom.getStatus());
            room.setCreateBy(updatedRoom.getCreateBy());
            room.setCreateDate(updatedRoom.getCreateDate());
            room.setTimeFrom(updatedRoom.getTimeFrom());
            room.setTimeTo(updatedRoom.getTimeTo());
            room.setZone(updatedRoom.getZone());
            return roomRepository.save(room);
        });
    }

    public void deleteRoom(int id) {
        roomRepository.deleteById(id);
    }
}