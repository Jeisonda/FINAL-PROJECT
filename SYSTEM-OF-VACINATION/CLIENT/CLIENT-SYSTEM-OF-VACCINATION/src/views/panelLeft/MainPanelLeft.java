package views.panelLeft;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JLabel;
import javax.swing.JPanel;

import views.MainFrame;

public class MainPanelLeft extends JPanel {
    private JLabel label_create_user;
    private JLabel label_create_vacination;
    private JLabel label_vacination;
    private JLabel label_history;
    private JLabel label_main;

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
        label_create_user = new JLabel("CREAR USUARIO");
        label_create_user.setBounds(0,80,200,30);
        labelConfiguration(label_create_user);
        label_create_user.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                main.changePanelCenter("createUser");
            }
            
            public void mouseEntered(MouseEvent e){
                label_create_user.setBackground(new Color(14,128,110));
            }

            public void mouseExited(MouseEvent e){
                label_create_user.setBackground(new Color(14,128,136));
            }
        });
        add(label_create_user);
    }

    private void addLabelCreateVacination(){
        label_create_vacination = new JLabel("CREAR VACUNA");
        label_create_vacination.setBounds(0, 130, 200, 30);
        labelConfiguration(label_create_vacination);
        label_create_vacination.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                   main.changePanelCenter("createVacination");
            }
            
            public void mouseEntered(MouseEvent e){
                label_create_vacination.setBackground(new Color(14,128,110));
            }

            public void mouseExited(MouseEvent e){
                label_create_vacination.setBackground(new Color(14,128,136));
            }
        });
        add(label_create_vacination);
    }

    private void addLabelVacination(){
        label_vacination = new JLabel("VACUNAR");
        label_vacination.setBounds(0, 180, 200, 30);
        labelConfiguration(label_vacination);
        label_vacination.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                main.changePanelCenter("vacination");
            }

            public void mouseEntered(MouseEvent e){
                label_vacination.setBackground(new Color(14,128,110));
            }

            public void mouseExited(MouseEvent e){
                label_vacination.setBackground(new Color(14,128,136));
            }
        });
        add(label_vacination);
    }

    private void addLabelHistory(){
        label_history = new JLabel("HISTORIA");
        label_history.setBounds(0, 230, 200, 30);
        labelConfiguration(label_history);
        label_history.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                main.changePanelCenter("history");
            }
            
            public void mouseEntered(MouseEvent e){
                label_history.setBackground(new Color(14,128,110));
            }

            public void mouseExited(MouseEvent e){
                label_history.setBackground(new Color(14,128,136));
            }
        });
        add(label_history);

    }

    private void addlabelMain(){
        label_main = new JLabel("INICIO");
        label_main.setBounds(0,280,200,30);
        labelConfiguration(label_main);
        label_main.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                main.changePanelCenter("main");
            }
            
            public void mouseEntered(MouseEvent e){
                label_main.setBackground(new Color(14,128,110));
            }

            public void mouseExited(MouseEvent e){
                label_main.setBackground(new Color(14,128,136));
            }
        });
        add(label_main);
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
