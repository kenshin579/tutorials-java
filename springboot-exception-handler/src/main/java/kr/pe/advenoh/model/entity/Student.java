package kr.pe.advenoh.model.entity;

import kr.pe.advenoh.model.audit.DateAudit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Student extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", unique = true, nullable = false)
    private Long id;

    private String name;
    private String mobileNumber;

    private String address;

    @Builder
    public Student(String name, String mobileNumber, String address) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }
}
