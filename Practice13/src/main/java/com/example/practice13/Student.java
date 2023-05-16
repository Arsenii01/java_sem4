package com.example.practice13;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class Student {
    @Value("${student.name}")
    private String name;
    @Value("${student.lastName}")
    private String lastName;
    @Value("${student.group}")
    private String group;

    @PreDestroy
    public void destroyMethod() {
        System.out.println(lastName + " " + name + ", group = " + group);
    }

    public Student() {}
}
