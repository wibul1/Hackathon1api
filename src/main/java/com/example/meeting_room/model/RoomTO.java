package com.example.meeting_room.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "room", schema = "project_test")
public class RoomTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "room_name", length = 45)
    private String roomName;

    @Column(name = "room_number", length = 45)
    private String roomNumber;

    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "room_create_by", length = 45)
    private String roomCreateBy;

    @Column(name = "room_create_dt", length = 45)
    private Timestamp roomCreateDt;

    @ManyToOne
    @JoinColumn(name = "zone_id", referencedColumnName = "id")
    private ZoneTO zone;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoomCreateBy() {
        return roomCreateBy;
    }

    public void setRoomCreateBy(String roomCreateBy) {
        this.roomCreateBy = roomCreateBy;
    }

    public Timestamp getRoomCreateDt() {
        return roomCreateDt;
    }

    public void setRoomCreateDt(Timestamp roomCreateDt) {
        this.roomCreateDt = roomCreateDt;
    }

    public ZoneTO getZone() {
        return zone;
    }

    public void setZone(ZoneTO zone) {
        this.zone = zone;
    }
}
