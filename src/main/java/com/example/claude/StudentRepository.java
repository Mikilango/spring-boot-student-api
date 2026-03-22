package com.example.claude;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Spring сам реализует все методы!
    // findAll(), findById(), save(), deleteById() — уже готовы!
}