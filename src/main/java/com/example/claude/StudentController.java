package com.example.claude;

import com.example.claude.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
@Tag(name = "Students", description = "API для управления студентами")
@SecurityRequirement(name = "bearerAuth")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @Operation(summary = "Получить студента по ID")
    @GetMapping("/{id}")
    public StudentResponseDTO getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @Operation(summary = "Создать нового студента")
    @PostMapping
    public StudentResponseDTO createStudent(@Valid @RequestBody StudentRequestDTO dto) {
        return studentService.createStudent(dto);
    }

    @Operation(summary = "Обновить студента")
    @PutMapping("/{id}")
    public StudentResponseDTO updateStudent(@PathVariable Long id,
                                            @Valid @RequestBody StudentRequestDTO dto) {
        return studentService.updateStudent(id, dto);
    }

    @Operation(summary = "Удалить студента")
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @Operation(summary = "Получить студентов постранично")
    @GetMapping("/paged")
    public Page<StudentResponseDTO> getAllStudentsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return studentService.getAllStudentsPaged(page, size, sortBy);
    }

    // GET /students/city/Бишкек
    @GetMapping("/city/{city}")
    @Operation(summary = "Найти студентов по городу")
    public List<StudentResponseDTO> getStudentsByCity(@PathVariable String city) {
        return studentService.getStudentsByCity(city);
    }

    // GET /students/search?name=Мир
    @GetMapping("/search")
    @Operation(summary = "Поиск студентов по имени")
    public List<StudentResponseDTO> searchStudents(@RequestParam String name) {
        return studentService.getStudentsByName(name);
    }

    // GET /students/older-than?age=20
    @GetMapping("/older-than")
    @Operation(summary = "Найти студентов старше возраста")
    public List<StudentResponseDTO> getStudentsOlderThan(@RequestParam int age) {
        return studentService.getStudentsOlderThan(age);
    }

    // GET /students/count/Бишкек
    @GetMapping("/count/{city}")
    @Operation(summary = "Подсчитать студентов по городу")
    public Long countStudentsByCity(@PathVariable String city) {
        return studentService.countStudentsByCity(city);
    }
}