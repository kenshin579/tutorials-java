package com.advenoh.model;

import com.advenoh.dto.JobStatusResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "job_status_log")
public class JobStatusLog {
    @Id
    @Column(name = "job_status_seqno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Instant createdAt;

    private String jobStatus;

    @ManyToOne
    @JoinColumn(name = "job_history_seqno")
    private JobHistoryLog jobHistoryLog;

    public JobStatusLog(String jobStatus, Instant createdAt) {
        this.jobStatus = jobStatus;
        this.createdAt = createdAt;
    }

    //연관관계 설정
    public void setJobHistoryLog(JobHistoryLog jobHistoryLog) {
        if (this.jobHistoryLog != null) {
            this.jobHistoryLog.getJobStatusLogList().remove(this);
        }
        this.jobHistoryLog = jobHistoryLog;
        jobHistoryLog.getJobStatusLogList().add(this);
    }
}
