package co.edu.uptc.views.panelCenter;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanelCenter extends JPanel{
 
    private JLabel labelImage;
    private Image iconOriginal;
    private Image scaletImage;
    private JLabel labelTitle;
    
    public MainPanelCenter(){
        panelConfiguration();
    }

    private void panelConfiguration(){
        initComponents();
        setLayout(null);
    }

    private void initComponents(){
        addTitleLabel();
        addImage();
    }

    private void addImage(){
        imageConfiguration();
        labelImage = new JLabel();
        labelImage.setIcon(new ImageIcon(scaletImage)); 
        labelImage.setBounds(0,0,740,592);
        add(labelImage);
    }
    
    private void imageConfiguration(){
        iconOriginal = new ImageIcon("images/imagenPrincipal.png").getImage();
        scaletImage = iconOriginal.getScaledInstance(740, 592, Image.SCALE_SMOOTH);
    }

    private void addTitleLabel(){
        labelTitle = new JLabel("<html><div style='text-align: center;'>CLÍNICA DE VACUNAS<br>ATENAS</div></html>");
        labelTitle.setBounds(180,440,400,90);
        labelTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 29));
        labelTitle.setOpaque(true);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setVerticalAlignment(JLabel.CENTER);
        labelTitle.setBackground(new Color(204,255,255));
        add(labelTitle);

    }
}
