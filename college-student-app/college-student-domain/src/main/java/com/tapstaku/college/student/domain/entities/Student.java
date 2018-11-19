package com.tapstaku.college.student.domain.entities;

/**
 * Created by Shoshore on 19/11/18
 */

import com.tapstaku.college.utils.enums.RegistrationStatus;
import com.tapstaku.college.utils.keygen.KeyGen;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@EqualsAndHashCode(of = "id")
@ToString(of = {"firstName", "idNumber"})
@Entity
@NamedQueries({@NamedQuery(name = "Student.findByIdNumber", query = "select c from Student c where c.idNumber = :idNumber")})
@Table(name = "student_details", indexes = {@Index(name = "student_unique_index", columnList = "id_type,id_number", unique = true),
@Index(name = "student_first_name_index", columnList = "firstName"),
@Index(name = "student_registration_status_index", columnList = "reg_status"),
@Index(name = "student_creation_index", columnList = "creation_date")})
public class Student implements Serializable {

    public Student() {
        super();
    }

    public Student(Long id, String firstName, String lastName, String idType, String gender, Date dateOfBirth,
                   String language, String idNumber, RegistrationStatus regStatus){
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setIdType(idType);
        this.setGender(gender);
        this.setDateOfBirth(dateOfBirth);
        this.setLanguage(language);
        this.setIdNumber(idNumber);
        this.setRegStatus(regStatus);
    }


    @Id
    private Long id;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(name = "id_number", length = 20)
    private String idNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth", length = 15)
    private Date dateOfBirth;

    @Column(name = "gender", length = 7)
    private String gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "reg_date")
    private Date regDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "reg_status", nullable = false)
    private RegistrationStatus regStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "language", length = 10)
    private String language;

    @Column(name = "id_type", length = 15)
    private String idType;

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                '}';
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_last_updated")
    private Date dateLastUpdated;

    @OneToMany(mappedBy = "student")
    private List<RegistrationTracker> registrationTrackers;

    @OneToMany(mappedBy = "student")
    private List<ResidentialAddress> residentialAddresses;

    @OneToMany(mappedBy = "student")
    private List<RegisteredStudents> registeredStudents;


    @PrePersist
    public void init() {
        if (getId() == null) {
            setId(KeyGen.getUniqueId());
        }
        if (getCreationDate() == null) {
            setCreationDate(new Date());
        }
    }

    @PreUpdate
    public void reload() {
        setDateLastUpdated(new Date());
    }


}
