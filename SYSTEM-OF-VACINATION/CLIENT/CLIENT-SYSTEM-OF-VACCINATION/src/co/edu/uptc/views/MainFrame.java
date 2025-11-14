package co.edu.uptc.views;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.pojos.PersonData;
import co.edu.uptc.pojos.Vaccinate;
import co.edu.uptc.pojos.Vaccine;
import co.edu.uptc.presenter.Presenter;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class MainFrame extends JFrame implements ViewInterface{
    private Image icon;
    private JPanel contentPanel;
    private MainPanelCenter panelCenter;
    private MainPanelLeft panelLeft;
    private CreateUserPanel createUserPanel;
    private CreateVaccinePanel createVacinationPanel;
    private VaccinatePanel vacinationPanel;
    private HistoryPanel historyPanel;
    public FormatVaccinationPanel vaccine;
    private Presenter presenter;

    public MainFrame(Presenter presenter){
        this.presenter = presenter;
        frameConfiguration();
    }
    
    private void frameConfiguration(){
        System.out.println("llegue");
        selectPort();
        setSize(940,630);
        setTitle("ATENAS");
        icon = Toolkit.getDefaultToolkit().getImage("images/partenon.png");
        setIconImage(icon);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        initComponents(); 
        addCloseBehavior();
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

    private void addCloseBehavior() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(
                    MainFrame.this,
                    "¿Desea cerrar la aplicación?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION
                );

                if (option == JOptionPane.YES_OPTION) {
                    try {
                        presenter.sendCloseMessage();
                        presenter.closeConnection(); 
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        dispose();
                        System.exit(0);
                    }
                }
            }
        });
    }

    private void selectPort(){
        String port = JOptionPane.showInputDialog(null, "Ingrese el puerto");
        String host = JOptionPane.showInputDialog(null, "Ingrese el host");
        presenter.selectHostAndPort(host, Integer.parseInt(port));
        
    }

    @Override
    public void showErrorMessage(String message) {
    }

    @Override
    public void showConfirmMessage(String message) {
    }

    @Override
    public void fillUserLabels(PersonData person) {
    }

    @Override
    public void fillVaccineTable(List<Vaccinate> vaccines) {
    }

    @Override
    public void fillVaccineLabels(Vaccine vaccine) {
    }

    @Override
    public void refreshComboFindVaccine() {
    }
}
