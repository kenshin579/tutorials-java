package kr.pe.advenoh.controller;

import kr.pe.advenoh.model.Course;
import kr.pe.advenoh.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@Slf4j
@RestController
@RequestMapping("/api/course")
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;

	@GetMapping
	public Iterable<Course> getList() {
		return courseRepository.findAll();
	}

	@Transactional
	@PostMapping
	public Course addCourse(Course course) {
		return courseRepository.save(course);
	}

	@Transactional
	@PutMapping("/{id}")
	public Course modifyCourse(Course course) {
		return courseRepository.save(course);
	}

	@Transactional
	@DeleteMapping("/{id}")
	public String deleteCourse(@PathVariable Long id) {
		Course course = new Course();
		course.setCourseId(id);
		courseRepository.delete(course);
		return "SUCCESS";
	}
}
