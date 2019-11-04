package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.JobHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {
    Page<JobHistory> findAll(Pageable pageable);
//    Optional<> findAllByCreatedAtAfter(Date after);
    Optional<List<JobHistory>> findJobHistoryByJobNameAndJobGroup(String jobName, String jobGroup);
    Optional<JobHistory> findFirstByJobNameAndJobGroupOrderByHistoryIdDesc(String jobName, String jobGroup);

}
