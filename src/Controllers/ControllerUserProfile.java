/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.User;
import Models.UserQuery;
import Views.UserProfile;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 *
 * @author PC MORGANA
 */
public class ControllerUserProfile {

    private UserProfile panel;
    private User user;
    private String[] images;
    private UserQuery uq;
    private int posImage, idUser;
    private ApplicationController appCont;

    public ControllerUserProfile(User user, ApplicationController appCont) {

        this.user = user;
        this.idUser = user.getId();
        this.posImage = -1;
        this.images = new String[6];
        this.uq = new UserQuery();
        this.appCont = appCont;

        images[0] = "/Views/imagenes/avata1.png";
        images[1] = "/Views/imagenes/avatar2.png";
        images[2] = "/Views/imagenes/cuarto.jpg";
        images[3] = "/Views/imagenes/blank-avatar.png";
        images[4] = "/Views/imagenes/quinto.png";
        images[5] = "/Views/imagenes/190613.png";

        panel = new UserProfile();
        addListeners();
        searchPositionImage();
        fillFields();

    }

    public UserProfile getPanel() {
        return panel;
    }

    private void addListeners() {
        panel.getButtonChangeImage().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (posImage + 1 < images.length) {
                    posImage++;
                } else {
                    posImage = 0;
                }
                user.setPicture(images[posImage]);
                panel.getImageIconLabel().setIcon(new ImageIcon(getClass().getResource(images[posImage])));

            }

        });

        panel.getButtonSaveAll().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panel.getNorificationLabel().setText("");

                String userName = panel.getBoxUserName().getText().trim();
                String name = panel.getBoxName().getText().trim();
                String lastName = panel.getBoxLastName().getText().trim();
                String password = panel.getBoxPassword().getText().trim();
                String description = panel.getBoxDescription().getText().trim();

                if (userName.isEmpty() || name.isEmpty() || lastName.isEmpty() || password.isEmpty() || description.isEmpty()) {
                    panel.getNorificationLabel().setText("Error: There are empty fields");
                } else {
                    user.setId(idUser);

                    user.setUserName(userName);
                    user.setName(name);
                    user.setLastName(lastName);
                    user.setPassword(password);
                    user.setDescription(description);

                    if (uq.update(user) != null) {
                        user = uq.update(user);
                        panel.getNorificationLabel().setText("Data updated successfully");
                        appCont.updateUser(user);
                    } else {
                        panel.getNorificationLabel().setText("Error: data not updated");
                    }

                }

            }

        });

    }

    private void searchPositionImage() {
        String url = user.getPicture();
        for (int i = 0; i < images.length; i++) {
            if (images[i].equals(url)) {
                posImage = i;
                break;
            }

        }

        if (posImage == -1) {
            posImage = 3;
        }
    }

    private void fillFields() {

        user.setPicture(images[posImage]);
        panel.getImageIconLabel().setIcon(new ImageIcon(getClass().getResource(images[posImage])));
        panel.getBoxUserName().setText(user.getUserName());
        panel.getNicknameHeadLabel().setText(user.getUserName());
        panel.getBoxName().setText(user.getName());
        panel.getBoxLastName().setText(user.getLastName());
        panel.getBoxPassword().setText(user.getPassword());
        panel.getBoxDescription().setText(user.getDescription());

    }

}
