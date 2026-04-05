package com.example.students.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDTO {

    @NotBlank(message = "Имя не может быть пустым!")
    @Size(min = 2, max = 50, message = "Имя должно быть от 2 до 50 символов!")
    private String name;

    @Min(value = 1, message = "Возраст не может быть меньше 1!")
    @Max(value = 100, message = "Возраст не может быть больше 100!")
    private int age;

    @NotBlank(message = "Город не может быть пустым!")
    private String city;
}