package com.advenoh.repository;

import com.advenoh.model.JobHistoryLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JobHistoryRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private JobHistoryRepository jobHistoryRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        JobHistoryLog jobHistoryLog = new JobHistoryLog();

//        Employee alex = new Employee("alex");
//        entityManager.persistAndFlush(alex);
//
//        Employee found = employeeRepository.findByName(alex.getName());
//        assertThat(found.getName()).isEqualTo(alex.getName());
    }
}