package com.jdbc.app.persistence;

import com.jdbc.app.persistence.entity.EmployeeEntity;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;

public class EmployeeDAO {

    public void insert(final EmployeeEntity employee) {
        try (
                var connection = ConnectionUtil.getConnection();
                var statement = connection.prepareStatement("INSERT INTO employees (name, salary, birthday) VALUES (?, ?, ?);")
        ) {
            statement.setString(1, employee.getName());
            statement.setBigDecimal(2, employee.getSalary());
            statement.setTimestamp(3, Timestamp.valueOf(employee.getBirthday().atZoneSameInstant(UTC).toLocalDateTime()));

            statement.executeUpdate();

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
        try (var connection = ConnectionUtil.getConnection(); var statement = connection.createStatement()) {
            String sql = "DELETE FROM employees WHERE id = " + id;
            statement.executeUpdate(sql);

            System.out.printf("%s line(s)  afected", statement.getUpdateCount());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
