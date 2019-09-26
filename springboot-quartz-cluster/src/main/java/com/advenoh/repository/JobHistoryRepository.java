package com.advenoh.repository;

import com.advenoh.model.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {
//    Page<JobLog> findById(Long jobId, Pageable pageable);
//    Optional<> findAllByCreatedAtAfter(Date after);
}
