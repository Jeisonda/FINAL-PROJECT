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
import model.DateModel;
import model.UserModel;
import model.VaccineModel;
import presenter.Presenter;

public class FormatVaccinationPanel extends JPanel implements ViewInterface {
    private int arcWidth = 50;
    private int arcHeight = 50;

    private boolean placeholderActivo = true;

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
    private JLabel label_dose;
    private JLabel label_dose_container;
    private JLabel label_vaccineName;
    private JLabel label_vaccineName_container;
    private JLabel label_batchNumber;
    private JLabel label_batchNumber_container;
    private JLabel label_expirationDate;
    private JLabel label_expirationDate_container;
    private JLabel label_diseaseName;
    private JLabel label_diseaseName_container;
    private JLabel label_findUser;
    private JLabel label_findVaccine;

    private JTextField txt_findUser;
    private JComboBox combo_findVaccine;

    private Presenter presenter;

    private JButton btn_vaccinate;

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
        txt_findUser = new JTextField("Ingrese N° de documento");
        addEventsTxfUser();
        txt_findUser.setBounds(40, 10, 200, 25);
        txt_findUser.setForeground(Color.gray);
        txt_findUser.setEditable(false);
        add(txt_findUser);
    }

    private void addEventsTxfUser() {
        txt_findUser.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                txt_findUser.setText("");
                txt_findUser.setForeground(Color.BLACK);
                txt_findUser.setEditable(true);
            }
        });
        txt_findUser.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (txt_findUser.getText().isEmpty()) {
                    txt_findUser.setText("Ingrese N° de documento");
                    txt_findUser.setForeground(Color.GRAY);
                    txt_findUser.setEditable(false);
                    placeholderActivo = true;
                }
            }
        });
        txt_findUser.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (placeholderActivo) {
                    txt_findUser.setText("");
                    txt_findUser.setEditable(true);
                    txt_findUser.setForeground(Color.black);
                    placeholderActivo = false;
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btn_vaccinate.doClick();
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
                presenter.searchUserById(txt_findUser.getText());
            }
        });
        add(label_findUser);

    }

    private void addLabelFullName() {
        label_fullName = new JLabel("Nombre Completo:");
        labelConfiguration(label_fullName);
        label_fullName.setBounds(60, 50, 120, 25);
        add(label_fullName);
    }

    private void addLabelFullNameContainer() {
        label_fullName_container = new JLabel("");
        labelConfiguration(label_fullName_container);
        label_fullName_container.setBounds(200, 50, 250, 25);
        add(label_fullName_container);
    }

    private void addLabelDocumentoNumber() {
        label_documentNumber = new JLabel("Número de documento:");
        labelConfiguration(label_documentNumber);
        label_documentNumber.setBounds(60, 80, 150, 25);
        add(label_documentNumber);
    }

    private void addLabelDocumentoNumberContainer() {
        label_documentNumber_container = new JLabel("");
        labelConfiguration(label_documentNumber_container);
        label_documentNumber_container.setBounds(220, 80, 200, 25);
        add(label_documentNumber_container);
    }

    private void addLabelTipeDocument() {
        label_tipeDocument = new JLabel("Tipo de documento:");
        labelConfiguration(label_tipeDocument);
        label_tipeDocument.setBounds(60, 110, 130, 25);
        add(label_tipeDocument);
    }

    private void addLabelTipeDocumentContainer() {
        label_tipeDocument_container = new JLabel("");
        labelConfiguration(label_tipeDocument_container);
        label_tipeDocument_container.setBounds(205, 110, 200, 25);
        add(label_tipeDocument_container);
    }

    private void addLabelAge() {
        label_age = new JLabel("Fecha de Nacimiento:");
        labelConfiguration(label_age);
        label_age.setBounds(60, 140, 145, 25);
        add(label_age);
    }

    private void addLabelAgeContainer() {
        label_age_container = new JLabel("");
        labelConfiguration(label_age_container);
        label_age_container.setBounds(206, 140, 200, 25);
        add(label_age_container);
    }

    private void addLabelEmail() {
        label_email = new JLabel("Correo:");
        labelConfiguration(label_email);
        label_email.setBounds(60, 170, 50, 25);
        add(label_email);
    }

    private void addLabelEmailContainer() {
        label_email_container = new JLabel("");
        labelConfiguration(label_email_container);
        label_email_container.setBounds(120, 170, 250, 25);
        add(label_email_container);
    }

    private void addComboFindVaccine() { 
        combo_findVaccine = new JComboBox<>();
        refreshComboFindVaccine();
        combo_findVaccine.setBounds(40, 200, 200, 25);
        add(combo_findVaccine);
    }

    public void refreshComboFindVaccine() {
       List<String> vaccineNames = presenter.getVaccineNames();
       DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (String name : vaccineNames) {
            model.addElement(name);
        }
        combo_findVaccine.setModel(model);
    }

    private void addLabelImageFindVaccine() {
        imageConfiguration();
        label_findVaccine = new JLabel();
        label_findVaccine.setOpaque(false);
        label_findVaccine.setBackground(new Color(220, 220, 220));
        label_findVaccine.setBounds(250, 200, 25, 25);
        label_findVaccine.setIcon(new ImageIcon(scaletImage));
        label_findVaccine.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label_findVaccine.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                presenter.searchVaccineByName(combo_findVaccine.getSelectedItem().toString());
            }
        });
        add(label_findVaccine);
    }

    private void addLabelId() {
        label_dose = new JLabel("Dosis:");
        labelConfiguration(label_dose);
        label_dose.setBounds(60, 240, 40, 25);
        add(label_dose);
    }

    private void addLabelIdContainer() {
        label_dose_container = new JLabel("");
        labelConfiguration(label_dose_container);
        label_dose_container.setBounds(100, 240, 200, 25);
        add(label_dose_container);
    }

    private void addLabelVaccineName() {
        label_vaccineName = new JLabel("Nombre de Vacuna:");
        labelConfiguration(label_vaccineName);
        label_vaccineName.setBounds(60, 270, 150, 25);
        add(label_vaccineName);
    }

    private void addLabelVaccineNameContainer() {
        label_vaccineName_container = new JLabel("");
        labelConfiguration(label_vaccineName_container);
        label_vaccineName_container.setBounds(200, 270, 300, 25);
        add(label_vaccineName_container);
    }

    private void addLabelBatch() {
        label_batchNumber = new JLabel("N° de lote:");
        labelConfiguration(label_batchNumber);
        label_batchNumber.setBounds(60, 300, 80, 25);
        add(label_batchNumber);
    }

    private void addLabelBatchContainer() {
        label_batchNumber_container = new JLabel("");
        labelConfiguration(label_batchNumber_container);
        label_batchNumber_container.setBounds(150, 300, 200, 25);
        add(label_batchNumber_container);
    }

    private void addLabelExpirationDate() {
        label_expirationDate = new JLabel("Fecha de vencimiento:");
        labelConfiguration(label_expirationDate);
        label_expirationDate.setBounds(60, 330, 150, 25);
        add(label_expirationDate);
    }

    private void addLabelExpirationDateContainer() {
        label_expirationDate_container = new JLabel("");
        labelConfiguration(label_expirationDate_container);
        label_expirationDate_container.setBounds(210, 330, 200, 25);
        add(label_expirationDate_container);
    }

    private void addLabelDisease() {
        label_diseaseName = new JLabel("Enfermedad Objetivo:");
        labelConfiguration(label_diseaseName);
        label_diseaseName.setBounds(60, 360, 250, 25);
        add(label_diseaseName);
    }

    private void addLabelDiseaseContainer() {
        label_diseaseName_container = new JLabel("");
        labelConfiguration(label_diseaseName_container);
        label_diseaseName_container.setBounds(215, 360, 300, 25);
        add(label_diseaseName_container);
    }

    private void addBtnVaccined() {
        btn_vaccinate = new JButton("Vacunar");
        btn_vaccinate.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        btn_vaccinate.setBounds(270, 420, 150, 30);
        btn_vaccinate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn_vaccinate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date applicationDate = new Date();
                presenter.vaccined(txt_findUser.getText(), combo_findVaccine.getSelectedItem().toString(),
                        applicationDate);
            }

        });
        add(btn_vaccinate);
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
        public void fillUserLabels(UserModel user) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fullName = user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName() + " "
                    + user.getSecondLastName();
            label_fullName_container.setText(fullName);
            label_documentNumber_container.setText(String.valueOf(user.getDocumentNumber()));
            label_tipeDocument_container.setText(user.getDocumentType());
            label_age_container.setText(sdf.format(user.getBornDate()));
            label_email_container.setText(user.getEmail());
        }

        @Override
        public void fillVaccineTable(List<DateModel> vaccines) {
        }

        @Override
        public void fillVaccineLabels(VaccineModel vaccine) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            label_vaccineName_container.setText(vaccine.getVaccineName());
            label_batchNumber_container.setText(vaccine.getBatchNumber());
            label_expirationDate_container.setText(sdf.format(vaccine.getExpirationDate()));
            label_diseaseName_container.setText(vaccine.getDiseaseName());
            label_dose_container.setText(String.valueOf(vaccine.getDose()));
        }

        public void update(){
            vaccinate.updateData();
        }
    }
