package co.edu.uptc.net.dto;

import java.util.Date;

import co.edu.uptc.pojos.Vaccine;

public class UpdateVaccinatePayLoad {
    private int rowIndex;
    private String documentNumber;
    private Vaccine vaccine;
    private Date applicationDate;
    
    public UpdateVaccinatePayLoad(int rowIndex, String documentNumber, Vaccine vaccine, Date applicationDate) {
        this.rowIndex = rowIndex;
        this.documentNumber = documentNumber;
        this.vaccine = vaccine;
        this.applicationDate = applicationDate;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    
}
