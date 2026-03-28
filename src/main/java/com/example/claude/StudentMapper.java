package com.example.claude;

import com.example.claude.dto.StudentRequestDTO;
import com.example.claude.dto.StudentResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentResponseDTO toResponseDTO(Student student);

    Student toEntity(StudentRequestDTO dto);

    void updateEntity(StudentRequestDTO dto, @MappingTarget Student student);
}