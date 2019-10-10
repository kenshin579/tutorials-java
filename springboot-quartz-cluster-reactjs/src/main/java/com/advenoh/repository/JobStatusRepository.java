package com.advenoh.repository;

import com.advenoh.model.JobHistory;
import com.advenoh.model.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobStatusRepository extends JpaRepository<JobStatus, Long> {
}
