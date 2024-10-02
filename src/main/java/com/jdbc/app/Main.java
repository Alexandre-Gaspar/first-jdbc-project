package com.jdbc.app;

import com.jdbc.app.persistence.ConnectionUtil;
import com.jdbc.app.persistence.EmployeeDAO;

import java.sql.SQLException;

public class Main {

    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    public static void main(String[] args) throws SQLException {

        try (var connection = ConnectionUtil.getConnection()) {
            System.out.println("Conectou");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }
}