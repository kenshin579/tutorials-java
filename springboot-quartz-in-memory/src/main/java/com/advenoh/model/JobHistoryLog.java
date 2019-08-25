package com.advenoh.model;

import com.advenoh.model.audit.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Getter
@Setter
@ToString
@Entity
@Table(name = "job_history_log")
public class JobHistoryLog extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String jobName;

    @Column(length = 50)
    private String jobGroup;

    //todo: enum 은 jpa에서 어떻게 저장하나?
    private JobType jobType; //cron, simple

//    @OneToMany(
//            mappedBy = "job_history_log",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER,
//            orphanRemoval = true
//    )
//    private List<JobStatusLog> jobStatusLogList;

    public JobHistoryLog(String jobName, String jobGroup, JobType jobType, Instant createdAt, Instant updateAt) {
        super(createdAt, updateAt);
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.jobType = jobType;
    }
}
