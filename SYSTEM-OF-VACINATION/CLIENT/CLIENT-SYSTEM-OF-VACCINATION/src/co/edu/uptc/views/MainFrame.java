package co.edu.uptc.views;

import javax.swing.JFrame;
import javax.swing.JPanel;

import co.edu.uptc.views.panelCenter.MainPanelCenter;
import co.edu.uptc.views.panelLeft.MainPanelLeft;
import co.edu.uptc.views.panelLeft.popupPanel.createUser.CreateUserPanel;
import co.edu.uptc.views.panelLeft.popupPanel.createVaccine.CreateVaccinePanel;
import co.edu.uptc.views.panelLeft.popupPanel.history.HistoryPanel;
import co.edu.uptc.views.panelLeft.popupPanel.vacination.FormatVaccinationPanel;
import co.edu.uptc.views.panelLeft.popupPanel.vacination.VaccinatePanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Toolkit;

public class MainFrame extends JFrame{
    private Image icon;
    private JPanel contentPanel;
    private MainPanelCenter panelCenter;
    private MainPanelLeft panelLeft;
    private CreateUserPanel createUserPanel;
    private CreateVaccinePanel createVacinationPanel;
    private VaccinatePanel vacinationPanel;
    private HistoryPanel historyPanel;
    public FormatVaccinationPanel vaccine;

    public MainFrame(){
        frameConfiguration();
    }
    
    private void frameConfiguration(){
        System.out.println("llegue");
        setSize(940,630);
        setTitle("ATENAS");
        icon = Toolkit.getDefaultToolkit().getImage("src/images/partenon 64x64.png");
        setIconImage(icon);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        System.out.println("he");
        initComponents(); 
    }
    
    public void initComponents(){
        addContentPanel();
        System.out.println("aca");
        addPanelLeft();
        System.out.println("estoy");
    }
    
    private void addPanelLeft(){
        panelLeft = new MainPanelLeft(this);
        add(panelLeft, BorderLayout.WEST);
    }
    
    private void addContentPanel(){
        contentPanel = new JPanel(new CardLayout());
        panelCenter = new MainPanelCenter();
        createUserPanel = new CreateUserPanel();
        vacinationPanel = new VaccinatePanel(this);
        vaccine = new FormatVaccinationPanel(vacinationPanel);
        System.out.println("d");
        vacinationPanel.setFormatPanel(vaccine);
        System.out.println("a");
        createVacinationPanel = new CreateVaccinePanel(vaccine);
        System.out.println("b");
        historyPanel = new HistoryPanel();
        System.out.println("n");

        contentPanel.add(panelCenter, "main");
        contentPanel.add(createUserPanel, "createUser");
        contentPanel.add(createVacinationPanel, "createVacination");
        contentPanel.add(historyPanel, "history");
        
        contentPanel.add(vacinationPanel,"vacination");
        add(contentPanel, BorderLayout.CENTER);
        changePanelCenter("main");
        
    }
    public void changePanelCenter(String panelName){
        CardLayout layout = (CardLayout) contentPanel.getLayout();
        layout.show(contentPanel, panelName);
        revalidate();
        repaint();
    }

}
