package views.panelLeft.popupPanel.history;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeaderHistoryPanel extends JPanel{

    private JLabel label_header;

    public HeaderHistoryPanel(){      
        setPreferredSize(new Dimension(0,50));
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents(){
        addLabelHeader();
    }  

    private void addLabelHeader(){
        label_header = new JLabel("HISTORIA USUARIO");
        label_header.setSize(0,100);
        label_header.setFont(new Font("Comic Sans MS", Font.BOLD, 19));
        label_header.setHorizontalAlignment(JLabel.CENTER);
        label_header.setVerticalAlignment(JLabel.CENTER);
        add(label_header, BorderLayout.CENTER);
    }
}   

