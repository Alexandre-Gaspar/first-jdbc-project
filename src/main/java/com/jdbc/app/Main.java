package com.jdbc.app;

import com.jdbc.app.persistence.EmployeeDAO;
import org.flywaydb.core.Flyway;

public class Main {

    private static final EmployeeDAO employeeDAO = new EmployeeDAO();

    public static void main(String[] args) {

        Flyway flyway = Flyway.configure()
                .dataSource(
                        "jdbc:postgresql://localhost:5432/sample-jdbc",
                        "postgres",
                        "postgres"
                ).load();

        flyway.migrate();

//        EmployeeEntity employee = new EmployeeEntity();
//        employee.setName("Madalena Cassua");
//        employee.setSalary(new BigDecimal("380"));
//        employee.setBirthday(OffsetDateTime.now().minusYears(18).minusMonths(1).minusDays(7));
//
//        employeeDAO.insert(employee);

//        employeeDAO.findAll().forEach(System.out::println);

        System.out.println(employeeDAO.findById(2L));

    }
}