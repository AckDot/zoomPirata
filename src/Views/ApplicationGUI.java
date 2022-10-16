package Views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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

    private Container pane;
    private JPanel principalPanel;
    private JPanel contentPanel;

    private JButton profileButton;
    private JButton meetButton;
    private JLabel perfilImg;
    private JLabel nickName;

    private Color fontsColor;

    public ApplicationGUI(String nickName, String picture) {
        initFrame();
        initHeader(nickName, picture);
        initButtons();
        initContentPanel();       
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(1024, 768));
        setTitle("ZOOM");
        setLocationRelativeTo(null);
        pane = getContentPane();
        principalPanel = new JPanel(new GridBagLayout());
        pane.add(principalPanel);
        fontsColor = principalPanel.getBackground();
        fontsColor = new Color(fontsColor.getRed() - 25, fontsColor.getGreen() - 25, fontsColor.getBlue() - 25);
    }

    private void initHeader(String nick, String picture) {
        JPanel header = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 25, 5, 25);
        perfilImg = new JLabel();
        ImageIcon icono = new ImageIcon(getClass().getResource(picture));
        icono = new ImageIcon(icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        perfilImg.setIcon(icono);
        c.gridx = 0;
        c.gridy = 0;
        header.add(perfilImg, c);

        nickName = new JLabel(nick);
        nickName.setFont(new Font("Serif", Font.BOLD, 18));
        c.gridx = 1;
        header.add(nickName, c);

        JPanel headerRight = new JPanel(new GridBagLayout());
        headerRight.setBackground(fontsColor);

        JLabel appLogo = new JLabel();
        icono = new ImageIcon(getClass().getResource("/Views/imagenes/logo-Zoom.png"));
        icono = new ImageIcon(icono.getImage().getScaledInstance(188, 60, Image.SCALE_SMOOTH));
        appLogo.setIcon(icono);
        c.gridx = 0;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.EAST;
        headerRight.add(appLogo, c);

        c.weightx = 1.0;
        c.gridx = 2;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.WEST;
        header.add(headerRight, c);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 0, 0);
        header.setBackground(fontsColor);
        header.setPreferredSize(new Dimension(principalPanel.getPreferredSize().width, 66));
        principalPanel.add(header, c);
    }

    private void initButtons() {
        GridBagConstraints c = new GridBagConstraints();

        profileButton = new JButton("Profile");
        profileButton.setBackground(fontsColor);
        profileButton.setFocusPainted(false);
        profileButton.setBorderPainted(false);
        profileButton.setPreferredSize(new Dimension(
                (int) (profileButton.getPreferredSize().getWidth() * 1.5), (int) (profileButton.getPreferredSize().getHeight() * 1.5)));
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.02;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        principalPanel.add(profileButton, c);

        meetButton = new JButton("Meet");
        meetButton.setBackground(fontsColor);
        meetButton.setBorderPainted(false);
        meetButton.setPreferredSize(profileButton.getPreferredSize());
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.NORTH;
        principalPanel.add(meetButton, c);
    }

    private void initContentPanel() {
        GridBagConstraints c = new GridBagConstraints();
        contentPanel = new JPanel();
        contentPanel.setBackground(principalPanel.getBackground());
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridheight = 2;
        c.fill = GridBagConstraints.BOTH;
        principalPanel.add(contentPanel, c);
        principalPanel.setBackground(fontsColor);
    }

    public void setContentPanel(JPanel contentPanel) {
        if (this.contentPanel.getComponentCount() != 0) {
            this.contentPanel.remove(0);
        }
        this.contentPanel.add(contentPanel);
        pack();
    }

    public JButton getProfileButton() {
        return profileButton;
    }

    public JButton getMeetButton() {
        return meetButton;
    }

    public void updateUserData(String nick, String picture) {
        ImageIcon icono = new ImageIcon(getClass().getResource(picture));
        icono = new ImageIcon(icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        perfilImg.setIcon(icono);

        nickName.setText(nick);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
