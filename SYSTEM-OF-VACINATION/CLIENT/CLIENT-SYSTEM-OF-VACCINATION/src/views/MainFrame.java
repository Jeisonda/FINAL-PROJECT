package views;

import javax.swing.JFrame;
import javax.swing.JPanel;

import views.panelCenter.MainPanelCenter;
import views.panelLeft.MainPanelLeft;
import views.panelLeft.popupPanel.createUser.CreateUserPanel;
import views.panelLeft.popupPanel.createVaccine.CreateVaccinePanel;
import views.panelLeft.popupPanel.history.HistoryPanel;
import views.panelLeft.popupPanel.vacination.FormatVaccinationPanel;
import views.panelLeft.popupPanel.vacination.VaccinatePanel;

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
        setSize(940,630);
        setTitle("ATENAS");
        icon = Toolkit.getDefaultToolkit().getImage("src/images/partenon 64x64.png");
        setIconImage(icon);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents(); 
    }
    
    public void initComponents(){
        addContentPanel();
        addPanelLeft();
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
        vacinationPanel.setFormatPanel(vaccine);
        createVacinationPanel = new CreateVaccinePanel(vaccine);
        historyPanel = new HistoryPanel();

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
