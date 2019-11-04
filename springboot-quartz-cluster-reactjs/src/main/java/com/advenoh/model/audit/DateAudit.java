package com.advenoh.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public abstract class DateAudit implements Serializable {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_dt", nullable = false, updatable = false)
    @CreatedDate
    private Date createDt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_dt", nullable = false)
    @LastModifiedDate
    private Date updateDt;
}