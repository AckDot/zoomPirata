/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.UserQuery;
import Models.User;
import Views.regUser;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.event.ActionEvent;
/**
 *
 * @author PC
 */
public class ControllerRegUser {
    public regUser interf ;
    public UserQuery connector ;
    
    public ControllerRegUser(){
        
        interf = new regUser();
        connector = new UserQuery();
        addListeners();
        interf.setVisible(true);
    }
    
     private void addListeners(){
        interf.btnContinue.addActionListener((ActionEvent e) -> {
            registerUser();
        });
        
        interf.btnCancel.addActionListener((ActionEvent e)->{
            new ControllerAccUser();
            interf.dispose();
        });
    }
     private void registerUser(){
         String userName=interf.txtUsername.getText();
         String password=String.valueOf(interf.fieldPassword.getPassword());
         String name=interf.txtName.getText();
         String lastName=interf.txtName.getText();
         User user= new User(userName,password, name, lastName);
         if (connector.insertUser(user)){
             FlatDarkLaf.setup();
            new ApplicationController(user);
            interf.dispose();
         }
         else {
             System.out.println("userName no valido");
         }
     }

}
