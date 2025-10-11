package views.panelLeft.popupPanel.history;

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

import interfaces.ViewInterface;
import model.DateModel;
import model.UserModel;
import model.VaccineModel;
import presenter.Presenter;

public class FormatHistoryPanel extends JPanel implements ViewInterface {

    private int arcWidth = 50;
    private int arcHeight = 50;

    private Presenter presenter;

    private Image iconOriginal, scaletImage;

    private PanelTable panelTable;

    private JLabel label_fullName;
    private JLabel label_fullName_container;
    private JLabel label_documentNumber;
    private JLabel label_documentNumber_container;
    private JLabel label_tipeDocument;
    private JLabel label_tipeDocument_container;
    private JLabel label_email;
    private JLabel label_email_container;
    private JLabel label_age;
    private JLabel label_age_container;

    private JLabel label_findUser;

    private JTextField txt_findName;

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
        txt_findName = new JTextField("Ingrese N° de documento");
        addEventsTxfUser();
        txt_findName.setBounds(40, 10, 200, 25);
        txt_findName.setForeground(Color.gray);
        txt_findName.setEditable(false);
        add(txt_findName);
    }

    private void addEventsTxfUser() {
        txt_findName.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                txt_findName.setText("");
                txt_findName.setForeground(Color.BLACK);
                txt_findName.setEditable(true);
            }
        });
        txt_findName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (txt_findName.getText().isEmpty()) {
                    txt_findName.setText("Ingrese N° de documento");
                    txt_findName.setForeground(Color.GRAY);
                    txt_findName.setEditable(false);
                    placeholderActivo = true;
                }
            }
        });
        txt_findName.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (placeholderActivo) {
                    txt_findName.setText("");
                    txt_findName.setEditable(true);
                    txt_findName.setForeground(Color.black);
                    placeholderActivo = false;
                }
            }
        });
    }

    private void addLabelSearchUser() {
        imageConfiguration();
        label_findUser = new JLabel();
        label_findUser.setOpaque(false);
        label_findUser.setBackground(new Color(220, 220, 220));
        label_findUser.setBounds(250, 10, 25, 25);
        label_findUser.setIcon(new ImageIcon(scaletImage));
        label_findUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label_findUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                presenter.searchUser(txt_findName.getText());
            }
        });
        add(label_findUser);

    }

    private void addLabelFullName() {
        label_fullName = new JLabel("Nombre Completo:");
        labelConfiguration(label_fullName);
        label_fullName.setBounds(60, 50, 150, 25);
        add(label_fullName);
    }

    private void addLabelFullNameContainer() {
        label_fullName_container = new JLabel();
        labelConfiguration(label_fullName_container);
        label_fullName_container.setBounds(210, 50, 250, 25);
        add(label_fullName_container);
    }

    private void addLabelDocumentoNumber() {
        label_documentNumber = new JLabel("Número de documento:");
        labelConfiguration(label_documentNumber);
        label_documentNumber.setBounds(60, 80, 170, 25);
        add(label_documentNumber);
    }

    private void addLabelDocumentoNumberContainer() {
        label_documentNumber_container = new JLabel();
        labelConfiguration(label_documentNumber_container);
        label_documentNumber_container.setBounds(235, 80, 200, 25);
        add(label_documentNumber_container);
    }

    private void addLabelTipeDocument() {
        label_tipeDocument = new JLabel("Tipo de documento:");
        labelConfiguration(label_tipeDocument);
        label_tipeDocument.setBounds(60, 110, 150, 25);
        add(label_tipeDocument);
    }

    private void addLabelTipeDocumentContainer() {
        label_tipeDocument_container = new JLabel();
        labelConfiguration(label_tipeDocument_container);
        label_tipeDocument_container.setBounds(210, 110, 200, 25);
        add(label_tipeDocument_container);
    }

    private void addLabelAge() {
        label_age = new JLabel("Fecha de nacimiento:");
        labelConfiguration(label_age);
        label_age.setBounds(60, 140, 160, 25);
        add(label_age);
    }

    private void addLabelAgeContainer() {
        label_age_container = new JLabel();
        labelConfiguration(label_age_container);
        label_age_container.setBounds(220, 140, 300, 25);
        add(label_age_container);
    }

    private void addLabelEmail() {
        label_email = new JLabel("Correo:");
        labelConfiguration(label_email);
        label_email.setBounds(60, 170, 60, 25);
        add(label_email);
    }

    private void addLabelEmailContainer() {
        label_email_container = new JLabel();
        labelConfiguration(label_email_container);
        label_email_container.setBounds(130, 170, 200, 25);
        add(label_email_container);
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
        iconOriginal = new ImageIcon("src/images/buscar 64x64.png").getImage();
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

    public void fillVaccineTable(List<DateModel> vaccines) {
        panelTable.fillTable(vaccines);
    }

    public void fillUserLabels(UserModel user) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fullName = user.getFirstName()+ " " + user.getMiddleName()+ " " + user.getLastName()+ " " + user.getSecondLastName();
        label_fullName_container.setText(fullName);
        label_documentNumber_container.setText(String.valueOf(user.getDocumentNumber()));
        label_tipeDocument_container.setText(user.getDocumentType());
        label_age_container.setText(sdf.format(user.getBornDate()));
        label_email_container.setText(user.getEmail());
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
    public void fillVaccineLabels(VaccineModel vaccine) {
    }

    @Override
    public void refreshComboFindVaccine() {
    }


}
