package com.example.meeting_room.model.biz;

import java.util.List;

public class ReserveRoomResDTO {
    private Integer id;
    private Integer roomId;
    private String roomName;
    private String roomNumber;
    private String zoneName;
    private boolean isAvailableNow;
    private String timeFrom;
    private String timeTo;
    private String status;
    private List<String> nextAvailableSlots;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public boolean isAvailableNow() {
        return isAvailableNow;
    }

    public void setAvailableNow(boolean availableNow) {
        isAvailableNow = availableNow;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getNextAvailableSlots() {
        return nextAvailableSlots;
    }

    public void setNextAvailableSlots(List<String> nextAvailableSlots) {
        this.nextAvailableSlots = nextAvailableSlots;
    }
}
