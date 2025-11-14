package co.edu.uptc.views.panelLeft.popupPanel.createUser;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import co.edu.uptc.views.MainFrame;

public class FormatUserPanel extends JPanel {
    public FormatUserPanel(){
    }

    private int arcWidth = 50;
    private int arcHeight = 50;

    private JLabel labelFirstName;
    private JLabel labelMiddleName;
    private JLabel labelLastName;
    private JLabel labelSecondLastName;
    private JLabel labelTipeDocument;
    private JLabel labelDocumentNumber;
    private JLabel labelAge;
    private JLabel labelPhoneNumber;
    private JLabel labelEmail;

    private JTextField txtFirstName;
    private JTextField txtMiddleName;
    private JTextField txtLastName;
    private JTextField txtSecondLastName;
    private JTextField txtDocumentNumber;
    private JTextField txtPhoneNumber;
    private JTextField txtEmail;

    private JDateChooser dateChooser;

    private javax.swing.JComboBox<String> comboTypeDocument;

    private JButton btnRegister;

    private MainFrame mainFrame;

    public FormatUserPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
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
        labelFirstName = new JLabel("Primer Nombre");
        labelConfiguration(labelFirstName);
        labelFirstName.setBounds(60, 20, 200, 20);
        add(labelFirstName);
    }

    private void addTxtFirstName() {
        txtFirstName = new JTextField();
        txtFirstName.setBounds(60, 50, 250, 25);
        add(txtFirstName);
    }

    private void addLabelMiddleName() {
        labelMiddleName = new JLabel("Segundo Nombre");
        labelConfiguration(labelMiddleName);
        labelMiddleName.setBounds(360, 20, 200, 25);
        add(labelMiddleName);
    }

    private void addTxtMiddleName() {
        txtMiddleName = new JTextField();
        txtMiddleName.setBounds(360, 50, 250, 25);
        add(txtMiddleName);
    }

    private void addLabelLastName() {
        labelLastName = new JLabel("Primer Apellido");
        labelConfiguration(labelLastName);
        labelLastName.setBounds(60, 80, 200, 20);
        add(labelLastName);
    }

    private void addTxtLastName() {
        txtLastName = new JTextField();
        txtLastName.setBounds(60, 110, 250, 25);
        add(txtLastName);
    }

    private void addLabelSecondLastName() {
        labelSecondLastName = new JLabel("Segundo Apellido");
        labelConfiguration(labelSecondLastName);
        labelSecondLastName.setBounds(360, 80, 200, 25);
        add(labelSecondLastName);
    }

    private void addtxtSecondLastName() {
        txtSecondLastName = new JTextField();
        txtSecondLastName.setBounds(360, 110, 250, 25);
        add(txtSecondLastName);
    }

    private void addLabelTipeDocumento() {
        labelTipeDocument = new JLabel("Tipo de documento");
        labelConfiguration(labelTipeDocument);
        labelTipeDocument.setBounds(60, 140, 200, 25);
        add(labelTipeDocument);
    }

    private void addComboTipeDocument() {
        String[] items = {"CC", "TI ", "PAS"};
        comboTypeDocument = new javax.swing.JComboBox<>(items);
        comboTypeDocument.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        comboTypeDocument.setBounds(60, 170, 250, 25);
        add(comboTypeDocument);
    }

    private void addLabelDocumentNumber() {
        labelDocumentNumber = new JLabel("N° documento");
        labelConfiguration(labelDocumentNumber);
        labelDocumentNumber.setBounds(360, 140, 200, 25);
        add(labelDocumentNumber);
    }

    private void addTxtDocumentoNumber() {
        txtDocumentNumber = new JTextField();
        txtDocumentNumber.setBounds(360, 170, 250, 25);
        add(txtDocumentNumber);
    }

    private void addLabelAge() {
        labelAge = new JLabel("Fecha de nacimiento");
        labelConfiguration(labelAge);
        labelAge.setBounds(60, 200, 200, 25);
        add(labelAge);
    }

    private void addTxtAge() {
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setBounds(60, 230, 100, 25);
        add(dateChooser);
    }

    private void addLabelPhoneNumber() {
        labelPhoneNumber = new JLabel("Telefono");
        labelConfiguration(labelPhoneNumber);
        labelPhoneNumber.setBounds(360, 200, 200, 25);
        add(labelPhoneNumber);
    }

    private void addTxtPhoneNumber() {
        txtPhoneNumber = new JTextField();
        txtPhoneNumber.setBounds(360, 230, 250, 25);
        add(txtPhoneNumber);
    }

    private void addLabelEmail() {
        labelEmail = new JLabel("Correo");
        labelConfiguration(labelEmail);
        labelEmail.setBounds(60, 260, 200, 25);
        add(labelEmail);
    }

    private void addTxtEmail() {
        txtEmail = new JTextField();
        txtEmail.setBounds(60, 290, 550, 25);
        add(txtEmail);
    }

    private void addButtonRegister() {
        btnRegister = new JButton("Registrar");
        btnRegister.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        btnRegister.setBounds(270, 360, 150, 30);
        btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegister.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                mainFrame.listenerCreateUserPerformed(txtFirstName.getText(), txtMiddleName.getText(), txtLastName.getText(),
                        txtSecondLastName.getText(), comboTypeDocument.getSelectedItem().toString(),
                        txtDocumentNumber.getText(),
                        dateChooser.getDate(), txtPhoneNumber.getText(), txtEmail.getText());

                txtFirstName.setText("");
                txtMiddleName.setText("");
                txtLastName.setText("");
                txtSecondLastName.setText("");
                comboTypeDocument.setSelectedIndex(0);
                txtDocumentNumber.setText("");
                dateChooser.setDate(null);
                txtPhoneNumber.setText("");
                txtEmail.setText("");
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
}
