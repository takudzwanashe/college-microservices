package com.tapstaku.college.accommodation.domain.entities;

import com.tapstaku.college.utils.enums.BedType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by taku on 11/14/18
 */

@Entity
@Table(name = "bed_tbl")
@Data
@ToString(exclude = "room")
public class Bed {

    @Id
    private Long id;

    @Column(name = "bed_type", length = 12, nullable = false)
    @Enumerated(EnumType.STRING)
    private BedType type;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hostel_id", nullable = false)
    private Hostel hostel;
}
