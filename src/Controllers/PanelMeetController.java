/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Meeting;
import Models.MeetingQuery;
import Models.User;
import Views.PanelMeet;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
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

/**
 *
 * @author ackdot
 */
public class PanelMeetController {

    private final PanelMeet panel;
    private final Meeting meet;
    private boolean selectHand;
    private MeetingQuery meetQ;
    private ArrayList<User> listaUsers;

    PanelMeetController(Meeting meet) {
        this.meet = meet;
        panel = new PanelMeet();
        selectHand = false;
        panel.setMeetCodeLabel(meet.getCode());
        introSound();
        setActionTimerButton();
        setActionChatButton();
        setActionPeopleButton();
        setActionNotesButton();
        setActionHandButton();
        setDisplayTimer("00:00:00");
        meetQ = new MeetingQuery();
        listaUsers = meetQ.usersInMeet(meet);
        panel.setUsersCount(listaUsers.size() + "/100 Users");
        addUsersMeet();
        
    }
    
    private void addUsersMeet(){
        for(int i = 0; i < listaUsers.size(); i++){
            User u = listaUsers.get(i);
            if(u.getRol().equals("Anonymous")){
                panel.addUsersPanel(u.getPicture(), "Anonymous", i);
            }else{
                panel.addUsersPanel(u.getPicture(), u.getUserName(), i);
            } 
        }  
    }

    private void setActionTimerButton() {

    }

    private void setActionChatButton() {

    }

    private void setActionPeopleButton() {

    }

    private void setActionNotesButton() {

    }

    private void setActionHandButton() {
        panel.getbuttonHand().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectHand = !selectHand;
                if (!selectHand) {
                    panel.getbuttonHand().setIcon(new ImageIcon(getClass().getResource("/Views/imagenes/outline_front_hand_white_24dp.png")));
                } else {
                    AudioInputStream audio = null;
                    try {
                        panel.getbuttonHand().setIcon(new ImageIcon(getClass().getResource("/Views/imagenes/outline_front_hand_black_24dp.png")));
                        File file = new File("src/Controllers/sonidos/pedirMano.wav");
                        audio = AudioSystem.getAudioInputStream(file);
                        Clip clip = AudioSystem.getClip();
                        clip.open(audio);
                        clip.start();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                        Logger.getLogger(PanelMeetController.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            audio.close();
                        } catch (IOException ex) {
                            Logger.getLogger(PanelMeetController.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
        AudioInputStream audio = null;
        try {
            File file = new File("src/Controllers/sonidos/intro.wav");
            audio = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(PanelMeetController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                audio.close();
            } catch (IOException ex) {
                Logger.getLogger(PanelMeetController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    JPanel getPanelMeet() {
        return panel;
    }

}
