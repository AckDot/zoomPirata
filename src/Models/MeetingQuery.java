/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ivan Palacios
 */
public class MeetingQuery {
    
    private final DataBaseConnection CONNECTOR = new DataBaseConnection(); 
    private final Connection connection = CONNECTOR.getConnection();
    //Crea la reunion creando el atributo de host
    public boolean insertMeeting(Meeting meet) {
        String sql = "insert into meetings (code, id_user) values (?,?)";
        
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,meet.getCode());
            st.setInt(2, meet.getHost().getId());
            st.execute(); 
            return true;
        }catch(SQLException e){
            System.out.println("error " + e);
        }
        return false;
    }
    //borra la reunion y los usuarios que estan dentro de la reunion
    public boolean deleteMeeting(Meeting meet){
        String sql = "delete from meetings where code = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, meet.getCode());
            st.execute();
            deleteMeetingsUsers(meet);
        }catch(SQLException e){
            System.out.println("error " + e);
        }
        return false;
    }
    // agrega usuarios a la reunion
    public boolean insertUserMeeting(User u, Meeting meet){
        String sql = "insert into meetings_users (id_user, id_meeting, rol) values (?,?,?)";
        
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1,u.getId());
            st.setInt(2,meet.getId());
            st.setString(3,u.getRol());
            st.execute(); 
            return true;
        }catch(SQLException e){
            System.out.println("error " + e);
        }
        return false;
    }
    // borrar usuarios de toda la reunion
    private void deleteMeetingsUsers(Meeting meet){
        String sql = "delete from meetings_users where id_meeting = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1,meet.getId());
            st.execute();
        }catch(SQLException e){
            System.out.println("eror " +e);
        }
    }
    
    
    /*******
    *getMeet(code)
    * return a meet with meet code.
    * return a meet null if don't found it.
    *****/
    public Meeting getMeet(String code) {
        String sql = "select * from meetings where code like ?";
        Meeting meet = null;
        UserQuery uq = new UserQuery(); 
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);
            ResultSet result = st.executeQuery();
            while(result.next()){
                User u = uq.getUser(result.getInt(3));
                meet = new Meeting(
                        result.getInt(1),
                        result.getString(2),u);
            }
        }catch(SQLException e){
            System.out.println("error " + e);
        }
        return meet;
    }
    //Cuenta la cantidad de usuarios que estan dentro de la reunion
    public int countUserIntoMeet(Meeting meet){
        String sql = "select count(id_meeting) from meetings_users where id_meeting = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, meet.getId());
            ResultSet result = st.executeQuery();
            while(result.next()){
                return result.getInt(1);
            }
        }catch(SQLException e){
            System.out.println("error " +e);
        }
        return 0;
    }
    
    public ArrayList<User> usersInMeet(Meeting meet){  
        ArrayList<User> listaUsers = new ArrayList<>();
        String sql = "select * from (select id_user, rol from meetings_users where id_meeting = ?) uno, users where uno.id_user = id";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, meet.getId());
            ResultSet result = st.executeQuery();
            while(result.next()){
                User u = new User(
                        result.getInt(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7),
                        result.getString(8),
                        result.getString(9)
                );
                u.setRol(result.getString(2));
                listaUsers.add(u);
            }
        }catch(SQLException e){
            System.out.println("erro " +e);
        }
        return listaUsers;
    }
}