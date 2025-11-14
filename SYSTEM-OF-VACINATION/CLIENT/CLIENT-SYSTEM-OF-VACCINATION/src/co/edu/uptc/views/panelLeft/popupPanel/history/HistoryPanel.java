package co.edu.uptc.views.panelLeft.popupPanel.history;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import co.edu.uptc.views.MainFrame;

public class HistoryPanel extends JPanel{

    private FormatHistoryPanel formatHistoryPanel;
    private HeaderHistoryPanel headerHistoryPanel;

    public HistoryPanel(MainFrame mainFrame){
        setLayout(new BorderLayout());
        initComponents(mainFrame);
    }

    private void initComponents(MainFrame mainFrame){
        addPanelHeader();
        addPanelCenter(mainFrame);
        addPanelDown();
        addPanelLeft();
        addPanelRigth();
    }

    private void addPanelHeader(){
        headerHistoryPanel = new HeaderHistoryPanel();
        add(headerHistoryPanel, BorderLayout.NORTH);
    }

    private void addPanelCenter(MainFrame mainFrame){
        formatHistoryPanel = new FormatHistoryPanel(mainFrame);
        add(formatHistoryPanel, BorderLayout.CENTER);
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

    public FormatHistoryPanel getFormatPanel() {
        return formatHistoryPanel;
    }
}
