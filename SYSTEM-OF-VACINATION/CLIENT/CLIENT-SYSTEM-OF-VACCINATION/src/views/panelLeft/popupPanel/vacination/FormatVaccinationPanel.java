package views.panelLeft.popupPanel.vacination;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interfaces.ViewInterface;
import pojos.Person;
import pojos.Vaccinate;
import pojos.Vaccine;
import presenter.Presenter;

public class FormatVaccinationPanel extends JPanel implements ViewInterface {
    private int arcWidth = 50;
    private int arcHeight = 50;

    private boolean placeholderActivo = true;

    private JLabel labelFullName;
    private JLabel labelFullNameFontainer;
    private JLabel labelDocumentNumber;
    private JLabel labelDocumentNumberContainer;
    private JLabel labelTipeDocument;
    private JLabel labelTipeDocumentContainer;
    private JLabel labelEmail;
    private JLabel labelEmailContainer;
    private JLabel labelAge;
    private JLabel labelAgeContainer;
    private JLabel labelDose;
    private JLabel labelDoseContainer;
    private JLabel labelVaccineName;
    private JLabel labelVaccineNameContainer;
    private JLabel labelBatchNumber;
    private JLabel labelBatchNumberContainer;
    private JLabel labelExpirationDate;
    private JLabel labelExpirationDateContainer;
    private JLabel labelDiseaseName;
    private JLabel labelDiseaseNameContainer;
    private JLabel labelFindUser;
    private JLabel labelFindVaccine;

    private JTextField txtFindUser;
    private JComboBox comboFindVaccine;

    private Presenter presenter;

    private JButton btnVaccinate;

    private Image iconOriginal, scaletImage;

    private VaccinatePanel vaccinate;

    public FormatVaccinationPanel(VaccinatePanel vaccinate) {
        this.vaccinate = vaccinate;
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
        addComboFindVaccine();
        addLabelImageFindVaccine();
        addLabelId();
        addLabelIdContainer();
        addLabelVaccineName();
        addLabelVaccineNameContainer();
        addLabelBatch();
        addLabelBatchContainer();
        addLabelExpirationDate();
        addLabelExpirationDateContainer();
        addLabelDisease();
        addLabelDiseaseContainer();
        addBtnVaccined();
    }

    private void addTxtFindUser() {
        txtFindUser = new JTextField("Ingrese N° de documento");
        addEventsTxfUser();
        txtFindUser.setBounds(40, 10, 200, 25);
        txtFindUser.setForeground(Color.gray);
        txtFindUser.setEditable(false);
        add(txtFindUser);
    }

    private void addEventsTxfUser() {
        txtFindUser.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                txtFindUser.setText("");
                txtFindUser.setForeground(Color.BLACK);
                txtFindUser.setEditable(true);
            }
        });
        txtFindUser.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (txtFindUser.getText().isEmpty()) {
                    txtFindUser.setText("Ingrese N° de documento");
                    txtFindUser.setForeground(Color.GRAY);
                    txtFindUser.setEditable(false);
                    placeholderActivo = true;
                }
            }
        });
        txtFindUser.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (placeholderActivo) {
                    txtFindUser.setText("");
                    txtFindUser.setEditable(true);
                    txtFindUser.setForeground(Color.black);
                    placeholderActivo = false;
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnVaccinate.doClick();
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
                presenter.searchUserById(txtFindUser.getText());
            }
        });
        add(labelFindUser);

    }

    private void addLabelFullName() {
        labelFullName = new JLabel("Nombre Completo:");
        labelConfiguration(labelFullName);
        labelFullName.setBounds(60, 50, 120, 25);
        add(labelFullName);
    }

    private void addLabelFullNameContainer() {
        labelFullNameFontainer = new JLabel("");
        labelConfiguration(labelFullNameFontainer);
        labelFullNameFontainer.setBounds(200, 50, 250, 25);
        add(labelFullNameFontainer);
    }

    private void addLabelDocumentoNumber() {
        labelDocumentNumber = new JLabel("Número de documento:");
        labelConfiguration(labelDocumentNumber);
        labelDocumentNumber.setBounds(60, 80, 150, 25);
        add(labelDocumentNumber);
    }

    private void addLabelDocumentoNumberContainer() {
        labelDocumentNumberContainer = new JLabel("");
        labelConfiguration(labelDocumentNumberContainer);
        labelDocumentNumberContainer.setBounds(220, 80, 200, 25);
        add(labelDocumentNumberContainer);
    }

    private void addLabelTipeDocument() {
        labelTipeDocument = new JLabel("Tipo de documento:");
        labelConfiguration(labelTipeDocument);
        labelTipeDocument.setBounds(60, 110, 130, 25);
        add(labelTipeDocument);
    }

    private void addLabelTipeDocumentContainer() {
        labelTipeDocumentContainer = new JLabel("");
        labelConfiguration(labelTipeDocumentContainer);
        labelTipeDocumentContainer.setBounds(205, 110, 200, 25);
        add(labelTipeDocumentContainer);
    }

    private void addLabelAge() {
        labelAge = new JLabel("Fecha de Nacimiento:");
        labelConfiguration(labelAge);
        labelAge.setBounds(60, 140, 145, 25);
        add(labelAge);
    }

    private void addLabelAgeContainer() {
        labelAgeContainer = new JLabel("");
        labelConfiguration(labelAgeContainer);
        labelAgeContainer.setBounds(206, 140, 200, 25);
        add(labelAgeContainer);
    }

    private void addLabelEmail() {
        labelEmail = new JLabel("Correo:");
        labelConfiguration(labelEmail);
        labelEmail.setBounds(60, 170, 50, 25);
        add(labelEmail);
    }

    private void addLabelEmailContainer() {
        labelEmailContainer = new JLabel("");
        labelConfiguration(labelEmailContainer);
        labelEmailContainer.setBounds(120, 170, 250, 25);
        add(labelEmailContainer);
    }

    private void addComboFindVaccine() { 
        comboFindVaccine = new JComboBox<>();
        refreshComboFindVaccine();
        comboFindVaccine.setBounds(40, 200, 200, 25);
        add(comboFindVaccine);
    }

    public void refreshComboFindVaccine() {
       List<String> vaccineNames = presenter.getVaccineNames();
       DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (String name : vaccineNames) {
            model.addElement(name);
        }
        comboFindVaccine.setModel(model);
    }

    private void addLabelImageFindVaccine() {
        imageConfiguration();
        labelFindVaccine = new JLabel();
        labelFindVaccine.setOpaque(false);
        labelFindVaccine.setBackground(new Color(220, 220, 220));
        labelFindVaccine.setBounds(250, 200, 25, 25);
        labelFindVaccine.setIcon(new ImageIcon(scaletImage));
        labelFindVaccine.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelFindVaccine.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                presenter.searchVaccineByName(comboFindVaccine.getSelectedItem().toString());
            }
        });
        add(labelFindVaccine);
    }

    private void addLabelId() {
        labelDose = new JLabel("Dosis:");
        labelConfiguration(labelDose);
        labelDose.setBounds(60, 240, 40, 25);
        add(labelDose);
    }

    private void addLabelIdContainer() {
        labelDoseContainer = new JLabel("");
        labelConfiguration(labelDoseContainer);
        labelDoseContainer.setBounds(100, 240, 200, 25);
        add(labelDoseContainer);
    }

    private void addLabelVaccineName() {
        labelVaccineName = new JLabel("Nombre de Vacuna:");
        labelConfiguration(labelVaccineName);
        labelVaccineName.setBounds(60, 270, 150, 25);
        add(labelVaccineName);
    }

    private void addLabelVaccineNameContainer() {
        labelVaccineNameContainer = new JLabel("");
        labelConfiguration(labelVaccineNameContainer);
        labelVaccineNameContainer.setBounds(200, 270, 300, 25);
        add(labelVaccineNameContainer);
    }

    private void addLabelBatch() {
        labelBatchNumber = new JLabel("N° de lote:");
        labelConfiguration(labelBatchNumber);
        labelBatchNumber.setBounds(60, 300, 80, 25);
        add(labelBatchNumber);
    }

    private void addLabelBatchContainer() {
        labelBatchNumberContainer = new JLabel("");
        labelConfiguration(labelBatchNumberContainer);
        labelBatchNumberContainer.setBounds(150, 300, 200, 25);
        add(labelBatchNumberContainer);
    }

    private void addLabelExpirationDate() {
        labelExpirationDate = new JLabel("Fecha de vencimiento:");
        labelConfiguration(labelExpirationDate);
        labelExpirationDate.setBounds(60, 330, 150, 25);
        add(labelExpirationDate);
    }

    private void addLabelExpirationDateContainer() {
        labelExpirationDateContainer = new JLabel("");
        labelConfiguration(labelExpirationDateContainer);
        labelExpirationDateContainer.setBounds(210, 330, 200, 25);
        add(labelExpirationDateContainer);
    }

    private void addLabelDisease() {
        labelDiseaseName = new JLabel("Enfermedad Objetivo:");
        labelConfiguration(labelDiseaseName);
        labelDiseaseName.setBounds(60, 360, 250, 25);
        add(labelDiseaseName);
    }

    private void addLabelDiseaseContainer() {
        labelDiseaseNameContainer = new JLabel("");
        labelConfiguration(labelDiseaseNameContainer);
        labelDiseaseNameContainer.setBounds(215, 360, 300, 25);
        add(labelDiseaseNameContainer);
    }

    private void addBtnVaccined() {
        btnVaccinate = new JButton("Vacunar");
        btnVaccinate.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        btnVaccinate.setBounds(270, 420, 150, 30);
        btnVaccinate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnVaccinate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date applicationDate = new Date();
                presenter.vaccined(txtFindUser.getText(), comboFindVaccine.getSelectedItem().toString(),
                        applicationDate);
            }

        });
        add(btnVaccinate);
    }

    private void labelConfiguration(JLabel label) {
        label.setForeground(Color.black);
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
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

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "UNSUCCES", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showConfirmMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "SUCCES", JOptionPane.INFORMATION_MESSAGE);
        }

        @Override
        public void fillUserLabels(Person person) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fullName = person.getFirstName() + " " + person.getMiddleName() + " " + person.getLastName() + " "
                    + person.getSecondLastName();
            labelFullNameFontainer.setText(fullName);
            labelDocumentNumberContainer.setText(String.valueOf(person.getDocumentNumber()));
            labelTipeDocumentContainer.setText(person.getDocumentType());
            labelAgeContainer.setText(sdf.format(person.getBornDate()));
            labelEmailContainer.setText(person.getEmail());
        }

        @Override
        public void fillVaccineTable(List<Vaccinate> vaccines) {
        }

        @Override
        public void fillVaccineLabels(Vaccine vaccine) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            labelVaccineNameContainer.setText(vaccine.getVaccineName());
            labelBatchNumberContainer.setText(vaccine.getBatchNumber());
            labelExpirationDateContainer.setText(sdf.format(vaccine.getExpirationDate()));
            labelDiseaseNameContainer.setText(vaccine.getDiseaseName());
            labelDoseContainer.setText(String.valueOf(vaccine.getDose()));
        }

        public void update(){
            vaccinate.updateData();
        }
    }
