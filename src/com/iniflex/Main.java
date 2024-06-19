package com.iniflex;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        BigDecimal minimumWage = new BigDecimal("1212.00");

        // 3.1 - Inserir todos os funcionários, na mesma ordem e informações da tabela.
        employees.add(new Employee("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        employees.add(new Employee("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        employees.add(new Employee("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        employees.add(new Employee("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19199.88"), "Diretor"));
        employees.add(new Employee("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        employees.add(new Employee("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        employees.add(new Employee("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        employees.add(new Employee("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        employees.add(new Employee("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        employees.add(new Employee("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 - Remover o funcionário “João” da lista.
        employees.removeIf(e -> e.getName().equalsIgnoreCase("João"));

        // 3.3 - Imprimir todos os funcionários com todas suas informações.
        System.out.println("Lista de Funcionários:");
        employees.forEach(System.out::println);

        // 3.4 - Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        employees.forEach(e -> e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.1))));

        // 3.5 - Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String, List<Employee>> employeesByRole = employees.stream().collect(Collectors.groupingBy(Employee::getRole));

        // 3.6 - Imprimir os funcionários, agrupados por função.
        System.out.println("\nFuncionários agrupados por função:");
        employeesByRole.forEach((role, list) -> {
            System.out.println(role + ":");
            list.forEach(e -> System.out.println("\t" + e));
        });

        // 3.8 - Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("\nFuncionários que fazem aniversário nos meses 10 e 12:");
        employees.stream()
                .filter(e -> e.getBirthDate().getMonthValue() == 10 || e.getBirthDate().getMonthValue() == 12)
                .forEach(System.out::println);

        // 3.9 - Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        Employee oldest = employees.stream().min(Comparator.comparing(Employee::getBirthDate)).orElse(null);
        if (oldest != null) {
            int age = Period.between(oldest.getBirthDate(), LocalDate.now()).getYears();
            System.out.printf("\nFuncionário com maior idade: %s, Idade: %d anos\n", oldest.getName(), age);
        }

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética.
        System.out.println("\nLista de Funcionários em ordem alfabética:");
        employees.stream().sorted(Comparator.comparing(Employee::getName)).forEach(System.out::println);

        // 3.11 – Imprimir o total dos salários dos funcionários.
        BigDecimal totalSalaries = employees.stream().map(Employee::getSalary).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("\nTotal dos salários dos funcionários: %s\n", totalSalaries.setScale(2, BigDecimal.ROUND_HALF_UP).toString().replace('.', ',').replaceAll("(\\d)(?=(\\d{3})+\\,)", "$1."));

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        System.out.println("\nQuantidade de salários mínimos por funcionário:");
        employees.forEach(e -> {
            BigDecimal minimumWages = e.getSalary().divide(minimumWage, 2, BigDecimal.ROUND_HALF_UP);
            System.out.printf("%s: %s salários mínimos\n", e.getName(), minimumWages.toString().replace('.', ','));
        });
    }
}
