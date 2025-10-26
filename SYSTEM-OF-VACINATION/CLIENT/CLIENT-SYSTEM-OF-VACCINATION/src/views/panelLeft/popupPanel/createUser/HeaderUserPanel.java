package views.panelLeft.popupPanel.createUser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeaderUserPanel extends JPanel{
    private JLabel labelHeader;

    public HeaderUserPanel(){
        setPreferredSize(new Dimension(0,50));
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents(){
        addLabelHeader();
    }  

    private void addLabelHeader(){
        labelHeader = new JLabel("CREAR USUARIO");
        labelHeader.setSize(0,100);
        labelHeader.setFont(new Font("Comic Sans MS", Font.BOLD, 19));
        labelHeader.setHorizontalAlignment(JLabel.CENTER);
        labelHeader.setVerticalAlignment(JLabel.CENTER);
        add(labelHeader, BorderLayout.CENTER);
    }
}   
