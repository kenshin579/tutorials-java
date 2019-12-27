package kr.pe.advenoh.model;

import kr.pe.advenoh.model.audit.DateAudit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "cellular_phone")
public class CellularPhone extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    private Long id;

    private String phoneNumber;

    private String model;

    //일대일관계에서 외래 키를 직접 관리하지 않는 지연로딩으로 설정해도 즉시로딩으로만 동작한다 (프록시의 한계)
    @OneToOne(mappedBy = "cellularPhone", fetch = FetchType.LAZY)
    private User user;

    @Builder
    public CellularPhone(String phoneNumber, String model) {
        this.phoneNumber = phoneNumber;
        this.model = model;
    }
}