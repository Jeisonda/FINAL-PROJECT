package co.edu.uptc.views.panelLeft;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JLabel;
import javax.swing.JPanel;

import co.edu.uptc.views.MainFrame;

public class MainPanelLeft extends JPanel {
    private JLabel labelCreateUser;
    private JLabel labelCreateVacination;
    private JLabel labelVacination;
    private JLabel labelHistory;
    private JLabel labelMain;

    private MainFrame main;

    public MainPanelLeft(MainFrame main){
        this.main = main;
        panelConfiguration();
    }

    private void panelConfiguration(){
        setBackground(new Color(14,128,136));
        setPreferredSize(new Dimension(200,0));
        setLayout(null);
        initComponents();
    }

    private void initComponents(){
        addLabelCreateUser();
        addLabelCreateVacination();
        addLabelVacination();
        addLabelHistory();
        addlabelMain();
    }

    private void addLabelCreateUser(){
        labelCreateUser = new JLabel("CREAR USUARIO");
        labelCreateUser.setBounds(0,80,200,30);
        labelConfiguration(labelCreateUser);
        addLabelCreateUserMouseListener();
        add(labelCreateUser);
    }

    private void addLabelCreateUserMouseListener() {
        labelCreateUser.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                main.changePanelCenter("createUser");
            }
            
            public void mouseEntered(MouseEvent e){
                labelCreateUser.setBackground(new Color(14,128,110));
            }

            public void mouseExited(MouseEvent e){
                labelCreateUser.setBackground(new Color(14,128,136));
            }
        });
    }

    private void addLabelCreateVacination(){
        labelCreateVacination = new JLabel("CREAR VACUNA");
        labelCreateVacination.setBounds(0, 130, 200, 30);
        labelConfiguration(labelCreateVacination);
        addLabelCreateVacinationListener();
        add(labelCreateVacination);
    }

    private void addLabelCreateVacinationListener() {
        labelCreateVacination.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                   main.changePanelCenter("createVacination");
            }
            
            public void mouseEntered(MouseEvent e){
                labelCreateVacination.setBackground(new Color(14,128,110));
            }

            public void mouseExited(MouseEvent e){
                labelCreateVacination.setBackground(new Color(14,128,136));
            }
        });
    }

    private void addLabelVacination(){
        labelVacination = new JLabel("VACUNAR");
        labelVacination.setBounds(0, 180, 200, 30);
        labelConfiguration(labelVacination);
        createLabelVaccinationListener();
        add(labelVacination);
    }

    private void createLabelVaccinationListener() {
        labelVacination.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                main.changePanelCenter("vacination");
            }

            public void mouseEntered(MouseEvent e){
                labelVacination.setBackground(new Color(14,128,110));
            }

            public void mouseExited(MouseEvent e){
                labelVacination.setBackground(new Color(14,128,136));
            }
        });
    }

    private void addLabelHistory(){
        labelHistory = new JLabel("HISTORIA");
        labelHistory.setBounds(0, 230, 200, 30);
        labelConfiguration(labelHistory);
        createLabelHistoryListener();
        add(labelHistory);
    }

    private void createLabelHistoryListener() {
        labelHistory.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                main.changePanelCenter("history");
            }
            
            public void mouseEntered(MouseEvent e){
                labelHistory.setBackground(new Color(14,128,110));
            }

            public void mouseExited(MouseEvent e){
                labelHistory.setBackground(new Color(14,128,136));
            }
        });
    }

    private void addlabelMain(){
        labelMain = new JLabel("INICIO");
        labelMain.setBounds(0,280,200,30);
        labelConfiguration(labelMain);
        createLabelMainListener();
        add(labelMain);
    }

    private void createLabelMainListener() {
        labelMain.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                main.changePanelCenter("main");
            }
            
            public void mouseEntered(MouseEvent e){
                labelMain.setBackground(new Color(14,128,110));
            }

            public void mouseExited(MouseEvent e){
                labelMain.setBackground(new Color(14,128,136));
            }
        });
    }

    private void labelConfiguration(JLabel label){
        label.setForeground(Color.white);
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        label.setBackground(new Color(14,128,136));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

}
