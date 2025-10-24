package views.panelLeft.popupPanel.history;

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

import com.fasterxml.jackson.databind.AnnotationIntrospector.Pair;

import interfaces.ViewInterface;
import model.DateModel;
import model.UserModel;
import model.VaccineModel;
import presenter.Presenter;

public class PanelTable extends JPanel implements ViewInterface{

    private JTable table;
    private JScrollPane scrollPane;
    private Presenter presenter;
    private List<DateModel> dateModelList;
    private List<VaccineModel> vaccineModelList;

    public PanelTable() {
        setLayout(new BorderLayout());
        dateModelList = new ArrayList<>();
        vaccineModelList = new ArrayList<>();
        presenter = new Presenter(this);
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
                if (row >= 0 && presenter != null && row < vaccineModelList.size()) {
                    try {
                        DefaultTableModel m = (DefaultTableModel) table.getModel();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                        VaccineModel updated = new VaccineModel();
                        updated.setDose(Integer.parseInt(m.getValueAt(row, 0).toString()));
                        updated.setVaccineName(m.getValueAt(row, 1).toString());
                        updated.setBatchNumber(m.getValueAt(row, 2).toString());
                        updated.setManufacterName(m.getValueAt(row, 3).toString());
                        updated.setExpirationDate((sdf.parse(m.getValueAt(row, 4).toString())));
                        updated.setDiseaseName(m.getValueAt(row, 5).toString());

                        Date appicationDate = sdf.parse(m.getValueAt(row, 6).toString());
                        long docNum = dateModelList.get(row).getPerson().getDocumentNumber();

                        presenter.updateVaccineFromTable(row, updated, appicationDate, docNum);
                    } catch (Exception ex) {
                        showErrorMessage("Error al actualizar");
                    }
                }
            }
        });

    }

    public void fillTable(List<DateModel> vaccines){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        dateModelList.clear();
        vaccineModelList.clear();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (DateModel dateModel : vaccines) {
            String applicationDate = sdf.format(dateModel.getApplicationDate());
            for (VaccineModel v : dateModel.getVaccines()) {
                Object[] row = {
                    v.getDose(),
                    v.getVaccineName(),
                    v.getBatchNumber(),
                    v.getManufacterName(),
                    sdf.format(v.getExpirationDate()),
                    v.getDiseaseName(),
                    applicationDate
                };
                model.addRow(row);
                dateModelList.add(dateModel);
                vaccineModelList.add(v);
            }
        }
    }

    @Override
    public void showErrorMessage(String message) {
    }

    @Override
    public void showConfirmMessage(String message) {
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
