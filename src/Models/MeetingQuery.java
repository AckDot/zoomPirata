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
 * @author Ivan Palacios
 */
public class MeetingQuery {
    
    private final DataBaseConnection CONNECTOR = new DataBaseConnection(); 
    private final Connection connection = CONNECTOR.getConnection();
    
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
    
    public boolean insertUserMeeting(User u, Meeting meet){
        String sql = "insert into mettings_users (id_user, id_meeting, rol) values (?,?,?)";
        
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1,u.getId());
            st.setInt(2,meet.getId());
            st.setString(3,u.getRol());
        }catch(SQLException e){}
        return false;
    }
    
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
                        result.getString(2),
                        u 
                );
            }
        }catch(SQLException e){
            System.out.println("error " + e);
        }
        return meet;
    }
    
    public static void main(String[] args) {
        UserQuery uq = new UserQuery();
        User u = uq.getUser(9);
        u.setRol("host");
        String code = "jalñskdjf";
        Meeting meet = new Meeting(code, u);
        MeetingQuery mq = new MeetingQuery();
        //mq.insertMeeting(meet);
        mq.deleteMeeting(meet);
    }
}
