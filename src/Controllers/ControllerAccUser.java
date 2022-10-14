/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.DataBaseConnection;
import Views.accUser;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.event.ActionEvent;

/**
 *
 * @author PC
 */
public class ControllerAccUser {
    public accUser interf;
    public DataBaseConnection connection;
    public ControllerAccUser(){
        interf=new accUser();
        connection=new DataBaseConnection();
        addListeners();
        interf.setVisible(true);
    }
    
    private void addListeners(){
        interf.btnCreateAccount.addActionListener((ActionEvent e)->{
            new ControllerRegUser();
            interf.dispose();
        });
        interf.btnSigin.addActionListener((ActionEvent e)->{
            accederUser();
        });
    }
    private void accederUser(){
        String userName= interf.txtUsername.getText();
        String password= String.valueOf(interf.txtPassword.getPassword());
        User user= connection.verifyUser(userName,password);
        if(user==null){
            System.out.println("usuario no encontrado");
        }
        else{
            FlatDarkLaf.setup();
            new ApplicationController(user);
            interf.dispose();
        }
    }
}
