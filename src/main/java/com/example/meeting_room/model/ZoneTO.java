package com.example.meeting_room.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "zone", schema = "project_test")
public class ZoneTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "zone_name", length = 50)
    private String zoneName;

    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "zone_create_by", length = 45)
    private String zoneCreateBy;

    @Column(name = "zone_create_dt", length = 45)
    private Timestamp zoneCreateDt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getZoneCreateBy() {
        return zoneCreateBy;
    }

    public void setZoneCreateBy(String zoneCreateBy) {
        this.zoneCreateBy = zoneCreateBy;
    }

    public Timestamp getZoneCreateDt() {
        return zoneCreateDt;
    }

    public void setZoneCreateDt(Timestamp zoneCreateDt) {
        this.zoneCreateDt = zoneCreateDt;
    }
}
