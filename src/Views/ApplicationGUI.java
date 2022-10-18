package Views;

import Models.User;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/*
 *
 * @author Jorge Salazar
 */
public class ApplicationGUI extends JFrame {

    private JPanel principalPanel;
    private JPanel usersPanel;

    private JButton profileButton;
    private JButton meetButton;
    private JButton closeSesionButton;
    private JLabel perfilImg;
    private JLabel nickName;

    private Color fontsColor;
    private JPanel body;

    public ApplicationGUI(String nickName, String picture) {
        initFrame();
        initPanel(nickName, picture);        
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(1024, 768));
        setTitle("ZOOM");
        setLocationRelativeTo(null);
        principalPanel = new JPanel(new GridBagLayout());
        setContentPane(principalPanel);
        fontsColor = principalPanel.getBackground();
        fontsColor = new Color(fontsColor.getRed() - 25, fontsColor.getGreen() - 25, fontsColor.getBlue() - 25);
    }
    
    private void initPanel(String nickName, String picture){
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1.0;
        c.weighty = 0.0;
        
        JPanel header = new JPanel(new GridBagLayout());
        initHeader(header, nickName, picture);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        principalPanel.add(header, c);
        
        body = new JPanel(new GridBagLayout());
        initBody(body);
        c.gridy = 1;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        principalPanel.add(body, c);
        principalPanel.setBackground(fontsColor);
    }

    private void initHeader(JPanel header, String nick, String picture) {

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 10, 5, 10);
        perfilImg = new JLabel();
        ImageIcon icono = new ImageIcon(getClass().getResource(picture));
        icono = new ImageIcon(icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        perfilImg.setIcon(icono);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        header.add(perfilImg, c);

        nickName = new JLabel(nick);
        nickName.setFont(new Font("Serif", Font.BOLD, 18));
        c.gridx = 1;
        header.add(nickName, c);

        JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        headerRight.setBackground(fontsColor);

        JLabel appLogo = new JLabel();
        icono = new ImageIcon(getClass().getResource("/Views/imagenes/logo-Zoom.png"));
        icono = new ImageIcon(icono.getImage().getScaledInstance(188, 60, Image.SCALE_SMOOTH));
        appLogo.setIcon(icono);
        headerRight.add(appLogo);

        c.weightx = 1.0;
        c.gridx = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1.0;
        header.add(headerRight, c);

        header.setBackground(fontsColor);
        header.setPreferredSize(new Dimension(principalPanel.getPreferredSize().width, 66));
    }
    
    private void initBody(JPanel body){
        GridBagConstraints c = new GridBagConstraints();
        
        profileButton = new JButton("Profile");
        profileButton.setBackground(fontsColor);
        profileButton.setFocusPainted(false);
        profileButton.setBorderPainted(false);
        profileButton.setFont(new Font("Serif", Font.PLAIN, 20));
        c.weightx = 0.1;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        body.add(profileButton, c);
        
        meetButton = new JButton("Meet");
        meetButton.setBackground(fontsColor);
        meetButton.setBorderPainted(false);
        meetButton.setFont(new Font("Serif", Font.PLAIN, 20));
        c.gridy = 1;
        body.add(meetButton, c);
        
        closeSesionButton = new JButton("Exit");
        closeSesionButton.setBackground(fontsColor);
        closeSesionButton.setBorderPainted(false);
        closeSesionButton.setFont(new Font("Serif", Font.PLAIN, 20));
        c.gridy = 2;
        c.weighty = 1.0;
        body.add(closeSesionButton, c);
        
        body.setBackground(fontsColor);
    }

    public void setContentPanel(JPanel contentPanel) {
        if (body.getComponentCount() == 4) {
            body.remove(3);
        }
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.9;
        c.gridheight = 3;
        c.gridx = 1;
        c.gridy = 0;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        body.add(contentPanel, c);
        pack();
    }
    
    public void addUserMeeting(User user, int count){
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = count;
        UserPanel uP;
        if(user.getRol().equals("Anonymous")){
            uP = new UserPanel(user.getPicture(), "Invited " + (count + 1));
        }else{
            uP = new UserPanel(user.getPicture(), user.getUserName());
        }
        usersPanel.add(uP, c);
        pack();
    }

    public JButton getProfileButton() {
        return profileButton;
    }

    public JButton getMeetButton() {
        return meetButton;
    }
    
    public JButton getCloseButton(){
        return closeSesionButton;
    }

    public void updateUserData(String nick, String picture) {
        ImageIcon icono = new ImageIcon(getClass().getResource(picture));
        icono = new ImageIcon(icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        perfilImg.setIcon(icono);
        nickName.setText(nick);
    }
}
