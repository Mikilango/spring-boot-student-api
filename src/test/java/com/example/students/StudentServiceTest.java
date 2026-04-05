package com.example.students;

import com.example.students.dto.StudentRequestDTO;
import com.example.students.dto.StudentResponseDTO;
import com.example.students.entity.Student;
import com.example.students.exception.StudentNotFoundException;
import com.example.students.repository.StudentRepository;
import com.example.students.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;
    private StudentRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId(1L);
        student.setName("Мирлан");
        student.setAge(21);
        student.setCity("Бишкек");

        requestDTO = new StudentRequestDTO("Мирлан", 21, "Бишкек");
    }

    @Test
    void getAllStudents_shouldReturnListOfStudents() {
        when(studentRepository.findAll()).thenReturn(List.of(student));

        List<StudentResponseDTO> result = studentService.getAllStudents();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Мирлан");
        assertThat(result.get(0).getCity()).isEqualTo("Бишкек");
    }

    @Test
    void getStudentById_shouldReturnStudent_whenExists() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        StudentResponseDTO result = studentService.getStudentById(1L);

        assertThat(result.getName()).isEqualTo("Мирлан");
        assertThat(result.getCity()).isEqualTo("Бишкек");
    }

    @Test
    void getStudentById_shouldThrowException_whenNotExists() {
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> studentService.getStudentById(99L))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void createStudent_shouldReturnCreatedStudent() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentResponseDTO result = studentService.createStudent(requestDTO);

        assertThat(result.getName()).isEqualTo("Мирлан");
        assertThat(result.getCity()).isEqualTo("Бишкек");
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void deleteStudent_shouldDeleteSuccessfully_whenExists() {
        when(studentRepository.existsById(1L)).thenReturn(true);

        studentService.deleteStudent(1L);

        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteStudent_shouldThrowException_whenNotExists() {
        when(studentRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> studentService.deleteStudent(99L))
                .isInstanceOf(StudentNotFoundException.class);
    }
}