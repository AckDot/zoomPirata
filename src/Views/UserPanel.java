
package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jorge
 */
public class UserPanel extends JPanel{
    
    private JLabel icono;
    private JLabel userName;
    
    public UserPanel(String img, String userName){
        setLayout(new GridBagLayout());
        initComponents(img, userName);
        setBackground(new Color(0, 51, 102));
    }
      
    private void initComponents(String img, String userName){
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,5,5,5);
        
        icono = new JLabel();
        setUserIcon(img);
        c.gridx = 0;
        c.gridy = 0;
        add(icono, c);
        
        this.userName = new JLabel(userName);
        this.userName.setFont(new Font("Serif", Font.PLAIN, 16));
        this.userName.setForeground(Color.WHITE);
        c.gridx = 1;
        add(this.userName, c);
    }
    
    public void setUserIcon(String img){
        ImageIcon icon = new ImageIcon(getClass().getResource(img));
        icon = new ImageIcon(icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        icono.setIcon(icon);
    }
    
    public String getUserName(){
        return userName.getText();
    }
}
