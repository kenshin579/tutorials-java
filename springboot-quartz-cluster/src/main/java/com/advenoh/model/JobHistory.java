package com.advenoh.model;

import kr.pe.advenoh.model.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Entity
@Table(name = "job_history")
public class JobHistory extends DateAudit {

    @Id
    @Column(name = "history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @NotNull
    @Column(name = "job_name", length = 50)
    private String jobName;

    @NotNull
    @Column(name = "job_group", length = 50)
    private String jobGroup;

    @NotNull
    @Column(name = "job_type", length = 50)
    @Enumerated(value = EnumType.STRING)
    private JobType jobType;
}
