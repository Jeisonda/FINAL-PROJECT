package views.panelLeft.popupPanel.createUser;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class FormatUserPanel extends JPanel implements ViewInterface {

    private int arcWidth = 50;
    private int arcHeight = 50;

    private JLabel label_firstName;
    private JLabel label_middleName;
    private JLabel label_lastName;
    private JLabel label_secondLastName;
    private JLabel label_tipeDocument;
    private JLabel label_documentNumber;
    private JLabel label_age;
    private JLabel label_phoneNumber;
    private JLabel label_email;

    private JTextField txt_firstName;
    private JTextField txt_middleName;
    private JTextField txt_lastName;
    private JTextField txt_secondLastName;
    private JTextField txt_documentNumber;
    private JTextField txt_phoneNumber;
    private JTextField txt_email;

    private JDateChooser dateChooser;

    private JComboBox combo_tipeDocument;

    private JButton btn_register;

    private Presenter presenter;

    public FormatUserPanel() {
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
        addLabelFirsName();
        addTxtFirstName();
        addLabelMiddleName();
        addTxtMiddleName();
        addLabelLastName();
        addTxtLastName();
        addLabelSecondLastName();
        addtxtSecondLastName();
        addLabelTipeDocumento();
        addComboTipeDocument();
        addLabelDocumentNumber();
        addTxtDocumentoNumber();
        addLabelAge();
        addTxtAge();
        addLabelPhoneNumber();
        addTxtPhoneNumber();
        addLabelEmail();
        addTxtEmail();
        addButtonRegister();
    }

    private void addLabelFirsName() {
        label_firstName = new JLabel("Primer Nombre");
        labelConfiguration(label_firstName);
        label_firstName.setBounds(60, 20, 200, 20);
        add(label_firstName);
    }

    private void addTxtFirstName() {
        txt_firstName = new JTextField();
        txt_firstName.setBounds(60, 50, 250, 25);
        add(txt_firstName);
    }

    private void addLabelMiddleName() {
        label_middleName = new JLabel("Segundo Nombre");
        labelConfiguration(label_middleName);
        label_middleName.setBounds(360, 20, 200, 25);
        add(label_middleName);
    }

    private void addTxtMiddleName() {
        txt_middleName = new JTextField();
        txt_middleName.setBounds(360, 50, 250, 25);
        add(txt_middleName);
    }

    private void addLabelLastName() {
        label_lastName = new JLabel("Primer Apellido");
        labelConfiguration(label_lastName);
        label_lastName.setBounds(60, 80, 200, 20);
        add(label_lastName);
    }

    private void addTxtLastName() {
        txt_lastName = new JTextField();
        txt_lastName.setBounds(60, 110, 250, 25);
        add(txt_lastName);
    }

    private void addLabelSecondLastName() {
        label_secondLastName = new JLabel("Segundo Apellido");
        labelConfiguration(label_secondLastName);
        label_secondLastName.setBounds(360, 80, 200, 25);
        add(label_secondLastName);
    }

    private void addtxtSecondLastName() {
        txt_secondLastName = new JTextField();
        txt_secondLastName.setBounds(360, 110, 250, 25);
        add(txt_secondLastName);
    }

    private void addLabelTipeDocumento() {
        label_tipeDocument = new JLabel("Tipo de documento");
        labelConfiguration(label_tipeDocument);
        label_tipeDocument.setBounds(60, 140, 200, 25);
        add(label_tipeDocument);
    }

    private void addComboTipeDocument() {
        String[] items = {"CC", "TI ", "PAS"};
        combo_tipeDocument = new JComboBox<>(items);
        combo_tipeDocument.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        combo_tipeDocument.setBounds(60, 170, 250, 25);
        add(combo_tipeDocument);
    }

    private void addLabelDocumentNumber() {
        label_documentNumber = new JLabel("N° documento");
        labelConfiguration(label_documentNumber);
        label_documentNumber.setBounds(360, 140, 200, 25);
        add(label_documentNumber);
    }

    private void addTxtDocumentoNumber() {
        txt_documentNumber = new JTextField();
        txt_documentNumber.setBounds(360, 170, 250, 25);
        add(txt_documentNumber);
    }

    private void addLabelAge() {
        label_age = new JLabel("Fecha de nacimiento");
        labelConfiguration(label_age);
        label_age.setBounds(60, 200, 200, 25);
        add(label_age);
    }

    private void addTxtAge() {
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setBounds(60, 230, 100, 25);
        add(dateChooser);
    }

    private void addLabelPhoneNumber() {
        label_phoneNumber = new JLabel("Telefono");
        labelConfiguration(label_phoneNumber);
        label_phoneNumber.setBounds(360, 200, 200, 25);
        add(label_phoneNumber);
    }

    private void addTxtPhoneNumber() {
        txt_phoneNumber = new JTextField();
        txt_phoneNumber.setBounds(360, 230, 250, 25);
        add(txt_phoneNumber);
    }

    private void addLabelEmail() {
        label_email = new JLabel("Correo");
        labelConfiguration(label_email);
        label_email.setBounds(60, 260, 200, 25);
        add(label_email);
    }

    private void addTxtEmail() {
        txt_email = new JTextField();
        txt_email.setBounds(60, 290, 550, 25);
        add(txt_email);
    }

    private void addButtonRegister() {
        btn_register = new JButton("Registrar");
        btn_register.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        btn_register.setBounds(270, 360, 150, 30);
        btn_register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn_register.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                presenter.createUser(txt_firstName.getText(), txt_middleName.getText(), txt_lastName.getText(),
                        txt_secondLastName.getText(), combo_tipeDocument.getSelectedItem().toString(),
                        txt_documentNumber.getText(),
                        dateChooser.getDate(), txt_phoneNumber.getText(), txt_email.getText());

                txt_firstName.setText("");
                txt_middleName.setText("");
                txt_lastName.setText("");
                txt_secondLastName.setText("");
                combo_tipeDocument.setSelectedIndex(0);
                txt_documentNumber.setText("");
                dateChooser.setDate(null); 
                txt_phoneNumber.setText("");
                txt_email.setText("");
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
    }
}

