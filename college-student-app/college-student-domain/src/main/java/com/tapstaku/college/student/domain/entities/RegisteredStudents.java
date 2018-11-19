package com.tapstaku.college.student.domain.entities;


/**
 * Created by Shoshore on 19/11/18
 */

import com.tapstaku.college.utils.enums.EntityStatus;
import com.tapstaku.college.utils.keygen.KeyGen;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;



@Data
@ToString
@Entity
@Table(name = "registeredStudent", indexes = {@Index(name = "student_unique_index", columnList = "regNumber", unique = true),
@Index(name = "student_status_index", columnList = "status")})

public class RegisteredStudents {


    @Id
    private Long id;

    @Column(name = "regNumber", length = 14)
    private String regNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private EntityStatus status;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_last_updated")
    private Date dateLastUpdated;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;


    @PrePersist
    public void init() {
        if(id == null) {
            id = KeyGen.getUniqueId();
        }

        if(status == null) {
            status = EntityStatus.ACTIVE;
        }

        if(creationDate == null) {
            creationDate = new Date();
        }
    }
    @PreUpdate
    public void reload() {
        dateLastUpdated = new Date();
    }



}
