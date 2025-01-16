
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    public static Connection getConnection() {
        Connection result = null;
        try {
            // Dang ky MySQL Driver voi DriverManager
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            //Cac thong so
            String url = "jdbc:mySQL://localhost:3306/mylaptop";
            String userName = "root";
            String password = "";
            //Tao ket noi 
            result = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            return result;
        }
        return result;
    }
    //Hàm đóng kết nối kết đến csdl
    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
        }
    }
    
}



