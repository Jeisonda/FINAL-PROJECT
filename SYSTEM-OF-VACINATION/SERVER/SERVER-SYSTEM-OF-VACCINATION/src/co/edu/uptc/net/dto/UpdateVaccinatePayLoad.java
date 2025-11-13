package co.edu.uptc.net.dto;

import java.util.Date;
import co.edu.uptc.model.Vaccine; // nota: usa la clase del servidor, no la del cliente

public class UpdateVaccinatePayLoad {
    private int rowIndex;
    private String documentNumber;
    private Vaccine vaccine;
    private Date applicationDate;

    public int getRowIndex() {
        return rowIndex;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }
}
