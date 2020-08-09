package kr.pe.advenoh.service;

import kr.pe.advenoh.exception.ApiException;
import kr.pe.advenoh.exception.StudentExceptionCode;
import kr.pe.advenoh.model.dto.StudentRequestDto;
import kr.pe.advenoh.model.dto.StudentResponseDto;
import kr.pe.advenoh.model.entity.Student;
import kr.pe.advenoh.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public StudentResponseDto addStudent(StudentRequestDto studentRequestDto) {
        Student student = Student.builder()
                .name(studentRequestDto.getName())
                .mobileNumber(studentRequestDto.getMobileNumber())
                .address(studentRequestDto.getAddress())
                .build();

        return modelMapper.map(studentRepository.save(student), StudentResponseDto.class);
    }

    @Transactional(readOnly = true)
    public StudentResponseDto getStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, StudentExceptionCode.STUDENT_NOT_FOUND));
        return modelMapper.map(student, StudentResponseDto.class);
    }

    @Transactional(readOnly = true)
    public List<StudentResponseDto> getStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponseDto> result = students.stream()
                .map(student -> modelMapper.map(student, StudentResponseDto.class))
                .collect(Collectors.toList());

        return result;
    }

    @Transactional
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

}
