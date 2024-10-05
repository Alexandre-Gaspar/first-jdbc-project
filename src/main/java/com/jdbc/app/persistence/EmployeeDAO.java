package com.jdbc.app.persistence;

import com.jdbc.app.persistence.entity.EmployeeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;

public class EmployeeDAO {

    public void insert(final EmployeeEntity employee) {
        try (var connection = ConnectionUtil.getConnection(); var statement = connection.createStatement()) {
            var name = employee.getName();
            var salary = employee.getSalary().toString();
            var birthday = formatOffsetDateTime(employee.getBirthday());

            String sql = "INSERT INTO employees (name, salary, birthday) " + "VALUES " +
                    "('" + name + "', " +
                    "'" + salary + "', '" +
                    birthday + "') " +
                    "RETURNING id;"; // returns the last object persisted id

            try (ResultSet resultSet = statement.executeQuery(sql)) {
                if (!resultSet.next()) {
                    employee.setId(resultSet.getLong("id"));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(final EmployeeEntity employee) {
        try (var connection = ConnectionUtil.getConnection(); var statement = connection.createStatement()) {
            var birthday = formatOffsetDateTime(employee.getBirthday());

            var sql = "UPDATE employees SET " +
                    "name = '" + employee.getName() + "', " +
                    "salary = " + employee.getSalary() + ", " +
                    "birthday = '" + birthday + "' " +
                    "WHERE id = " + employee.getId();

            statement.executeUpdate(sql);

            System.out.printf("Foram afectadas %s registros na base de dados", statement.getUpdateCount());
//            try (ResultSet resultSet = statement.executeQuery(sql)) {
//                if (!resultSet.next()) {
//                    employee.setId(resultSet.getLong("id"));
//                }
//            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(final Long id) {

    }

    public List<EmployeeEntity> findAll() {
        List<EmployeeEntity> entityList = new ArrayList<>();

        try (var connection = ConnectionUtil.getConnection(); var statement = connection.createStatement()) {

            String sql = "SELECT * FROM employees";
            statement.executeQuery(sql);


            var resultSet = statement.getResultSet();
            while (resultSet.next()) {
                var employee = new EmployeeEntity();
                employee.setId(resultSet.getLong("id"));
                employee.setName(resultSet.getString("name"));
                employee.setSalary(resultSet.getBigDecimal("salary"));
                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                employee.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));

                entityList.add(employee);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return entityList;
    }

    public EmployeeEntity findById(final long id) {

        var employee = new EmployeeEntity();

        try (var connection = ConnectionUtil.getConnection(); var statement = connection.createStatement()) {

            String sql = "SELECT * FROM employees WHERE id=" + id;
            statement.executeQuery(sql);


            var resultSet = statement.getResultSet();

            if (resultSet.next()) {
                employee.setId(resultSet.getLong("id"));
                employee.setName(resultSet.getString("name"));
                employee.setSalary(resultSet.getBigDecimal("salary"));
                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                employee.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return employee;
    }

    private String formatOffsetDateTime(final OffsetDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
