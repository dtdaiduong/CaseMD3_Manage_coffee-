package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/coffee_manage?useSSL=false";
    private static String userName = "root";
    private static String passWord = "123456";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, userName, passWord);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

//    public static Connection getSqlConnection(String hostName, String dbName, String userName, String password) {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
//            return DriverManager.getConnection(connectionURL, userName, password);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
}
