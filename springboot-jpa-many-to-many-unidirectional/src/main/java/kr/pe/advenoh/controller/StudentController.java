package kr.pe.advenoh.controller;

import kr.pe.advenoh.model.Student;
import kr.pe.advenoh.repository.StudentRepository;
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
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public Iterable<Student> getList() {
        return studentRepository.findAll();
    }

    @Transactional
    @PostMapping
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    @PutMapping("/{id}")
    public Student modifyStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        Student Student = new Student();
        Student.setStudentId(id);
        studentRepository.delete(Student);
        return "SUCCESS";
    }
}
