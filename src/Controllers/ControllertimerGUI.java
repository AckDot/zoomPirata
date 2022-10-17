/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Views.timerGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author PC
 */
public class ControllertimerGUI {
    timerGUI interf;
    JLabel marcador;
    String minutes;
    String hours;
    Timer timer;
    ActionListener accion;
    public ControllertimerGUI(JLabel marcador){
        interf=new timerGUI();
        this.marcador=marcador;
        llenado();
        addListeners();
        minutes="00";
        hours="00";
        timer = new Timer(1000,null);
        accion=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cronometro();
            }
        };
    }
    
    private void llenado(){
        String numero;
        for(int i=0;i<60;i++){
            
            if(i<10){
                numero="0"+String.valueOf(i);
            }
            else{
                numero=String.valueOf(i);
            }
            interf.choiceMinute.add(numero);
        }
        for (int k=0;k<13;k++){
            if(k<10){
                numero="0"+String.valueOf(k);
            }
            else{
                numero=String.valueOf(k);
            }
            interf.choiceHour.add(numero);
        }
        
        
    }

    private void addListeners() {

        interf.iniciar.addActionListener((ActionEvent e) -> {
           minutes =String.valueOf(interf.choiceMinute.getSelectedIndex());
           hours =String.valueOf(interf.choiceHour.getSelectedIndex());
           System.out.println(hours+":"+minutes);
           timer.addActionListener(accion);
           timer.start();
        });
    }
    
    public void visible(){
        interf.setVisible(true);
    }
    private void cronometro(){
        int min= Integer.parseInt(minutes);
        int hor= Integer.parseInt(hours);
        System.out.println(hours+minutes);
        if(min>0){
            min--;
            minutes=String.valueOf(min);            
        }
        if(hor>0){
            hor--;
            hours=String.valueOf(hor);
        }
    }
    
}
