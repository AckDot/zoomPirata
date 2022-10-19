/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Meeting;
import Models.MeetingQuery;
import Models.User;
import Views.PanelMeet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author ackdot
 */
public class PanelMeetController {

    private final PanelMeet panel;
    private boolean selectHand;
    private MeetingQuery meetQ;
    private ArrayList<User> listaUsers;
    private Meeting meet;

    PanelMeetController(Meeting meet) {
        this.meet = meet;
        panel = new PanelMeet();
        selectHand = false;
        panel.setMeetCodeLabel(meet.getCode());
        setActionHandButton();
        setDisplayTimer("00:00:00");
        reloadUsers();
        ActionListener usuarios = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                reloadUsers();
            }
        };
        Timer timer = new Timer(2500, usuarios);
        timer.start(); 
    }
    
    private void reloadUsers(){
        meetQ = new MeetingQuery();
        listaUsers = meetQ.usersInMeet(meet);
        panel.setUsersCount(listaUsers.size() + "/100 Users");
        addUsersMeet();
    }

    private void addUsersMeet() {
        for (int i = 0; i < listaUsers.size(); i++) {
            User u = listaUsers.get(i);
            if (u.getRol().equals("Anonymous")) {
                panel.addUsersPanel(u.getPicture(), "Anonymous", i);
            } else {
                panel.addUsersPanel(u.getPicture(), u.getUserName(), i);
            }
        }
    }

    private void setActionHandButton() {
        panel.getbuttonHand().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectHand = !selectHand;
                if (!selectHand) {
                    panel.getbuttonHand().setIcon(new ImageIcon(getClass().getResource("/Views/imagenes/outline_front_hand_white_24dp.png")));
                } else {
                    panel.getbuttonHand().setIcon(new ImageIcon(getClass().getResource("/Views/imagenes/outline_front_hand_black_24dp.png")));
                    BufferedInputStream myStream = new BufferedInputStream(getClass().getResourceAsStream("/Controllers/sonidos/pedirMano.wav"));
                    try {
                        AudioInputStream audio = AudioSystem.getAudioInputStream(myStream);
                        Clip clip2 = AudioSystem.getClip();
                        clip2.open(audio);
                        clip2.start();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                        Logger.getLogger(PanelMeetController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void setDisplayTimer(String text) {
        panel.getdisplay_Timer().setText(text);
    }

    private void introSound() {
        BufferedInputStream myStream = new BufferedInputStream(getClass().getResourceAsStream("/Controllers/sonidos/intro.wav"));
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(myStream);
            Clip clip2 = AudioSystem.getClip();
            clip2.open(audio);
            clip2.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(PanelMeetController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    JPanel getPanelMeet() {
        introSound();
        return panel;
    }

}
