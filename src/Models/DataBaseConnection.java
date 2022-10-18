package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * DatabaseConexion
 * @author  IvanPalacios
 */
public class DataBaseConnection {

    private Connection connection;

    public DataBaseConnection() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://aws-sa-east-1.connect.psdb.cloud/zoom?sslMode=VERIFY_IDENTITY",
                    "lige8w55arxwgd7en0i0",
                    "pscale_pw_Q43sgAvYivIPjVQMmHuKF6AFX5QhASpnc3iC3DekhpT");

        } catch (SQLException e) {
            System.out.print("No Conected" + e.getMessage());
        }
    }
    
    public Connection getConnection(){
        return connection;
    }    
}
