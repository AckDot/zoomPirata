/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Ivan Palacios
 */
public class Meeting {
    private int id;
    private String code;
    private User host; 
    
    public Meeting (String code, User host){
        this.code = code;
        this.host = host;
    }

    public Meeting(int id, String code, User host) {
        this.id = id;
        this.code = code;
        this.host = host;
    }
    
    //getters
    public int getId() { return id;}
    public String getCode() { return code;}
    public User getHost() { return host;}
    
    //setters
    public void setId(int id) { this.id = id;}
    public void setCode(String code) { this.code = code;}
    public void setHost(User host) {this.host = host;}
    
}
