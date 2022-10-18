
package Models;

import java.util.Random;

/**
 *
 * @author Jorge
 */
public class MeetCodeGenerator {
    
    private String meetCode;
    private final Random r;
    
    public MeetCodeGenerator(){
        r = new Random();
    }
    
    public String generateCode(){
        meetCode = "";
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
}
