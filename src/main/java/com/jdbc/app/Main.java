package com.jdbc.app;

import com.jdbc.app.persistence.EmployeeDAO;
import org.flywaydb.core.Flyway;

public class Main {

    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    public static void main(String[] args) {

        Flyway flyway = Flyway.configure()
                .dataSource(
                        "jdbc:postgresql://localhost:5432/sample-jdbc",
                        "postgres",
                        "postgres"
                ).load();

        flyway.migrate();

    }
}