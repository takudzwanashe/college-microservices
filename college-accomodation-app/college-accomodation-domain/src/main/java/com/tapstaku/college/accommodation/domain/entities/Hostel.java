package com.tapstaku.college.accommodation.domain.entities;

import com.tapstaku.college.utils.enums.HostelLocationType;
import com.tapstaku.college.utils.enums.HostelStatus;
import com.tapstaku.college.utils.enums.HostelGenderType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hostel_tbl")
@Data
@ToString(exclude = "rooms")
public class Hostel {

    @Id
    private Long id;

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

    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private List<Room> rooms;

}
