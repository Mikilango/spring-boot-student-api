package com.example.claude;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // 1️⃣ Spring Data метод — сам генерирует запрос по названию!
    List<Student> findByCity(String city);

    // 2️⃣ JPQL — свой запрос через @Query
    @Query("SELECT s FROM Student s WHERE s.city = :city")
    List<Student> findByCityJpql(@Param("city") String city);

    // 3️⃣ Нативный SQL — обычный SQL запрос
    @Query(value = "SELECT * FROM students WHERE city = :city",
            nativeQuery = true)
    List<Student> findByCityNative(@Param("city") String city);

    // 4️⃣ Найти по имени содержащему строку (LIKE)
    @Query("SELECT s FROM Student s WHERE s.name LIKE %:name%")
    List<Student> findByNameContaining(@Param("name") String name);

    // 5️⃣ Найти студентов старше определённого возраста
    @Query("SELECT s FROM Student s WHERE s.age > :age ORDER BY s.age ASC")
    List<Student> findByAgeGreaterThan(@Param("age") int age);

    // 6️⃣ Подсчитать студентов по городу
    @Query("SELECT COUNT(s) FROM Student s WHERE s.city = :city")
    Long countByCity(@Param("city") String city);
}