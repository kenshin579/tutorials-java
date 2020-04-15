package kr.pe.advenoh.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@ToString(exclude = "member")
@Entity
@Table(name = "DSL_PHONE")
public class DslPhone {

    @Id
    private int id;

    private String number;

    private String manufacture;

    public DslPhone(int id, String number, String manufacture) {
        this.id = id;
        this.number = number;
        this.manufacture = manufacture;
    }

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private DslMember member;
}