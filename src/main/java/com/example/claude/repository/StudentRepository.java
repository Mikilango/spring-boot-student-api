package com.example.claude.repository;

import com.example.claude.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByCity(String city);

    @Query("SELECT s FROM Student s WHERE s.city = :city")
    List<Student> findByCityJpql(@Param("city") String city);

    @Query(value = "SELECT * FROM students WHERE city = :city",
            nativeQuery = true)
    List<Student> findByCityNative(@Param("city") String city);

    @Query("SELECT s FROM Student s WHERE s.name LIKE %:name%")
    List<Student> findByNameContaining(@Param("name") String name);

    @Query("SELECT s FROM Student s WHERE s.age > :age ORDER BY s.age ASC")
    List<Student> findByAgeGreaterThan(@Param("age") int age);

    @Query("SELECT COUNT(s) FROM Student s WHERE s.city = :city")
    Long countByCity(@Param("city") String city);
}