package Controllers;

import Models.User;
import Views.ApplicationGUI;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

/**
 *
 * @author Jorge
 */
public class ApplicationController {

    private final ApplicationGUI gui;
    private User user;
    private final JoinMeetController joinMeetCont;
    private final ControllerUserProfile userProfCont;

    public ApplicationController(User user) {
        this.user = user;
        gui = new ApplicationGUI(user.getUserName(), user.getPicture());
        joinMeetCont = new JoinMeetController(this);
        userProfCont = new ControllerUserProfile(user, this);
        setActionProfileButton();
        setActionMeetButton();
        setActionExitButton();
        gui.setContentPanel(joinMeetCont.getPanel());
        gui.setVisible(true);
    }

    private void setActionProfileButton() {
        gui.getProfileButton().addActionListener((ActionEvent e) -> {
            gui.setContentPanel(userProfCont.getPanel());
        });
    }

    private void setActionMeetButton() {
        gui.getMeetButton().addActionListener((ActionEvent e) -> {
            gui.setContentPanel(joinMeetCont.getPanel());
        });
    }

    private void setActionExitButton(){
        gui.getCloseButton().addActionListener((ActionEvent e) -> {
            new ControllerAccUser();
            gui.dispose();
        });
    }
    
    public void setContentPanel(JPanel panel) {
        gui.setContentPanel(panel);
    }

    public void updateUser(User user) {
        this.user = user;
        gui.updateUserData(user.getUserName(), user.getPicture());
    }
    
    public User getUser() {
        return user;
    }
}
