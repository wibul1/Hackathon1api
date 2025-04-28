package com.example.meeting_room.service;

import com.example.meeting_room.model.ReserveRoomTO;
import com.example.meeting_room.model.RoomTO;
import com.example.meeting_room.model.biz.ReserveRoomReqDTO;
import com.example.meeting_room.model.biz.ReserveRoomResDTO;
import com.example.meeting_room.repository.ReserveRoomRepository;
import com.example.meeting_room.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReserveRoomService {

    @Autowired
    private ReserveRoomRepository ReserveRoomRepository;

    @Autowired
    private RoomRepository RoomRepository;

//    public ReserveRoomTO addRoom(ReserveRoomTO room) {
//        return ReserveRoomRepository.save(room);
//    }
//
//    public List<ReserveRoomTO> getAllRooms() {
//        return ReserveRoomRepository.findAll();
//    }

//    public List<ReserveRoomDTO> getRoomsByZoneIdAndStatus(Integer zoneId, String status) {
//        List<ReserveRoomTO> rooms;
//
//        // ถ้า status เป็น null หรือค่าว่าง ให้ค้นหาทั้งหมดของ zoneId
//        if (status == null || status.isEmpty()) {
//            rooms = roomRepository.findByZone_Id(zoneId); // ค้นหาตาม zoneId อย่างเดียว
//        } else {
//            rooms = roomRepository.findByZone_IdAndStatus(zoneId, status); // ค้นหาตาม zoneId และ status
//        }
//
//        // แปลงจาก RoomTO เป็น RoomDTO
//        return rooms.stream().map(room -> {
//            ReserveRoomDTO dto = new ReserveRoomDTO();
//            dto.setId(room.getId());
//            dto.setRoomNumber(room.getRoomNumber());
//            dto.setStatus(room.getStatus());
//            dto.setCreateBy(room.getCreateBy());
//            dto.setCreateDate(room.getCreateDate());
//            dto.setTimeFrom(room.getTimeFrom());
//            dto.setTimeTo(room.getTimeTo());
//            dto.setZoneName(room.getZone().getZoneName()); // ดึง zone_name จาก zoneTO
//            return dto;
//        }).collect(Collectors.toList());
//    }
//
//
//
//
//
//    public Optional<ReserveRoomTO> updateRoom(int id, ReserveRoomTO updatedRoom) {
//        return roomRepository.findById(id).map(room -> {
//            room.setRoomNumber(updatedRoom.getRoomNumber());
//            room.setStatus(updatedRoom.getStatus());
//            room.setCreateBy(updatedRoom.getCreateBy());
//            room.setCreateDate(updatedRoom.getCreateDate());
//            room.setTimeFrom(updatedRoom.getTimeFrom());
//            room.setTimeTo(updatedRoom.getTimeTo());
//            room.setZone(updatedRoom.getZone());
//            return roomRepository.save(room);
//        });
//    }
//
//    public void deleteRoom(int id) {
//        roomRepository.deleteById(id);
//    }



    public List<ReserveRoomResDTO> checkRoomAvailabilityByZone(Integer zoneId) {
        List<RoomTO> rooms = RoomRepository.findByZone_IdAndStatus(zoneId,"A");  // เอาทุกห้องใน zone
        List<ReserveRoomTO> allBookings = ReserveRoomRepository.findAll(); // ดึงจองทั้งหมด

        // กรองเฉพาะ booking ที่ status = 'A'
        Map<Integer, List<ReserveRoomTO>> groupedBookings = allBookings.stream()
                .filter(r -> r.getRoom() != null && "A".equalsIgnoreCase(r.getStatus()))
                .collect(Collectors.groupingBy(r -> r.getRoom().getId()));

        List<ReserveRoomResDTO> result = new ArrayList<>();
        LocalTime now = LocalTime.now();

        for (RoomTO room : rooms) {
            List<ReserveRoomTO> bookings = groupedBookings.getOrDefault(room.getId(), new ArrayList<>());

            ReserveRoomResDTO dto = new ReserveRoomResDTO();
            dto.setRoomId(room.getId());
            dto.setRoomNumber(room.getRoomNumber());
            dto.setZoneName(room.getZone().getZoneName());

            boolean available = true;
            String timeFrom = null;
            String timeTo = null;
            String status = null;
            List<String> nextSlots = new ArrayList<>();

            for (ReserveRoomTO booking : bookings) {
                LocalTime from = LocalTime.parse(booking.getTimeFrom());
                LocalTime to = LocalTime.parse(booking.getTimeTo());

                if (now.isAfter(from) && now.isBefore(to)) {
                    available = false;
                    timeFrom = from.toString();
                    timeTo = to.toString();
                    status = booking.getStatus();
                } else if (now.isBefore(from)) {
                    nextSlots.add(from + " - " + to);
                }
            }

            dto.setAvailableNow(available);
            dto.setTimeFrom(timeFrom);
            dto.setTimeTo(timeTo);
            dto.setStatus(status);
            dto.setNextAvailableSlots(nextSlots);

            result.add(dto);
        }

        return result;
    }


    public List<Map<String, String>> getTimeSlotsByRoomId(Integer roomId) {
        List<ReserveRoomTO> bookings = ReserveRoomRepository.findByRoom_IdAndStatusIn(roomId, Arrays.asList("A", "R"));

        List<Map<String, String>> times = new ArrayList<>();
        for (ReserveRoomTO booking : bookings) {
            Map<String, String> map = new HashMap<>();
            map.put("timeFrom", booking.getTimeFrom());
            map.put("timeTo", booking.getTimeTo());
            times.add(map);
        }

        return times;
    }


    public ReserveRoomTO createReservation(ReserveRoomReqDTO req) {
//        System.out.println("getRoomId: " + req.getRoom_id());

        // ดึง Room จาก roomId
        RoomTO room = RoomRepository.findById(req.getRoom_id())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        ReserveRoomTO reservation = new ReserveRoomTO();
        reservation.setRoom(room); // ส่ง RoomTO แทน Integer
        reservation.setTimeFrom(req.getTimeFrom());
        reservation.setTimeTo(req.getTimeTo());

        // แปลง String เป็น LocalTime
        LocalTime now = LocalTime.now().withSecond(0).withNano(0); // ตัดวินาทีทิ้ง เพื่อเทียบง่าย
        LocalTime inputTime = LocalTime.parse(req.getTimeFrom());

        // ถ้าเวลาเริ่มตรงกับเวลาปัจจุบัน -> status = 'A' (active)
        if (inputTime.equals(now)) {
            reservation.setStatus("A");
        } else {
            reservation.setStatus("R"); // ยังไม่ถึงเวลา -> จองล่วงหน้า
        }

        reservation.setReserveCreateBy("w");
        reservation.setReserveCreateDt(Timestamp.valueOf(LocalDateTime.now()));

        return ReserveRoomRepository.save(reservation);
    }


    public ReserveRoomTO changStatus(ReserveRoomReqDTO req) {
        Optional<ReserveRoomTO> optionalReservation = ReserveRoomRepository.findById(req.getId());

        if (optionalReservation.isPresent()) {
            ReserveRoomTO reservation = optionalReservation.get();

            String currentStatus = reservation.getStatus();
            if ("A".equals(currentStatus)) {
                reservation.setStatus("I");
            } else if ("R".equals(currentStatus)) {
                reservation.setStatus("A");
            }

            return ReserveRoomRepository.save(reservation); // ใช้ save() แทน update()
        } else {
            throw new RuntimeException("Reservation not found with id: " + req.getId());
        }
    }


    public List<ReserveRoomResDTO> searchReserveRoom(Integer zoneId, Integer roomId) {
        List<Integer> roomIds = new ArrayList<>();
        if (roomId != null) {
            RoomTO room = RoomRepository.findById(roomId).orElse(null);
            if (room != null && room.getZone().getId().equals(zoneId)) {
                roomIds.add(roomId);
            } else {
                return Collections.emptyList();
            }
        } else {
            List<RoomTO> roomsInZone = RoomRepository.findByZone_Id(zoneId);
            roomIds = roomsInZone.stream()
                    .map(RoomTO::getId)
                    .collect(Collectors.toList());
        }


        // ดึงข้อมูลจาก ReserveRoomTO ตาม room_id และ status A หรือ R
        List<ReserveRoomTO> reserveRooms = ReserveRoomRepository.findByRoomIdInAndStatusIn(roomIds, List.of("A", "R"));

        // map ข้อมูลเป็น DTO
        return reserveRooms.stream().map(res -> {
            ReserveRoomResDTO dto = new ReserveRoomResDTO();
            dto.setId(res.getId());
            dto.setRoomId(res.getRoom().getId());
            dto.setRoomNumber(res.getRoom().getRoomNumber());
            dto.setRoomName(res.getRoom().getRoomName());
            dto.setZoneName(res.getRoom().getZone().getZoneName());
            dto.setTimeFrom(res.getTimeFrom());
            dto.setTimeTo(res.getTimeTo());
            dto.setStatus(res.getStatus());
            return dto;
        }).sorted(Comparator.comparing(ReserveRoomResDTO::getRoomNumber)) // ✅ sort ด้วย roomNumber
                .collect(Collectors.toList());
    }




}