package views.panelLeft.popupPanel.createVaccine;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import interfaces.ViewInterface;
import model.DateModel;
import model.UserModel;
import model.VaccineModel;
import presenter.Presenter;
import views.panelLeft.popupPanel.vacination.FormatVaccinationPanel;

public class FormatVaccinePanel extends JPanel implements ViewInterface {

    private Presenter presenter;

    private int arcWidth = 30;
    private int arcHeight = 30;

    private JLabel labelNameVaccine;
    private JLabel labelManufacterName;
    private JLabel labelDiseaseName;
    private JLabel labelExpirationDate;
    private JLabel labelTipeVacination;
    private JLabel labelBatchNumber;
    private JLabel label_dose;

    private JTextField txtNameVacination;
    private JTextField txtManufacterName;
    private JTextField txtDiseaseName;
    private JTextField txtBatchNumber;
    private JTextField txtDose;
    private JTextField txtVaccineId;

    private JDateChooser dateChooser;

    private JComboBox comboTipeVaccine;

    private JButton btnRegister;

    private FormatVaccinationPanel vaccine;

    public FormatVaccinePanel(FormatVaccinationPanel vaccine) {
        if (vaccine == null) {
            throw new IllegalArgumentException("FormatVaccinationPanel no puede ser null");
        }
        this.vaccine = vaccine;
        presenter = new Presenter(this);
        panelConfiguration();
    }

    private void panelConfiguration() {
        setBackground(new Color(220, 220, 220));
        setLayout(null);
        setOpaque(false);
        initComponents();
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        g2.dispose();
        super.paintComponent(g);
    }

    private void initComponents() {
        addLabelNameVacination();
        addTxtFirstName();
        addLabelMiddleName();
        addTxtMiddleName();
        addLabelLastName();
        addTxtLastName();
        addLabelExpirationDate();
        addtxtExpirationDate();
        addLabelVaccineType();
        addComboVaccineType();
        addLabelDocumentNumber();
        addTxtDocumentoNumber();
        addLabelAge();
        addTxtAge();

        addButtonRegister();
    }

    private void addLabelNameVacination() {
        labelNameVaccine = new JLabel("Nombre Vacuna");
        labelConfiguration(labelNameVaccine);
        labelNameVaccine.setBounds(60, 20, 200, 20);
        add(labelNameVaccine);
    }

    private void addTxtFirstName() {
        txtNameVacination = new JTextField();
        txtNameVacination.setBounds(60, 50, 250, 25);
        add(txtNameVacination);
    }

    private void addLabelMiddleName() {
        labelManufacterName = new JLabel("Nombre fabricante");
        labelConfiguration(labelManufacterName);
        labelManufacterName.setBounds(360, 20, 200, 25);
        add(labelManufacterName);
    }

    private void addTxtMiddleName() {
        txtManufacterName = new JTextField();
        txtManufacterName.setBounds(360, 50, 250, 25);
        add(txtManufacterName);
    }

    private void addLabelLastName() {
        labelDiseaseName = new JLabel("Enfermedad Objetivo");
        labelConfiguration(labelDiseaseName);
        labelDiseaseName.setBounds(60, 80, 200, 25);
        add(labelDiseaseName);
    }

    private void addTxtLastName() {
        txtDiseaseName = new JTextField();
        txtDiseaseName.setBounds(60, 110, 250, 25);
        add(txtDiseaseName);
    }

    private void addLabelExpirationDate() {
        labelExpirationDate = new JLabel("Fecha de vencimiento");
        labelConfiguration(labelExpirationDate);
        labelExpirationDate.setBounds(360, 80, 200, 25);
        add(labelExpirationDate);
    }

    private void addtxtExpirationDate() {
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setBounds(360, 110, 250, 25);
        add(dateChooser);
    }

    private void addLabelVaccineType() {
        labelTipeVacination = new JLabel("Tipo de vacuna");
        labelConfiguration(labelTipeVacination);
        labelTipeVacination.setBounds(60, 140, 200, 25);
        add(labelTipeVacination);
    }

    private void addComboVaccineType() {
        String[] items = { "ARNm", "Inactiva", "Atenue", "Subunidad", "Toxoide", "Vector Viral" };
        comboTipeVaccine = new JComboBox<>(items);
        comboTipeVaccine.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        comboTipeVaccine.setBounds(60, 170, 250, 25);
        add(comboTipeVaccine);
    }

    private void addLabelDocumentNumber() {
        labelBatchNumber = new JLabel("N° de lote");
        labelConfiguration(labelBatchNumber);
        labelBatchNumber.setBounds(360, 140, 200, 25);
        add(labelBatchNumber);
    }

    private void addTxtDocumentoNumber() {
        txtBatchNumber = new JTextField();
        txtBatchNumber.setBounds(360, 170, 250, 25);
        add(txtBatchNumber);
    }

    private void addLabelAge() {
        label_dose = new JLabel("Dosis requeridas");
        labelConfiguration(label_dose);
        label_dose.setBounds(60, 200, 200, 25);
        add(label_dose);
    }

    private void addTxtAge() {
        txtDose = new JTextField();
        txtDose.setBounds(60, 230, 120, 25);
        add(txtDose);
    }

    private void addButtonRegister() {
        btnRegister = new JButton("Registrar");
        btnRegister.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        btnRegister.setBounds(270, 360, 150, 30);
        btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegister.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                presenter.createVaccine(txtNameVacination.getText(), txtManufacterName.getText(),
                        txtDiseaseName.getText(), dateChooser.getDate(),
                        comboTipeVaccine.getSelectedItem().toString(), txtBatchNumber.getText(), txtDose.getText());

                txtNameVacination.setText("");
                txtManufacterName.setText("");
                txtDiseaseName.setText("");
                dateChooser.setDate(null);
                comboTipeVaccine.setSelectedIndex(0);
                txtBatchNumber.setText("");
                txtDose.setText("");
             
                vaccine.update();

            }

        });
        add(btnRegister);
    }

    private void labelConfiguration(JLabel label) {
        label.setForeground(Color.black);
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        label.setBackground(new Color(220, 220, 220));
        label.setOpaque(true);
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "UNSUCCES", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showConfirmMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "SUCCES", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void fillUserLabels(UserModel user) {
    }

    @Override
    public void fillVaccineTable(List<DateModel> vaccines) {

    }

    @Override
    public void fillVaccineLabels(VaccineModel vaccine) {
    }

    @Override
    public void refreshComboFindVaccine() {
        if (vaccine != null) {
            vaccine.refreshComboFindVaccine();
        }
    }
}
