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
//        employee.setName("Fé Mourinho");
//        employee.setSalary(new BigDecimal("677.390"));
//        employee.setBirthday(OffsetDateTime.now().minusYears(10).minusMonths(6).minusDays(6));
//
//        employeeDAO.insert(employee);

//        employeeDAO.findAll().forEach(System.out::println);

//        System.out.println(employeeDAO.findById(2L));

//        var employee = new EmployeeEntity();
//        employee.setId(2);
//        employee.setName("Madalena Samuel");
//        employee.setSalary(new BigDecimal("589.00"));
//        employee.setBirthday(OffsetDateTime.now().minusDays(9));
//
//        employeeDAO.update(employee);

        employeeDAO.delete(2L);

    }
}