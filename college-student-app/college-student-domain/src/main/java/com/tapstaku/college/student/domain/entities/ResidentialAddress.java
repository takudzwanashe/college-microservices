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
@Table(name = "residential_address", indexes = {@Index(name = "student_status_index", columnList = "status")})
public class ResidentialAddress {

    @Id
    private Long id;

    @Column(name = "address", length = 80)
    private String address;

    private Long suburbId;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30, nullable = false)
    private EntityStatus status;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_last_updated")
    private Date dateLastUpdated;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;


    @PrePersist
    public void init(){
        if (id == null){
            id = KeyGen.getUniqueId();
        }
        if (status == null){
            status = EntityStatus.ACTIVE;
        }
        if (creationDate == null){
            creationDate = new Date();
        }

    }
    @PreUpdate
    public void reload(){
        dateLastUpdated = new Date();
    }






}
