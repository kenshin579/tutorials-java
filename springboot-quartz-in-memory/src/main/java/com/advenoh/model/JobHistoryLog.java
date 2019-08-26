package com.advenoh.model;

import com.advenoh.model.audit.DateAudit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "job_history_log")
public class JobHistoryLog extends DateAudit {

    @Id
    @Column(name = "job_history_seqno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String jobName;

    @Column(length = 50)
    private String jobGroup;

    @Enumerated(value = EnumType.STRING)
    private JobType jobType;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "jobHistoryLog")
    private List<JobStatusLog> jobStatusLogList = new ArrayList<>();

    public JobHistoryLog(String jobName, String jobGroup, JobType jobType, Instant createdAt, Instant updateAt) {
        super(createdAt, updateAt);
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.jobType = jobType;
    }
}
