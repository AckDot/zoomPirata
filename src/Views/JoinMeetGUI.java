package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Jorge
 */
public class JoinMeetGUI extends JPanel {

    private JTextArea area1;
    private JTextArea area2;
    private JTextArea area3;
    private JTextField meetCodeField;
    private JButton newMeetB;
    private JButton joinMeetB;
    private JLabel imgLabel;
    private JPanel pL;
    private JPanel pR;

    public JoinMeetGUI(Dimension d) {
        setLayout(new GridBagLayout());
        setPreferredSize(d);
        initPanel();
    }

    private void initPanel() {
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.25;
        c.weighty = 0.25;
        c.insets = new Insets(0, 5, 0, 5);
        c.fill = GridBagConstraints.BOTH;

        pL = new JPanel(new GridBagLayout());
        initLeftPanel(pL);
        c.gridx = 0;
        c.gridy = 0;
        add(pL, c);

        pR = new JPanel(new GridBagLayout());
        initRightPanel(pR);
        c.gridx = 1;
        c.gridy = 0;
        add(pR, c);
    }

    private void initLeftPanel(JPanel pL) {
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(5, 5, 5, 5);
        area1 = new JTextArea();
        area1.setText("\n\n\n\nPremium Meetings\nFree for everyone.");
        area1.setEditable(false);
        Font f = new Font("Serif", Font.BOLD, 28);
        area1.setFont(f);

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        pL.add(area1, c);

        area2 = new JTextArea();
        area2.setText("We have designed our service for secure meetings. So that everyone can use it.");
        area2.setEditable(false);
        area2.setLineWrap(true);
        area2.setWrapStyleWord(true);
        f = new Font("Serif", Font.PLAIN, 18);
        area2.setFont(f);
        c.gridy = 1;
        c.anchor = GridBagConstraints.NORTH;
        pL.add(area2, c);

        JPanel p1 = new JPanel(new GridBagLayout());

        newMeetB = new JButton("New meet");
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.NORTH;
        p1.add(newMeetB, c);

        joinMeetB = new JButton("Join meet as...");
        c.gridx = 1;
        p1.add(joinMeetB, c);

        meetCodeField = new JTextField();
        meetCodeField.setPreferredSize(new Dimension((int) (joinMeetB.getPreferredSize().getWidth() * 2) - 5, meetCodeField.getPreferredSize().height));
        meetCodeField.setForeground(Color.GRAY);
        meetCodeField.setText("Introduce a meet code to join it.");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        p1.add(meetCodeField, c);

        c.gridy = 2;
        c.weightx = 1;
        pL.add(p1, c);
    }

    private void initRightPanel(JPanel pR) {
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.insets = new Insets(5, 5, 5, 5);

        imgLabel = new JLabel();
        ImageIcon icono = new ImageIcon(getClass().getResource("/Views/imagenes/joinMeet_img.png"));
        imgLabel.setIcon(icono);
        c.gridx = 0;
        c.gridy = 0;
        pR.add(imgLabel, c);

        area3 = new JTextArea();
        area3.setText("Tap new meeting to generate a link that you can share with the people you want to meet.");
        area3.setEditable(false);
        area3.setLineWrap(true);
        area3.setWrapStyleWord(true);
        Font f = new Font("Serif", Font.PLAIN, 14);
        area3.setFont(f);
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        pR.add(area3, c);
    }
    
    public JButton getNewMeetButton(){
        return newMeetB;
    }
    
    public JButton getJoinMeetButton(){
        return joinMeetB;
    }
    
    public JTextField getMeetCodeField(){
        return meetCodeField;
    }
}
