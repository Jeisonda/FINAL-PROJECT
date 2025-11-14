package co.edu.uptc.views.panelLeft.popupPanel.createUser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

public class CreateUserPanel extends JPanel{
    private HeaderUserPanel headerUserPanel;
    private FormatUserPanel formatUserPanel;

    public CreateUserPanel() {
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents(){
        addPanelHeader();
        addPanelFormat();
        addPanelDown();
        addPanelRigth();
        addPanelLeft();
    }

    private void addPanelHeader(){
        headerUserPanel = new HeaderUserPanel();
        add(headerUserPanel, BorderLayout.NORTH);
    }

    private void addPanelFormat(){
        formatUserPanel = new FormatUserPanel();
        add(formatUserPanel, BorderLayout.CENTER);
    }

    private void addPanelLeft(){
        JPanel panelLeft = new JPanel();
        panelLeft.setPreferredSize(new Dimension(25,0));
        add(panelLeft, BorderLayout.WEST);
    }

    private void addPanelDown(){
       JPanel panelDown = new JPanel();
       panelDown.setPreferredSize(new Dimension(0,25));
       add(panelDown, BorderLayout.SOUTH);
    }

    private void addPanelRigth(){
        JPanel panelRigth = new JPanel();
        panelRigth.setPreferredSize(new Dimension(25,0));
        add(panelRigth, BorderLayout.EAST);
    }
}
