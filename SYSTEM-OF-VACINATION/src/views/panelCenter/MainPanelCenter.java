package views.panelCenter;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanelCenter extends JPanel{
 
    private JLabel label_image;
    private Image iconOriginal, scaletImage;
    private JLabel label_Title;
    
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
        label_image = new JLabel();
        label_image.setIcon(new ImageIcon(scaletImage)); 
        label_image.setBounds(0,0,740,592);
        add(label_image);
    }
    
    private void imageConfiguration(){
        iconOriginal = new ImageIcon("src/images/imagenPrincipal.png").getImage();
        scaletImage = iconOriginal.getScaledInstance(740, 592, Image.SCALE_SMOOTH);
    }

    private void addTitleLabel(){
        label_Title = new JLabel("<html><div style='text-align: center;'>CLÍNICA DE VACUNAS<br>ATENAS</div></html>");
        label_Title.setBounds(180,440,400,90);
        label_Title.setFont(new Font("Comic Sans MS", Font.PLAIN, 29));
        label_Title.setOpaque(true);
        label_Title.setHorizontalAlignment(JLabel.CENTER);
        label_Title.setVerticalAlignment(JLabel.CENTER);
        label_Title.setBackground(new Color(204,255,255));
        add(label_Title);

    }
}
