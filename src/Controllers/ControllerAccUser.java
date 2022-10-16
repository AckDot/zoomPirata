
package Controllers;

import Models.User;
import Models.UserQuery;
import Views.accUser;
import java.awt.event.ActionEvent;

/**
 *
 * @author PC
 */
public class ControllerAccUser {
    public accUser interf;
    public UserQuery connection;
    public ControllerAccUser(){
        interf=new accUser();
        connection=new UserQuery();
        addListeners();
        interf.setLocationRelativeTo(null);
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
            interf.error.setText("*error el usuario no existe");
        }
        else{
            new ApplicationController(user);
            interf.dispose();
        }
    }
}
