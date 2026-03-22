package com.example.claude.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDTO {
    private String name;
    private int age;
    private String city;
}
