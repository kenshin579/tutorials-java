package kr.pe.advenoh.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@ToString(exclude = "member")
@Entity
@Table(name = "DSL_HOME_ADDRESS")
public class DslHomeAddress {

    @Id
    private int id;

    private String address;

    @OneToOne(optional = false)
    @JoinColumn(name = "MEMBER_ID")
    private DslMember member;
}
