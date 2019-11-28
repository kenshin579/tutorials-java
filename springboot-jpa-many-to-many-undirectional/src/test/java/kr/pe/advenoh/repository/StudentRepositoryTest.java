package kr.pe.advenoh.repository;

import kr.pe.advenoh.model.Course;
import kr.pe.advenoh.model.Student;
import kr.pe.advenoh.model.enums.Gender;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {
//    @Autowired
//    private TestEntityManager testEntityManager;

    @Autowired
    private EntityManager em;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void 단방양_save() {
        Course course1 = new Course();
        course1.setCourseName("C++");
        em.persist(course1);

        Student student1 = new Student();
        student1.setName("Frank");
        student1.setGender(Gender.MALE);
        student1.getCourses().add(course1); //연관관계 설정
        em.persist(student1);
    }
}