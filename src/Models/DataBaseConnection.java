package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;


/**
 * DatabaseConexion
 */
public class DataBaseConnection {

    private Connection connection;

    public DataBaseConnection() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://aws-sa-east-1.connect.psdb.cloud/zoom?sslMode=VERIFY_IDENTITY",
                    "lige8w55arxwgd7en0i0",
                    "pscale_pw_Q43sgAvYivIPjVQMmHuKF6AFX5QhASpnc3iC3DekhpT");
            System.out.println("connected");
        } catch (SQLException e) {
            System.out.print("error");
        }
    }    
    
    public void insertUser(User u){
        try {
            String sql = "INSERT INTO user (user_name, password, name, last_name) VALUES (?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u.getUserName());
            st.setString(2, u.getPassword());
            st.setString(3, u.getName());
            st.setString(4, u.getLastName());
            st.execute();
            JOptionPane.showMessageDialog(null, "Usuario registrado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Usuario exitente" + e); 
        } 
    }
    
    public static void main(String[] args) {
        User u = new User("hola","pedro123","Juan", "Perez");
        DataBaseConnection db = new DataBaseConnection();
        db.insertUser(u);
    }
}

