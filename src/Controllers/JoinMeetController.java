package Controllers;

import Models.Meeting;
import Models.MeetingQuery;
import Views.JoinMeetGUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jorge
 */
public class JoinMeetController {

    private final JoinMeetGUI gui;
    private final ApplicationController appController;
    private final MeetingQuery meetQuery;
    PanelMeetController meetController;

    public JoinMeetController(ApplicationController aC) {
        this.appController = aC;
        gui = new JoinMeetGUI(new Dimension(970, 600));
        meetQuery = new MeetingQuery();
        newMeetEvent();
        joinMeetAction();
        meetCodeAction();
    }

    public JPanel getPanel() {
        return gui;
    }

    private void newMeetEvent() {
        gui.getNewMeetButton().addActionListener((ActionEvent e) -> {
            Meeting meet = new Meeting(appController.getUser());
            while (!meetQuery.insertMeeting(meet)) {
                meet = new Meeting(appController.getUser());
            }              
            appController.getUser().setRol("Host");
            meet = meetQuery.getMeet(meet.getCode());
            meetQuery.insertUserMeeting(appController.getUser(), meet);
            meetController = new PanelMeetController(meet); 
            appController.setContentPanel(meetController.getPanelMeet());
        });
    }

    private void joinMeetAction() {
        gui.getJoinMeetButton().addActionListener((ActionEvent e) -> {
            String meetCode = gui.getMeetCodeField().getText();
            if (!meetCode.equals("") && !meetCode.equals("Introduce a meet code to join it.")) {
                int option = JOptionPane.showOptionDialog(gui, "How do you want to join the meeting?", "Meet Login", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Normal login", "Anonymous login"}, "Normal login");
                if (option != -1) {
                    Meeting meet = meetQuery.getMeet(meetCode);
                    if (meet == null) {
                        JOptionPane.showOptionDialog(gui, "Please introduce a valid meet code.", "Warning", JOptionPane.OK_OPTION,
                                JOptionPane.WARNING_MESSAGE, null, new Object[]{"OK"}, "OK");
                    } else {
                        if (option == 0) {//Ingreso normal
                            appController.getUser().setRol("Normal");
                        }
                        if (option == 1) {//Ingreso anonimo
                            appController.getUser().setRol("Anonymous");
                        }
                        //Insertando al user en la reunion en la bd
                        meetQuery.insertUserMeeting(appController.getUser(), meet);
                        //Creando y mostrando la interfaz del meet
                        meetController = new PanelMeetController(meet);
                        appController.setContentPanel(meetController.getPanelMeet());
                    }
                }
            }else{
                JOptionPane.showOptionDialog(null, "Please introduce a meet code.", "Advise", JOptionPane.OK_OPTION,
                                JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"OK"}, "OK");
            }
        });
    }

    private void meetCodeAction() {
        gui.getMeetCodeField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                gui.getMeetCodeField().setText("");
                gui.getMeetCodeField().setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = gui.getMeetCodeField().getText();
                if (text.equals("")) {
                    gui.getMeetCodeField().setForeground(Color.GRAY);
                    gui.getMeetCodeField().setText("Introduce a meet code to join it.");
                }
            }
        });
    }
}
