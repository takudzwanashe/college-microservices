package com.tapstaku.college.accommodation.domain.entities;

import com.tapstaku.college.utils.enums.HostelLocationType;
import com.tapstaku.college.utils.enums.HostelStatus;
import com.tapstaku.college.utils.enums.HostelGenderType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Hostel {

    @Id
    private String id;

    @Column(name = "hostel_name", length = 40)
    private String name;

    @Column(name = "status", length = 12)
    private HostelStatus status;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "hostel_type", length = 30)
    private HostelGenderType type;

    @Column(name = "hostel_location", length = 12)
    private HostelLocationType locationType;

    @OneToMany
    private List<Room> rooms;
}
