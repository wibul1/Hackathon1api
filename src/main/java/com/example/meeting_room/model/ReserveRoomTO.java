package com.example.meeting_room.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "reserve_room", schema = "project_test")
public class ReserveRoomTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "reserve_create_by", length = 45)
    private String reserveCreateBy;

    @Column(name = "reserve_create_dt", length = 45)
    private Timestamp reserveCreateDt;

    @Column(name = "time_from", length = 45)
    private String timeFrom;

    @Column(name = "time_to", length = 45)
    private String timeTo;


    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private RoomTO room;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReserveCreateBy() {
        return reserveCreateBy;
    }

    public void setReserveCreateBy(String reserveCreateBy) {
        this.reserveCreateBy = reserveCreateBy;
    }

    public Timestamp getReserveCreateDt() {
        return reserveCreateDt;
    }

    public void setReserveCreateDt(Timestamp reserveCreateDt) {
        this.reserveCreateDt = reserveCreateDt;
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

    public RoomTO getRoom() {
        return room;
    }

    public void setRoom(RoomTO room) {
        this.room = room;
    }
}

