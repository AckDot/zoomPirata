
package Controllers;

import Models.User;
import Views.ApplicationGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jorge
 */
public class ApplicationController {
    
    private final ApplicationGUI gui;
    private final User user;
    
    public ApplicationController(User user){
        this.user = user;
        gui = new ApplicationGUI(user.getUserName());
        setActionProfileButton();
        setActionMeetButton();
        gui.setVisible(true);
    }
    
    
    private void setActionProfileButton(){
        gui.getProfileButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    
    private void setActionMeetButton(){
        gui.getMeetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  gui.setContentPanel(new PanelMeetController(user).getPanelMeet());
            }
        });     
    }
    
}
