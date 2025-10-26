package views.panelLeft.popupPanel.history;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeaderHistoryPanel extends JPanel{

    private JLabel labelHeader;

    public HeaderHistoryPanel(){      
        setPreferredSize(new Dimension(0,50));
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents(){
        addLabelHeader();
    }  

    private void addLabelHeader(){
        labelHeader = new JLabel("HISTORIA USUARIO");
        labelHeader.setSize(0,100);
        labelHeader.setFont(new Font("Comic Sans MS", Font.BOLD, 19));
        labelHeader.setHorizontalAlignment(JLabel.CENTER);
        labelHeader.setVerticalAlignment(JLabel.CENTER);
        add(labelHeader, BorderLayout.CENTER);
    }
}   

