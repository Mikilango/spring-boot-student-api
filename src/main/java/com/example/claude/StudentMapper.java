package com.example.claude;

import com.example.claude.dto.StudentRequestDTO;
import com.example.claude.dto.StudentResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    // Student → StudentResponseDTO
    StudentResponseDTO toResponseDTO(Student student);

    // StudentRequestDTO → Student
    Student toEntity(StudentRequestDTO dto);

    // Обновить существующий Student из DTO
    void updateEntity(StudentRequestDTO dto, @MappingTarget Student student);
}