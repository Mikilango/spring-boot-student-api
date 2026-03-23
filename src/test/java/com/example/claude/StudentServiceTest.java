package com.example.claude;

import com.example.claude.dto.StudentRequestDTO;
import com.example.claude.dto.StudentResponseDTO;
import com.example.claude.exception.StudentNotFoundException;
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
    private StudentRepository studentRepository; // фейковый репозиторий

    @InjectMocks
    private StudentService studentService; // настоящий сервис

    private Student student;
    private StudentRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        // Подготовка данных перед каждым тестом
        student = new Student();
        student.setId(1L);
        student.setName("Мирлан");
        student.setAge(21);
        student.setCity("Бишкек");

        requestDTO = new StudentRequestDTO("Мирлан", 21, "Бишкек");
    }

    // ✅ Тест 1 — получить всех студентов
    @Test
    void getAllStudents_shouldReturnListOfStudents() {
        // ARRANGE
        when(studentRepository.findAll()).thenReturn(List.of(student));

        // ACT
        List<StudentResponseDTO> result = studentService.getAllStudents();

        // ASSERT
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Мирлан");
        assertThat(result.get(0).getCity()).isEqualTo("Бишкек");
    }

    // ✅ Тест 2 — получить студента по id
    @Test
    void getStudentById_shouldReturnStudent_whenExists() {
        // ARRANGE
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        // ACT
        StudentResponseDTO result = studentService.getStudentById(1L);

        // ASSERT
        assertThat(result.getName()).isEqualTo("Мирлан");
        assertThat(result.getCity()).isEqualTo("Бишкек");
    }

    // ✅ Тест 3 — студент не найден
    @Test
    void getStudentById_shouldThrowException_whenNotExists() {
        // ARRANGE
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        // ASSERT + ACT
        assertThatThrownBy(() -> studentService.getStudentById(99L))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("99");
    }

    // ✅ Тест 4 — создать студента
    @Test
    void createStudent_shouldReturnCreatedStudent() {
        // ARRANGE
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // ACT
        StudentResponseDTO result = studentService.createStudent(requestDTO);

        // ASSERT
        assertThat(result.getName()).isEqualTo("Мирлан");
        assertThat(result.getCity()).isEqualTo("Бишкек");
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    // ✅ Тест 5 — удалить студента
    @Test
    void deleteStudent_shouldDeleteSuccessfully_whenExists() {
        // ARRANGE
        when(studentRepository.existsById(1L)).thenReturn(true);

        // ACT
        studentService.deleteStudent(1L);

        // ASSERT
        verify(studentRepository, times(1)).deleteById(1L);
    }

    // ✅ Тест 6 — удалить несуществующего студента
    @Test
    void deleteStudent_shouldThrowException_whenNotExists() {
        // ARRANGE
        when(studentRepository.existsById(99L)).thenReturn(false);

        // ASSERT + ACT
        assertThatThrownBy(() -> studentService.deleteStudent(99L))
                .isInstanceOf(StudentNotFoundException.class);
    }
}