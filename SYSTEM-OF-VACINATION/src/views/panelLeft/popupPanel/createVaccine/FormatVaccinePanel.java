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

    private JLabel label_NameVaccine;
    private JLabel label_manufacterName;
    private JLabel label_diseaseName;
    private JLabel label_expirationDate;
    private JLabel label_tipeVacination;
    private JLabel label_batchNumber;
    private JLabel label_dose;

    private JTextField txt_NameVacination;
    private JTextField txt_manufacterName;
    private JTextField txt_diseaseName;
    private JTextField txt_batchNumber;
    private JTextField txt_dose;
    private JTextField txt_vaccineId;

    private JDateChooser dateChooser;

    private JComboBox combo_tipeVaccine;

    private JButton btn_register;

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
        label_NameVaccine = new JLabel("Nombre Vacuna");
        labelConfiguration(label_NameVaccine);
        label_NameVaccine.setBounds(60, 20, 200, 20);
        add(label_NameVaccine);
    }

    private void addTxtFirstName() {
        txt_NameVacination = new JTextField();
        txt_NameVacination.setBounds(60, 50, 250, 25);
        add(txt_NameVacination);
    }

    private void addLabelMiddleName() {
        label_manufacterName = new JLabel("Nombre fabricante");
        labelConfiguration(label_manufacterName);
        label_manufacterName.setBounds(360, 20, 200, 25);
        add(label_manufacterName);
    }

    private void addTxtMiddleName() {
        txt_manufacterName = new JTextField();
        txt_manufacterName.setBounds(360, 50, 250, 25);
        add(txt_manufacterName);
    }

    private void addLabelLastName() {
        label_diseaseName = new JLabel("Enfermedad Objetivo");
        labelConfiguration(label_diseaseName);
        label_diseaseName.setBounds(60, 80, 200, 25);
        add(label_diseaseName);
    }

    private void addTxtLastName() {
        txt_diseaseName = new JTextField();
        txt_diseaseName.setBounds(60, 110, 250, 25);
        add(txt_diseaseName);
    }

    private void addLabelExpirationDate() {
        label_expirationDate = new JLabel("Fecha de vencimiento");
        labelConfiguration(label_expirationDate);
        label_expirationDate.setBounds(360, 80, 200, 25);
        add(label_expirationDate);
    }

    private void addtxtExpirationDate() {
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setBounds(360, 110, 250, 25);
        add(dateChooser);
    }

    private void addLabelVaccineType() {
        label_tipeVacination = new JLabel("Tipo de vacuna");
        labelConfiguration(label_tipeVacination);
        label_tipeVacination.setBounds(60, 140, 200, 25);
        add(label_tipeVacination);
    }

    private void addComboVaccineType() {
        String[] items = { "ARNm", "Inactiva", "Atenue", "Subunidad", "Toxoide", "Vector Viral" };
        combo_tipeVaccine = new JComboBox<>(items);
        combo_tipeVaccine.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        combo_tipeVaccine.setBounds(60, 170, 250, 25);
        add(combo_tipeVaccine);
    }

    private void addLabelDocumentNumber() {
        label_batchNumber = new JLabel("N° de lote");
        labelConfiguration(label_batchNumber);
        label_batchNumber.setBounds(360, 140, 200, 25);
        add(label_batchNumber);
    }

    private void addTxtDocumentoNumber() {
        txt_batchNumber = new JTextField();
        txt_batchNumber.setBounds(360, 170, 250, 25);
        add(txt_batchNumber);
    }

    private void addLabelAge() {
        label_dose = new JLabel("Dosis requeridas");
        labelConfiguration(label_dose);
        label_dose.setBounds(60, 200, 200, 25);
        add(label_dose);
    }

    private void addTxtAge() {
        txt_dose = new JTextField();
        txt_dose.setBounds(60, 230, 120, 25);
        add(txt_dose);
    }

    private void addButtonRegister() {
        btn_register = new JButton("Registrar");
        btn_register.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        btn_register.setBounds(270, 360, 150, 30);
        btn_register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn_register.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                presenter.createVaccine(txt_NameVacination.getText(), txt_manufacterName.getText(),
                        txt_diseaseName.getText(), dateChooser.getDate(),
                        combo_tipeVaccine.getSelectedItem().toString(), txt_batchNumber.getText(), txt_dose.getText());

                txt_NameVacination.setText("");
                txt_manufacterName.setText("");
                txt_diseaseName.setText("");
                dateChooser.setDate(null);
                combo_tipeVaccine.setSelectedIndex(0);
                txt_batchNumber.setText("");
                txt_dose.setText("");
             
                vaccine.update();

            }

        });
        add(btn_register);
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
