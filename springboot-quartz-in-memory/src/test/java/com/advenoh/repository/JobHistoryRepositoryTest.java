package com.advenoh.repository;

import com.advenoh.model.JobHistoryLog;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JobHistoryRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private JobHistoryRepository jobHistoryRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        JobHistoryLog jobHistoryLog = new JobHistoryLog(2L, "job1", "group1", "simple");
        entityManager.persistAndFlush(jobHistoryLog);

        Optional<JobHistoryLog> found = jobHistoryRepository.findById(2L);
        log.info("{}", found);

//        Employee alex = new Employee("alex");
//        entityManager.persistAndFlush(alex);
//
//        Employee found = employeeRepository.findByName(alex.getName());
//        assertThat(found.getName()).isEqualTo(alex.getName());
    }
}