package Controllers;

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
    private final ApplicationController aC;

    public JoinMeetController(ApplicationController aC) {
        this.aC = aC;
        gui = new JoinMeetGUI(new Dimension(980, 500));
        newMeetEvent();
        joinMeetAction();
        meetCodeAction();
    }

    public JPanel getPanel() {
        return gui;
    }

    private void newMeetEvent() {
        gui.getNewMeetButton().addActionListener((ActionEvent e) -> {

        });
    }

    private void joinMeetAction() {
        gui.getJoinMeetButton().addActionListener((ActionEvent e) -> {
            int option = JOptionPane.showOptionDialog(null, "¿Como desea ingresar a la reunion?", "Ingreso", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Ingreso normal", "Ingreso anonimo"}, "Ingreso normal");
            if (option == 0) {//Ingreso normal

            }
            if (option == 1) {//Ingreso anonimo

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
                if(text.equals("")){
                    gui.getMeetCodeField().setForeground(Color.GRAY);
                    gui.getMeetCodeField().setText("Introduce a meet code to join it.");
                }
            }
        });
    }
}
