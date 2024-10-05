package com.jdbc.app;

import com.jdbc.app.persistence.EmployeeDAO;
import com.jdbc.app.persistence.entity.EmployeeEntity;
import org.flywaydb.core.Flyway;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

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

//        System.out.println(employeeDAO.findById(2L));

        var employee = new EmployeeEntity();
        employee.setId(2);
        employee.setName("Madalena Samuel");
        employee.setSalary(new BigDecimal("589.00"));
        employee.setBirthday(OffsetDateTime.now().minusDays(9));

        employeeDAO.update(employee);

    }
}