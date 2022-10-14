package Views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

    private Container pane;
    private JPanel principalPanel;
    private JPanel contentPanel;

    private JButton profileButton;
    private JButton meetButton;
    
    public ApplicationGUI(String nickName) {
        initFrame();
        initHeader(nickName);
        initButtons();
        initContentPanel();
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setTitle("ZOOM");
        setLocationRelativeTo(null);
        pane = getContentPane();
        principalPanel = new JPanel(new GridBagLayout());
        pane.add(principalPanel);
    }
    
    private void initHeader(String nick){
        JPanel header = new JPanel(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 25, 5, 25);    
        JLabel perfilImg = new JLabel("Imagen Perfil");
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.0;
        header.add(perfilImg, c);
        
        JLabel nickName = new JLabel("Usuario:  " + nick);
        c.gridx = 1;
        header.add(nickName, c);
        
        JPanel headerRight = new JPanel(new GridBagLayout()); 
        headerRight.setBackground(new Color(50, 50, 50, 50));
        c.weightx = 1.0;
        c.gridx = 2;
        c.anchor = GridBagConstraints.EAST;
        header.add(headerRight, c);
        
        JLabel appName = new JLabel("Zoom SIS II");
        c.gridx = 0;
        headerRight.add(appName, c);
        
        JLabel appLogo = new JLabel("App Logo");
        c.gridx = 1;
        c.weightx = 0.0;
        headerRight.add(appLogo, c);
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 0, 0);
        header.setBackground(new Color(50, 50, 50, 50));
        header.setPreferredSize(new Dimension(principalPanel.getPreferredSize().width, 66));
        c.fill = GridBagConstraints.BOTH;
        principalPanel.add(header, c);  
    }

    private void initButtons() {
        GridBagConstraints c = new GridBagConstraints();

        profileButton = new JButton("Perfil");
        profileButton.setBackground(new Color(50, 50, 50));
        profileButton.setFocusPainted(false);
        profileButton.setBorderPainted(false);
        profileButton.setPreferredSize(new Dimension(
                (int) (profileButton.getPreferredSize().getWidth() * 1.5), (int) (profileButton.getPreferredSize().getHeight() * 1.5)));
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.0;
        principalPanel.add(profileButton, c);

        meetButton = new JButton("Reunión");
        meetButton.setBackground(new Color(50, 50, 50));
        meetButton.setBorderPainted(false);
        meetButton.setPreferredSize(profileButton.getPreferredSize());
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.NORTH;
        principalPanel.add(meetButton, c);      
    }
    
    private void initContentPanel(){
        GridBagConstraints c = new GridBagConstraints();
        contentPanel = new JPanel();
        contentPanel.setBackground(principalPanel.getBackground());
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1.0;
        c.gridheight = 2;
        c.fill = GridBagConstraints.BOTH;
        principalPanel.add(contentPanel, c);
        principalPanel.setBackground(new Color(50, 50, 50));
    }

    public void setContentPanel(JPanel contentPanel) {
        System.out.println(this.contentPanel.getComponentCount() );
        if (this.contentPanel.getComponentCount() != 0) {
            this.contentPanel.remove(0);
        }
        this.contentPanel.add(contentPanel);
        System.out.println(this.contentPanel.getComponentCount() );
        pack();
        repaint();
    }

    public JButton getProfileButton() {
        return profileButton;
    }

    public JButton getMeetButton() {
        return meetButton;
    }

}
