/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author navi-
 */
public class User {
    private int id;
    private String userName,
                   password,
                   name,
                   lastName;
    //constructors 
    public User(String userName, String password, String name, String lastName) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
    }

    public User(int id, String userName, String password, String name, String lastName) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
    }
    
    
    //getters
    public int getId() {return id;}
    public String getUserName() {return userName;}
    public String getPassword() {return password;}
    public String getName() {return name;}
    public String getLastName() {return lastName;}
    //setters
    public void setId(int id) {this.id = id;}
    public void setUserName(String userName) {this.userName = userName;}
    public void setPassword(String password) {this.password = password;}
    public void setName(String name) {this.name = name;}
    public void setLastName(String lastName) {this.lastName = lastName;}
}
