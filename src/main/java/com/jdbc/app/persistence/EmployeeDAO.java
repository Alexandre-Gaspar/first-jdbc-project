package com.jdbc.app.persistence;

import com.jdbc.app.persistence.entity.EmployeeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    public void update(final EmployeeEntity entity) {

    }

    public void delete(final Long id) {

    }

    public List<EmployeeEntity> findAll() {
        return null;
    }

    public EmployeeEntity findById(final long id) {
        return null;
    }

    private String formatOffsetDateTime(final OffsetDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
