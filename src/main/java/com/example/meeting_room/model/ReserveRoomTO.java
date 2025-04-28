package com.example.meeting_room.model;

import jakarta.persistence.*;

@Entity
@Table(name = "room", schema = "project_test")
public class RoomTO {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "room_number", length = 4)
    private String roomNumber;

    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "create_by", length = 45)
    private String createBy;

    @Column(name = "create_date", length = 45)
    private String createDate;

    @Column(name = "time_from", length = 45)
    private String timeFrom;

    @Column(name = "time_to", length = 45)
    private String timeTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id", referencedColumnName = "id", nullable = false)
    private ZoneTO zone;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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

    public ZoneTO getZone() {
        return zone;
    }

    public void setZone(ZoneTO zone) {
        this.zone = zone;
    }


}

