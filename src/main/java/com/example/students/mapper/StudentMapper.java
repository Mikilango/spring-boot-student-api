package com.example.students.mapper;

import com.example.students.dto.StudentRequestDTO;
import com.example.students.dto.StudentResponseDTO;
import com.example.students.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentResponseDTO toResponseDTO(Student student);

    Student toEntity(StudentRequestDTO dto);

    void updateEntity(StudentRequestDTO dto, @MappingTarget Student student);
}