package co.edu.uptc.views.panelLeft.popupPanel.vacination;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import co.edu.uptc.views.MainFrame;


public class VaccinatePanel extends JPanel {

    private HeaderVaccinatePanel headerVaccinatePanel;
    private FormatVaccinationPanel vaccinationPanel;
    private MainFrame main;

    public VaccinatePanel(MainFrame main) {
        this.main = main;
        setLayout(new BorderLayout());
        initComponents();
    }

    public void updateData() {
        main.revalidate();
        main.repaint();
    }

    public void initComponents() {
        addPanelHeader();
        addPanelDown();
        addPanelLeft();
        addPanelRigth();
    }

    public void setFormatPanel(FormatVaccinationPanel formatPanel) {
        this.vaccinationPanel = formatPanel;
        add(formatPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void addPanelHeader() {
        headerVaccinatePanel = new HeaderVaccinatePanel();
        add(headerVaccinatePanel, BorderLayout.NORTH);
    }

    private void addPanelLeft() {
        JPanel panelLeft = new JPanel();
        panelLeft.setPreferredSize(new Dimension(25, 0));
        add(panelLeft, BorderLayout.WEST);
    }

    private void addPanelDown() {
        JPanel panelDown = new JPanel();
        panelDown.setPreferredSize(new Dimension(0, 25));
        add(panelDown, BorderLayout.SOUTH);
    }

    private void addPanelRigth() {
        JPanel panelRigth = new JPanel();
        panelRigth.setPreferredSize(new Dimension(25, 0));
        add(panelRigth, BorderLayout.EAST);
    }
}