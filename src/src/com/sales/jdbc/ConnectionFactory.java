package src.com.sales.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/BDVENDAS", "teste", "123");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
