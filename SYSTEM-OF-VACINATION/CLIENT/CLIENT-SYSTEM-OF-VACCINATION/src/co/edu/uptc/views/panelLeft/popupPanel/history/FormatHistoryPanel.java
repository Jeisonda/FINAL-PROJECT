package co.edu.uptc.views.panelLeft.popupPanel.history;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.pojos.Person;
import co.edu.uptc.pojos.PersonData;
import co.edu.uptc.pojos.Vaccinate;
import co.edu.uptc.pojos.Vaccine;
import co.edu.uptc.presenter.Presenter;

public class FormatHistoryPanel extends JPanel implements ViewInterface {

    private int arcWidth = 50;
    private int arcHeight = 50;

    private Presenter presenter;

    private Image iconOriginal, scaletImage;

    private PanelTable panelTable;

    private JLabel labelFullName;
    private JLabel labelFullNameContainer;
    private JLabel labelDocumentNumber;
    private JLabel labelDocumentNumberContainer;
    private JLabel labelTipeDocument;
    private JLabel labelTipeDocumentContainer;
    private JLabel labelEmail;
    private JLabel labelEmailContainer;
    private JLabel labelAge;
    private JLabel labelAgeContainer;

    private JLabel labelFindUser;

    private JTextField txtFindName;

    private boolean placeholderActivo = true;

    public FormatHistoryPanel() {
        presenter = new Presenter(this);
        setBackground(new Color(220, 220, 220));
        setLayout(null);
        setOpaque(false);
        initComponents();
    }

    private void initComponents() {
        addTxtFindUser();
        addLabelSearchUser();
        addLabelFullName();
        addLabelFullNameContainer();
        addLabelDocumentoNumber();
        addLabelDocumentoNumberContainer();
        addLabelTipeDocument();
        addLabelTipeDocumentContainer();
        addLabelAge();
        addLabelAgeContainer();
        addLabelEmail();
        addLabelEmailContainer();
        addPanelTable();
        addLabelVaccine();
    }

    private void addTxtFindUser() {
        txtFindName = new JTextField("Ingrese N° de documento");
        addEventsTxfUser();
        txtFindName.setBounds(40, 10, 200, 25);
        txtFindName.setForeground(Color.gray);
        txtFindName.setEditable(false);
        add(txtFindName);
    }

    private void addEventsTxfUser() {
        txtFindName.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                txtFindName.setText("");
                txtFindName.setForeground(Color.BLACK);
                txtFindName.setEditable(true);
            }
        });
        txtFindName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (txtFindName.getText().isEmpty()) {
                    txtFindName.setText("Ingrese N° de documento");
                    txtFindName.setForeground(Color.GRAY);
                    txtFindName.setEditable(false);
                    placeholderActivo = true;
                }
            }
        });
        txtFindName.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (placeholderActivo) {
                    txtFindName.setText("");
                    txtFindName.setEditable(true);
                    txtFindName.setForeground(Color.black);
                    placeholderActivo = false;
                }
            }
        });
    }

    private void addLabelSearchUser() {
        imageConfiguration();
        labelFindUser = new JLabel();
        labelFindUser.setOpaque(false);
        labelFindUser.setBackground(new Color(220, 220, 220));
        labelFindUser.setBounds(250, 10, 25, 25);
        labelFindUser.setIcon(new ImageIcon(scaletImage));
        labelFindUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelFindUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                presenter.searchUser(txtFindName.getText());
            }
        });
        add(labelFindUser);

    }

    private void addLabelFullName() {
        labelFullName = new JLabel("Nombre Completo:");
        labelConfiguration(labelFullName);
        labelFullName.setBounds(60, 50, 150, 25);
        add(labelFullName);
    }

    private void addLabelFullNameContainer() {
        labelFullNameContainer = new JLabel();
        labelConfiguration(labelFullNameContainer);
        labelFullNameContainer.setBounds(210, 50, 250, 25);
        add(labelFullNameContainer);
    }

    private void addLabelDocumentoNumber() {
        labelDocumentNumber = new JLabel("Número de documento:");
        labelConfiguration(labelDocumentNumber);
        labelDocumentNumber.setBounds(60, 80, 170, 25);
        add(labelDocumentNumber);
    }

    private void addLabelDocumentoNumberContainer() {
        labelDocumentNumberContainer = new JLabel();
        labelConfiguration(labelDocumentNumberContainer);
        labelDocumentNumberContainer.setBounds(235, 80, 200, 25);
        add(labelDocumentNumberContainer);
    }

    private void addLabelTipeDocument() {
        labelTipeDocument = new JLabel("Tipo de documento:");
        labelConfiguration(labelTipeDocument);
        labelTipeDocument.setBounds(60, 110, 150, 25);
        add(labelTipeDocument);
    }

    private void addLabelTipeDocumentContainer() {
        labelTipeDocumentContainer = new JLabel();
        labelConfiguration(labelTipeDocumentContainer);
        labelTipeDocumentContainer.setBounds(210, 110, 200, 25);
        add(labelTipeDocumentContainer);
    }

    private void addLabelAge() {
        labelAge = new JLabel("Fecha de nacimiento:");
        labelConfiguration(labelAge);
        labelAge.setBounds(60, 140, 160, 25);
        add(labelAge);
    }

    private void addLabelAgeContainer() {
        labelAgeContainer = new JLabel();
        labelConfiguration(labelAgeContainer);
        labelAgeContainer.setBounds(220, 140, 300, 25);
        add(labelAgeContainer);
    }

    private void addLabelEmail() {
        labelEmail = new JLabel("Correo:");
        labelConfiguration(labelEmail);
        labelEmail.setBounds(60, 170, 60, 25);
        add(labelEmail);
    }

    private void addLabelEmailContainer() {
        labelEmailContainer = new JLabel();
        labelConfiguration(labelEmailContainer);
        labelEmailContainer.setBounds(130, 170, 200, 25);
        add(labelEmailContainer);
    }

    private void addLabelVaccine() {
        JLabel vaccine = new JLabel("Vacuna");
        vaccine.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        vaccine.setBackground(new Color(220, 220, 220));
        vaccine.setOpaque(true);
        vaccine.setBounds(40, 200, 200, 30);
        add(vaccine);
    }

    private void addPanelTable() {
        panelTable = new PanelTable();
        panelTable.setBounds(20, 240, 630, 200);
        add(panelTable);

    }

    private void labelConfiguration(JLabel label) {
        label.setForeground(Color.black);
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        label.setBackground(new Color(220, 220, 220));
        label.setOpaque(true);
    }

    private void imageConfiguration() {
        iconOriginal = new ImageIcon("images/buscar.png").getImage();
        scaletImage = iconOriginal.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        g2.dispose();
        super.paintComponent(g);
    }

    public void fillVaccineTable(List<Vaccinate> vaccines) {
        panelTable.fillTable(vaccines);
    }

    public void fillUserLabels(PersonData person) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fullName = person.getPerson().getFirstName()+ " " + person.getPerson().getMiddleName()+ " " + person.getPerson().getLastName()+ " " + person.getPerson().getSecondLastName();
        labelFullNameContainer.setText(fullName);
        labelDocumentNumberContainer.setText(String.valueOf(person.getPerson().getDocumentNumber()));
        labelTipeDocumentContainer.setText(person.getPerson().getDocumentType());
        labelAgeContainer.setText(sdf.format(person.getPerson().getBornDate()));
        labelEmailContainer.setText(person.getPerson().getEmail());
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
    public void fillVaccineLabels(Vaccine vaccine) {
    }

    @Override
    public void refreshComboFindVaccine() {
    }
}
