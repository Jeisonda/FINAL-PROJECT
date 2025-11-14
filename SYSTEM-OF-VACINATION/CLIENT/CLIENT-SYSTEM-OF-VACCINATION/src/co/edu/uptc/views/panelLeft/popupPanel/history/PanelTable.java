package co.edu.uptc.views.panelLeft.popupPanel.history;

import java.awt.BorderLayout;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import co.edu.uptc.pojos.PersonData;
import co.edu.uptc.pojos.Vaccinate;
import co.edu.uptc.pojos.Vaccine;
import co.edu.uptc.views.MainFrame;

public class PanelTable extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;
    private MainFrame main;
    private List<Vaccinate> vaccinateList;

    public PanelTable(MainFrame main) {
        setLayout(new BorderLayout());
        vaccinateList = new ArrayList<>();
        this.main = main;
        initComponents();
    }

    private void initComponents() {
        addTable();
    }

    private void addTable() {
        String[] columns = { "DOSIS", "NOMBRE", "N°LOTE","LABORATORIO", "FECHA VENCIMIENTO", "ENFERMEDAD OBJETIVO", "FECHA VACUNACIÓN"};
        DefaultTableModel model = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        table.getTableHeader().setResizingAllowed(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int[] widths = { 55, 120, 100, 120 ,150, 205, 130};
        for (int i = 0; i < widths.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        }
        add(scrollPane, BorderLayout.CENTER);
        model.addTableModelListener(e->{
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                if (row >= 0 && main != null && row < vaccinateList.size()) {
                    try {
                        DefaultTableModel m = (DefaultTableModel) table.getModel();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Vaccine updated = new Vaccine();
                        updated.setDose(Integer.parseInt(m.getValueAt(row, 0).toString()));
                        updated.setVaccineName(m.getValueAt(row, 1).toString());
                        updated.setBatchNumber(m.getValueAt(row, 2).toString());
                        updated.setManufacterName(m.getValueAt(row, 3).toString());
                        updated.setExpirationDate((sdf.parse(m.getValueAt(row, 4).toString())));
                        updated.setDiseaseName(m.getValueAt(row, 5).toString());
                        Date applicationDate = sdf.parse(m.getValueAt(row, 6).toString());
                        String docNum = vaccinateList.get(row).getDocumentNumber();
                        main.listenerUpdateVaccinePerformed(row, updated, applicationDate, Long.parseLong(docNum));
                    } catch (Exception ex) {
                        if (main != null) main.presenter.showErrorMessage("Error al actualizar");
                    }
                }
            }
        });
    }

    public void fillTable(List<Vaccinate> vaccines){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        vaccinateList.clear();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Vaccinate vaccinate : vaccines) {
            Vaccine v = vaccinate.getVaccine();
            Object[] row = {
                vaccinate.getDose(),
                v.getVaccineName(),
                v.getBatchNumber(),
                v.getManufacterName(),
                sdf.format(v.getExpirationDate()),
                v.getDiseaseName(),
                sdf.format(vaccinate.getApplicationDate())
            };
            model.addRow(row);
            vaccinateList.add(vaccinate);
        }
    }
}
