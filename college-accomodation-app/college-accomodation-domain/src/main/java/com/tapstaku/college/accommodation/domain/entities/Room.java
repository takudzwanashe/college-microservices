package com.tapstaku.college.accommodation.domain.entities;

import com.tapstaku.college.utils.enums.RoomType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room_tbl")
@Data
@ToString(exclude = {"hostel","beds"})
public class Room {

    @Id
    private Long id;

    @Column(name = "room_number", length = 6)
    private String number;

    @Column(name = "number_of_beds", length = 2)
    private int numberOfBeds;

    @Column(name = "room_type", length = 20)
    private RoomType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hostel_id", nullable = false)
    private Hostel hostel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.ALL)
    @JoinColumn(name = "bed_id")
    private List<Bed> beds;

}
