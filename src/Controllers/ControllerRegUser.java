/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.DataBaseConnection;
import Models.User;
import Views.regUser;
import java.awt.event.ActionEvent;
/**
 *
 * @author PC
 */
public class ControllerRegUser {
    public regUser interf ;
    public DataBaseConnection connector ;
    
    public ControllerRegUser(){
        
        interf = new regUser();
        connector = new DataBaseConnection();
        addListeners();
    }
    
     private void addListeners(){
        interf.btnContinue.addActionListener((ActionEvent e) -> {
        registerUser();
        });
    }
     private void registerUser(){
         String userName=interf.getUsername().getText();
         String password=interf.fieldPassword.getText();
         String name=interf.txtName.getText();
         String lastName=interf.txtName.getText();
         User user= new User(userName,password, name, lastName);
         connector.insertUser(user);
     }
}
