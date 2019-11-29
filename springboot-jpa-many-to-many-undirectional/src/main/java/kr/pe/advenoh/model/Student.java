package kr.pe.advenoh.model;

import kr.pe.advenoh.model.audit.DateAudit;
import kr.pe.advenoh.model.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "student")
public class Student extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    private String name;

    private Date birthDay;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phone;

    @ManyToMany
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses = new ArrayList<>();

    public Student(String name, Date birthDay, Gender gender, String phone) {
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.phone = phone;
    }
}