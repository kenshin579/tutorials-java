package com.advenoh.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
//@Transactional
//@TestPropertySource(locations = "classpath:application.properties")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JobHistoryLogRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private JobHistoryRepository jobHistoryRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
//        JobHistoryLog jobHistoryLog = new JobHistoryLog(2L, "job1", "group1", JobType.SIMPLE);
//        entityManager.persist(jobHistoryLog);
//
//        Optional<JobHistoryLog> found = jobHistoryRepository.findById(2L);
//        log.info("{}", found);

//        Employee alex = new Employee("alex");
//        entityManager.persistAndFlush(alex);
//
//        Employee found = employeeRepository.findByName(alex.getName());
//        assertThat(found.getName()).isEqualTo(alex.getName());
    }
}