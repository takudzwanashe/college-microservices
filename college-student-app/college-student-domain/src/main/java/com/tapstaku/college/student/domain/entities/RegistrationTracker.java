package com.tapstaku.college.student.domain.entities;


import com.tapstaku.college.utils.enums.EntityStatus;
import com.tapstaku.college.utils.keygen.KeyGen;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Shoshore on 19/11/18
 */

@Entity
@NamedQueries(value = @NamedQuery(name = "RegistrationTracker.findTrackerByStudentId", query = "select r from RegistrationTracker r where r.student_id =: student_id" ))
@Table(name = "registration_status", indexes = {@Index(name = "reg_tracker_status", columnList = "status")})


public class RegistrationTracker {

    @Id
    private Long id;

    @Column(name = "city", length = 40)
    private String city;

    @Column(name = "suburb", length = 40)
    private String suburb;

    @Column(name = "house_number", length = 40)
    private String houseNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30, nullable = false)
    private EntityStatus status;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_last_updated")
    private Date dateLastUpdated;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Student student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public EntityStatus getStatus() {
        return status;
    }

    public void setStatus(EntityStatus status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDateLastUpdated() {
        return dateLastUpdated;
    }

    public void setDateLastUpdated(Date dateLastUpdated) {
        this.dateLastUpdated = dateLastUpdated;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @PrePersist
    public void init(){
        if (id == null) {
            id = KeyGen.getUniqueId();
        }

        if (status == null) {
            status = EntityStatus.ACTIVE;
        }

        if (creationDate == null) {
            creationDate = new Date();
        }
    }
    @PreUpdate
    public void reload() {
        dateLastUpdated = new Date();
    }
}
