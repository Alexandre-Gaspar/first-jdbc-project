package com.jdbc.app.persistence;

import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ConnectionUtil {

    private static final String datasourceUrl = "jdbc:postgresql://localhost:5432/sample-jdbc";
    private static final String datasourceUser = "postgres";
    private static final String datasourcePassword = "postgres";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(datasourceUrl, datasourceUser, datasourcePassword);
    }

}
