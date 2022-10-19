/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Views.timerGUI;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author PC
 */
public class ControllertimerGUI {

    timerGUI interf;
    JLabel marcador;
    int seconds;
    int minutes;
    int hours;
    Timer timer;
    boolean bandera;
    ActionListener accion;
    JButton btnPlayPause;
    JButton btnClose;

    public ControllertimerGUI(JLabel marcador, JButton playPause, JButton close) {
        interf = new timerGUI();
        this.marcador = marcador;
        btnPlayPause = playPause;
        btnClose = close;
        btnPlayPause.setVisible(false);
        btnClose.setVisible(false);
        llenado();
        addListeners();
        accion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cronometro();
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(ControllertimerGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ControllertimerGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(ControllertimerGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        timer = new Timer(1000, accion);
        bandera = false;
    }

    private void llenado() {
        llenandoItems(60, interf.choiceSecond);
        llenandoItems(60, interf.choiceMinute);
        llenandoItems(12, interf.choiceHour);

    }

    private void addListeners() {

        interf.iniciar.addActionListener((ActionEvent e) -> {

            seconds = interf.choiceSecond.getSelectedIndex();
            minutes = interf.choiceMinute.getSelectedIndex();
            hours = interf.choiceHour.getSelectedIndex();
            timer.start();
            interf.setVisible(false);
            btnClose.setVisible(true);
            btnPlayPause.setVisible(true);
        });
    }

    public void visible() {
        mostrarTiempo();
        interf.choiceSecond.select(0);
        interf.choiceMinute.select(0);
        interf.choiceHour.select(0);
        interf.setVisible(true);
        marcador.setVisible(true);
    }

    private void cronometro() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (seconds == 0 && minutes == 0 && hours == 0) {
            timer.stop();
            marcador.setVisible(false);
            btnClose.setVisible(false);
            btnPlayPause.setVisible(false);
            BufferedInputStream myStream = new BufferedInputStream(getClass().getResourceAsStream("/Controllers/sonidos/alarma.wav"));
            try {
                AudioInputStream audio = AudioSystem.getAudioInputStream(myStream);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                Logger.getLogger(PanelMeetController.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "se acabo el tiempo");
        }
        mostrarTiempo();

        if (seconds > 0) {
            seconds--;
        } else {
            if (minutes > 0) {
                minutes--;
                seconds = 59;
            } else {
                if (hours > 0) {
                    hours--;
                    minutes = 59;
                    seconds = 59;
                }
            }
        }

    }

    private void mostrarTiempo() {
        String num = numString(hours);
        num = num + ":" + numString(minutes) + ":" + numString(seconds);
        marcador.setText(num);
    }

    private void llenandoItems(int num, Choice list) {
        for (int i = 0; i < num; i++) {
            list.add(numString(i));
        }
    }

    private String numString(int n) {
        String num;
        if (n < 10) {
            num = "0" + String.valueOf(n);
        } else {
            num = String.valueOf(n);
        }
        return num;
    }

    public void playPause() {

        if (bandera) {
            timer.start();
            bandera = false;
        } else {
            timer.stop();
            bandera = true;
        }
    }

    public void cerrar() {
        bandera = false;
        btnClose.setVisible(false);
        btnPlayPause.setVisible(false);
        timer.stop();
        marcador.setVisible(false);
        hours = 0;
        seconds = 0;
        minutes = 0;
    }
}
