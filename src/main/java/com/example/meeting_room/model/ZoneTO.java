package com.example.meeting_room.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "zone", schema = "project_test")
public class zoneTO {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "zone_name", length = 50)
    private String zoneName;

    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "create_by", length = 45)
    private String createBy;

    @Column(name = "create_date", length = 45)
    private String createDate;

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
}
