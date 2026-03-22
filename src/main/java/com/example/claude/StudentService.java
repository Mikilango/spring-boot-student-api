package com.example.claude;

import com.example.claude.dto.*;
import com.example.claude.exception.StudentNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Конвертация Student → StudentResponseDTO
    private StudentResponseDTO toResponseDTO(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getCity()
        );
    }

    // Конвертация StudentRequestDTO → Student
    private Student toEntity(StudentRequestDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setCity(dto.getCity());
        return student;
    }

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return toResponseDTO(student);
    }

    public StudentResponseDTO createStudent(StudentRequestDTO dto) {
        Student student = toEntity(dto);
        Student saved = studentRepository.save(student);
        return toResponseDTO(saved);
    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setCity(dto.getCity());
        return toResponseDTO(studentRepository.save(student));
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id); // ✅
        }
        studentRepository.deleteById(id);
    }
}