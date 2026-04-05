package com.example.students.service;

import com.example.students.mapper.StudentMapper;
import com.example.students.repository.StudentRepository;
import com.example.students.dto.*;
import com.example.students.entity.Student;
import com.example.students.exception.StudentNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository,
                          StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponseDTO) // ← MapStruct!
                .collect(Collectors.toList());
    }

    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return studentMapper.toResponseDTO(student); // ← MapStruct!
    }

    public StudentResponseDTO createStudent(StudentRequestDTO dto) {
        Student student = studentMapper.toEntity(dto); // ← MapStruct!
        return studentMapper.toResponseDTO(studentRepository.save(student));
    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        studentMapper.updateEntity(dto, student); // ← MapStruct!
        return studentMapper.toResponseDTO(studentRepository.save(student));
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }

    public Page<StudentResponseDTO> getAllStudentsPaged(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return studentRepository.findAll(pageable)
                .map(studentMapper::toResponseDTO); // ← MapStruct!
    }

    public List<StudentResponseDTO> getStudentsByCity(String city) {
        return studentRepository.findByCity(city)
                .stream()
                .map(studentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<StudentResponseDTO> getStudentsByName(String name) {
        return studentRepository.findByNameContaining(name)
                .stream()
                .map(studentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<StudentResponseDTO> getStudentsOlderThan(int age) {
        return studentRepository.findByAgeGreaterThan(age)
                .stream()
                .map(studentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Long countStudentsByCity(String city) {
        return studentRepository.countByCity(city);
    }
}