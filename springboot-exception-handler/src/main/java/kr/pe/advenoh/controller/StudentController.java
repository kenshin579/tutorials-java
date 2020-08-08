package kr.pe.advenoh.controller;

import kr.pe.advenoh.model.dto.StudentRequestDto;
import kr.pe.advenoh.model.dto.StudentResponseDto;
import kr.pe.advenoh.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<?> addStudent(@ModelAttribute @Valid StudentRequestDto studentRequestDto) {
        StudentResponseDto studentResponseDto = studentService.addStudent(studentRequestDto);
        return new ResponseEntity<>(studentResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable(name = "studentId") Long studentId) {
        StudentResponseDto studentResponseDto = studentService.getStudent(studentId);
        return new ResponseEntity<>(studentResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getStudents() {
        List<StudentResponseDto> students = studentService.getStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable(name = "studentId") Long studentId) {
        Map<String, Object> result = new HashMap<>();
        result.put("succeed", true);
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
