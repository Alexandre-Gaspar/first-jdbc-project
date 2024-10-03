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

        EmployeeEntity employee = new EmployeeEntity();
        employee.setName("Alexandre Gaspar");
        employee.setSalary(new BigDecimal("789.989"));
        employee.setBirthday(OffsetDateTime.now().minusYears(23));

        employeeDAO.insert(employee);

    }
}