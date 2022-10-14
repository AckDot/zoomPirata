
package Controllers;

import Views.ApplicationGUI;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Jorge
 */
public class ApplicationController {
    
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        ApplicationController c = new ApplicationController(1, "Jorge");
    }

    private final ApplicationGUI gui;
    private final int idUser;
    
    public ApplicationController(int idUser, String nick){
        this.idUser = idUser;
        gui = new ApplicationGUI(nick);
        setActionProfileButton();
        setActionMeetButton();
        gui.setVisible(true);
    }
    
    public int getIdUser(){
        return idUser;
    }
    
    private void setActionProfileButton(){
        gui.getProfileButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel p = new JPanel();
                JButton b = new JButton("Prueba");
                p.add(b);
                p.setPreferredSize(new Dimension(300, 300));
                gui.setContentPanel(p);
            }
        });
    }
    
    private void setActionMeetButton(){
        gui.getMeetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel p = new JPanel();
                JButton b = new JButton("PruebaMeet");
                p.add(b);
                p.setPreferredSize(new Dimension(350, 350));
                gui.setContentPanel(p);
            }
        });     
    }
    
}
