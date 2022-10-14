/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author IvanPalacios
 */
public class UserQuery {

    private final DataBaseConnection CONNECTOR = new DataBaseConnection();
    private Connection connection = CONNECTOR.getConnection();

    public boolean insertUser(User u) {
        try {
            String sql = "INSERT INTO users (user_name, password, name, last_name) VALUES (?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u.getUserName());
            st.setString(2, u.getPassword());
            st.setString(3, u.getName());
            st.setString(4, u.getLastName());
            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("SQl Exception: " + e);
            return false;
        }
    }

    public User verifyUser(String userName, String password) {
        String sql = "select * from users where user_name like ? and password like ?";
        User user = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            st.setString(2, password);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                user = new User(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7)
                );
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return user;
    }

    public User update(User u) {
        String sql = "UPDATE users "
                   + "Set user_name = ?, password = ?, name = ?, last_name = ?, description = ?, picture = ?"
                   + "Where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u.getUserName());
            st.setString(2, u.getPassword());
            st.setString(3, u.getName());
            st.setString(4, u.getLastName());
            st.setString(5, u.getDescription());
            st.setString(6, u.getPicture());
            st.setInt(7, u.getId());
            st.execute();
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return getUser(u.getId());
    }

    private User getUser(int id) {
        String sql = "select * from users where id = ?";
        User user = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                user = new User(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7)
                );
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return user;
    }

}
