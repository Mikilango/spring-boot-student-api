package com.example.students.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long id) {
        super("Студент с ID " + id + " не найден.");
    }
}
