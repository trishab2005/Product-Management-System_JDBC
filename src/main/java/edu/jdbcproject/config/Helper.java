package edu.jdbcproject.config;


import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Helper {

    public static Connection makeCon() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        ods.setServerName("localhost");
        ods.setDatabaseName("trisha");
        ods.setURL("jdbc:oracle:thin:@//localhost:1521/XEPDB1");
        ods.setUser("c##scott");
        ods.setPassword("tiger");
        return ods.getConnection();
    }
}
