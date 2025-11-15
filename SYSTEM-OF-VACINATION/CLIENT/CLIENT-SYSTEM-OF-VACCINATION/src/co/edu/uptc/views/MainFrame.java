package co.edu.uptc.views;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import co.edu.uptc.control.ClientController;
import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.pojos.PersonData;
import co.edu.uptc.pojos.Vaccinate;
import co.edu.uptc.pojos.Vaccine;
import co.edu.uptc.views.panelCenter.MainPanelCenter;
import co.edu.uptc.views.panelLeft.MainPanelLeft;
import co.edu.uptc.views.panelLeft.popupPanel.createUser.CreateUserPanel;
import co.edu.uptc.views.panelLeft.popupPanel.createVaccine.CreateVaccinePanel;
import co.edu.uptc.views.panelLeft.popupPanel.history.HistoryPanel;
import co.edu.uptc.views.panelLeft.popupPanel.history.FormatHistoryPanel;
import co.edu.uptc.views.panelLeft.popupPanel.vacination.FormatVaccinationPanel;
import co.edu.uptc.views.panelLeft.popupPanel.vacination.VaccinatePanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;

public class MainFrame extends JFrame implements ViewInterface {
    private Image icon;
    private JPanel contentPanel;
    private MainPanelCenter panelCenter;
    private MainPanelLeft panelLeft;
    private CreateUserPanel createUserPanel;
    private CreateVaccinePanel createVacinationPanel;
    private VaccinatePanel vacinationPanel;
    private HistoryPanel historyPanel;
    private FormatHistoryPanel formatHistoryPanel;
    public FormatVaccinationPanel vaccine;
    public ClientController presenter;
    private String activePanelName;

    public MainFrame(ClientController presenter) {
        this.presenter = presenter;
        presenter.setView(this);
        frameConfiguration();
    }

    private void frameConfiguration() {
        setSize(940, 630);
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

    public void initComponents() {
        addContentPanel();
        addPanelLeft();
    }

    private void addPanelLeft() {
        panelLeft = new MainPanelLeft(this);
        add(panelLeft, BorderLayout.WEST);
    }

    private void addContentPanel() {
        contentPanel = new JPanel(new CardLayout());
        panelCenter = new MainPanelCenter();
        createUserPanel = new CreateUserPanel(this);
        vacinationPanel = new VaccinatePanel(this);
        vaccine = new FormatVaccinationPanel(vacinationPanel, this);
        vacinationPanel.setFormatPanel(vaccine);
        createVacinationPanel = new CreateVaccinePanel(vaccine, this);
        addContentPanel2();
    }

    private void addContentPanel2() {
        historyPanel = new HistoryPanel(this);
        formatHistoryPanel = historyPanel.getFormatPanel();
        contentPanel.add(panelCenter, "main");
        contentPanel.add(createUserPanel, "createUser");
        contentPanel.add(createVacinationPanel, "createVacination");
        contentPanel.add(historyPanel, "history");
        contentPanel.add(vacinationPanel, "vacination");
        add(contentPanel, BorderLayout.CENTER);
        changePanelCenter("main");
    }

    public void changePanelCenter(String panelName) {
        CardLayout layout = (CardLayout) contentPanel.getLayout();
        layout.show(contentPanel, panelName);
        this.activePanelName = panelName;
        revalidate();
        repaint();
    }

    public void listenerSearchUserPerformed(String document) {
        presenter.searchUser(document);
    }

    public void listenerCreateUserPerformed(String firstName, String middleName, String lastName, String secondLastName, String typeDoc, String document, Date bornDate, String phone, String email) {
        presenter.createUser(firstName, middleName, lastName, secondLastName, typeDoc, document, bornDate, phone, email);
    }

    public void listenerCreateVaccinePerformed(String name, String manuName, String disease, Date expDate, String vaccineType, String batchNumber, String dose) {
        presenter.createVaccine(name, manuName, disease, expDate, vaccineType, batchNumber, dose);
    }

    public void listenerApplyVaccinePerformed(String document, String vaccineName, Date date) {
        presenter.vaccined(document, vaccineName, date);
    }

    public void listenerFindHistoryPerformed(String document) {
        presenter.searchPersonById(document);
    }

    public void listenerRefreshVaccinesPerformed() {
        formatVaccinesRefresh();
    }

    public void listenerUpdateVaccinePerformed(int rowIndex, Vaccine vaccineData, Date applicationDate, long documentNumber) {
        presenter.updateVaccineFromTable(rowIndex, vaccineData, applicationDate, documentNumber);
    }

    public void listenerFindVaccineByNamePerformed(String vaccineName) {
        presenter.searchVaccineByName(vaccineName);
    }

    private void formatVaccinesRefresh() {
        if (vaccine != null) {
            vaccine.refreshComboFindVaccine();
        }
    }

    private void addCloseBehavior() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(
                        MainFrame.this,
                        "¿Desea cerrar la aplicación?",
                        "Confirmar salida",
                        JOptionPane.YES_NO_OPTION);
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

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showConfirmMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void fillUserLabels(PersonData person) {
        if ("history".equals(activePanelName) && formatHistoryPanel != null) {
            formatHistoryPanel.fillUserLabels(person);
        }
        else if ("vacination".equals(activePanelName) && vaccine != null) {
            vaccine.fillUserLabels(person);
        }
    }

    @Override
    public void fillVaccineTable(List<Vaccinate> vaccines) {
        if ("history".equals(activePanelName) && formatHistoryPanel != null) {
            formatHistoryPanel.fillVaccineTable(vaccines);
        }
    }

    @Override
    public void fillVaccineLabels(Vaccine vaccine) {
        if (this.vaccine != null) {
            this.vaccine.fillVaccineLabels(vaccine);
        }
    }

    @Override
    public void refreshComboFindVaccine() {
        if (vaccine != null) {
            vaccine.refreshComboFindVaccine();
        }
    }

    @Override
    public String askInput(String message) {
        return JOptionPane.showInputDialog(this, message);
    }
}
