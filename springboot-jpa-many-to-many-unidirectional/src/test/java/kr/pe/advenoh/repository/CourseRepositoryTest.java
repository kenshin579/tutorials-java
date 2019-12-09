package kr.pe.advenoh.repository;

import kr.pe.advenoh.config.H2TestJPAConfig;
import kr.pe.advenoh.model.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { H2TestJPAConfig.class })
//@DataJpaTest
//@ContextConfiguration(classes = H2TestJPAConfig.class)
@ActiveProfiles("test")
public class CourseRepositoryTest {
	@Autowired
	private CourseRepository courseRepository;

	@Test
	public void test_save_get() {
		Course course = courseRepository.save(new Course("course1", "101"));
		Course foundCourse = courseRepository.findById(course.getCourseId()).orElse(null);

		assertThat(foundCourse).isNotNull();
		assertThat(course.getLocation()).isEqualTo(foundCourse.getLocation());
	}
}