package com.example.claude;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Привет, Spring Boot!";
    }

    @GetMapping("/info")
    public String info() {
        return "Меня зовут Мирлан, я изучаю Spring Boot!";
    }

    @GetMapping("/status")
    public String status() {
        return "Сервер работает отлично!";
    }

}