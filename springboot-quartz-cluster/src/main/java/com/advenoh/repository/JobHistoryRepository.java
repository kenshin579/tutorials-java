//package com.advenoh.repository;
//
//import com.advenoh.model.JobHistoryLog;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface JobHistoryRepository extends JpaRepository<JobHistoryLog, Long> {
//    Optional<JobHistoryLog> findById(Long aLong);
//
//    Optional<List<JobHistoryLog>> findAllByCreatedAtAfter(Date after);
//}
