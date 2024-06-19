package com.iniflex;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee extends Person {
    private BigDecimal salary;
    private String role;

    public Employee(String name, LocalDate birthDate, BigDecimal salary, String role) {
        super(name, birthDate);
        this.salary = salary;
        this.role = role;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", getName(), getBirthDate().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                salary.setScale(2, BigDecimal.ROUND_HALF_UP).toString().replace('.', ',').replaceAll("(\\d)(?=(\\d{3})+\\,)", "$1."),
                role);
    }
}
