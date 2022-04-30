package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbCOnnection;
    private final Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://aws-online.cbsgwjc8ltev.us-west-2.rds.amazonaws.com:3306/?user=admin", "admin", "12345678");
    }

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        return dbCOnnection == null ? dbCOnnection = new DBConnection() : dbCOnnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
