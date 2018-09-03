package com.slyone.java8;

import java.math.BigDecimal;

public class Developer {
    private final String name;
    private final BigDecimal salary;
    private final int age;

    Developer(String name, BigDecimal salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }
}
