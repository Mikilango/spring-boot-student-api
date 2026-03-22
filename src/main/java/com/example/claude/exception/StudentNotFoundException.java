package com.example.claude.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long id) {
        super("Студент с ID " + id + " не найден.");
    }
}
