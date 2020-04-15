package kr.pe.advenoh.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "DSL_MEMBER")
public class DslMember {

    @Id
    @Column(name = "MEMBER_ID")
    private int id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.PERSIST, optional = false)
    private DslHomeAddress homeAddress;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.PERSIST)
    private List<DslPhone> phoneList = new ArrayList<>();

    public void setHomeAddress(DslHomeAddress homeAddress) {
        this.homeAddress = homeAddress;
        homeAddress.setMember(this);
    }

    public void addPhone(DslPhone phone) {
        phoneList.add(phone);
        phone.setMember(this);
    }
}