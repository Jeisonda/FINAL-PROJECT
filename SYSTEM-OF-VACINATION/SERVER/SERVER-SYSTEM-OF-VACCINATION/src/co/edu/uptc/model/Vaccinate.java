package co.edu.uptc.model;

import java.util.Date;

public class Vaccinate implements Comparable<Vaccinate> {
    private String documentNumber;
    private Vaccine vaccine;
    private Date applicationDate;
    private int dose;

    public Vaccinate() {
    }

    public Vaccinate(Vaccine vaccine, Date applicationDate, int dose, String documentNumber) {
        this.vaccine = vaccine;
        this.applicationDate = applicationDate;
        this.dose = dose;
        this.documentNumber = documentNumber;
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

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    @Override
    public int compareTo(Vaccinate other) {
        return compareDocumentNumber(other);
    }

    private int compareDocumentNumber(Vaccinate other) {
        String thisDoc = this.documentNumber;
        String otherDoc = other.documentNumber;

        if (thisDoc == null && otherDoc == null) return 0;
        if (thisDoc == null) return -1;
        if (otherDoc == null) return 1;

        int cmp = thisDoc.compareTo(otherDoc);
        if (cmp != 0) return cmp;
        return compareDate(other, cmp);
    }

    private int compareDate(Vaccinate other, int cmp) {
        Date thisDate = this.applicationDate;
        Date otherDate = other.applicationDate;

        if (thisDate == null && otherDate == null) {
        } else if (thisDate == null) {
            return -1;
        } else if (otherDate == null) {
            return 1;
        } else {
            cmp = thisDate.compareTo(otherDate);
            if (cmp != 0) return cmp;
        } 
        return compareVaccineName(other);
    }

    private int compareVaccineName(Vaccinate other) {
        String thisVaccine = (vaccine != null && vaccine.getVaccineName() != null)
                ? vaccine.getVaccineName()
                : "";

        String otherVaccine = (other.vaccine != null && other.vaccine.getVaccineName() != null)
                ? other.vaccine.getVaccineName()
                : "";

        return thisVaccine.compareTo(otherVaccine);
    }
}
