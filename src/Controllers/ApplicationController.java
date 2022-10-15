package Controllers;

import Models.User;
import Views.ApplicationGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 *
 * @author Jorge
 */
public class ApplicationController {

    private final ApplicationGUI gui;
    private User user;

    public ApplicationController(User user) {
        this.user = user;
        gui = new ApplicationGUI(user.getUserName(), user.getPicture());
        setActionProfileButton();
        setActionMeetButton();
        resizeControl();
        gui.setVisible(true);
    }

    private void setActionProfileButton() {
        gui.getProfileButton().addActionListener((ActionEvent e) -> {
            ControllerUserProfile c = new ControllerUserProfile(user, this);
            gui.setContentPanel(c.getPanel());
        });
    }

    private void setActionMeetButton() {
        gui.getMeetButton().addActionListener((ActionEvent e) -> {
            JoinMeetController c = new JoinMeetController(this);
            gui.setContentPanel(c.getPanel());
        });
    }

    private void resizeControl() {
        gui.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                if(gui.getContentPanel().getComponentCount() > 0){
                    gui.getContentPanel().getComponent(0).setPreferredSize(gui.getContentPanel().getSize());
                }
            }
        });
    }

    public void updateUser(User user) {
        this.user = user;
        gui.updateUserData(user.getUserName(), user.getPicture());
    }
}
