package com.advenoh.repository;

import com.advenoh.model.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {
//    Page<JobLog> findById(Long jobId, Pageable pageable);
//    Optional<> findAllByCreatedAtAfter(Date after);
    Optional<List<JobHistory>> findJobHistoryByJobNameAndJobGroup(String jobName, String jobGroup);
    Optional<JobHistory> findFirstByJobNameAndJobGroupOrderByHistoryIdDesc(String jobName, String jobGroup);


}
