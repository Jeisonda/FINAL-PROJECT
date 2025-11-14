package co.edu.uptc.views.panelLeft.popupPanel.createVaccine;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import co.edu.uptc.views.MainFrame;
import co.edu.uptc.views.panelLeft.popupPanel.vacination.FormatVaccinationPanel;

public class CreateVaccinePanel extends JPanel{
    private HeaderVaccinePanel headerVacinationPanel;
    private FormatVaccinePanel formatVacinePanel;
    private FormatVaccinationPanel vaccine;
    private MainFrame mainFrame;

    public CreateVaccinePanel(FormatVaccinationPanel vaccine, MainFrame mainFrame){
        this.vaccine = vaccine;
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents(){
        addHeader();
        addFormat();
        addPanelDown();
        addPanelLeft();
        addPanelRigth();
    }

    private void addHeader(){
        headerVacinationPanel = new HeaderVaccinePanel();
        add(headerVacinationPanel, BorderLayout.NORTH);
    }

    private void addFormat(){
        formatVacinePanel = new FormatVaccinePanel(vaccine, mainFrame);
        add(formatVacinePanel, BorderLayout.CENTER);
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
