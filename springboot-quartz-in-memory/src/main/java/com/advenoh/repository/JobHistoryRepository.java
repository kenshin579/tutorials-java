package com.advenoh.repository;

import com.advenoh.model.JobHistoryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistoryLog, Long> {

}
