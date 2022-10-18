/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Random;

/**
 *
 * @author Ivan Palacios
 */
public class Meeting {
    private int id;
    private String code;
    private User host; 
    
    public Meeting (User host){
        this.code = generateCode();
        this.host = host;
    }

    public Meeting(int id, String code, User host) {
        this.id = id;
        this.code = code;
        this.host = host;
    }
    
    private String generateCode(){
        Random r = new Random();
        String meetCode = "";
        for(int i = 0; i < 8; i++){
            int tipoC = r.nextInt(3);
            //Numero 0-9
            if(tipoC == 0){
                meetCode += r.nextInt(10);
            }
            //Letra mayuscula
            if(tipoC == 1){
                char c = (char)(r.nextInt(26) + 65);
                meetCode += c;
            }
            //Letra minuscula
            if(tipoC == 2){
                char c = (char)(r.nextInt(26) + 97);
                meetCode += c;
            }
        }
        return meetCode;
    }
    
    //getters
    public int getId() { return id;}
    public String getCode() { return code;}
    public User getHost() { return host;}
    
    //setters
    public void setId(int id) { this.id = id;}
    public void setCode(String code) { this.code = code;}
    public void setHost(User host) {this.host = host;}
}
