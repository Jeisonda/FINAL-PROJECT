package co.edu.uptc.pojos;

import java.util.Date;

public class Vaccinate {
    private String documentNumber;
    private Vaccine vaccine;
    private Date applicationDate;
    private int dose;

    public Vaccinate() {
    }

    public Vaccinate(String documentNumber, Vaccine vaccine, Date applicationDate, int dose) {
        this.documentNumber = documentNumber;
        this.vaccine = vaccine;
        this.applicationDate = applicationDate;
        this.dose = dose;
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

    public int getDose() {
        return dose;
    }
}
