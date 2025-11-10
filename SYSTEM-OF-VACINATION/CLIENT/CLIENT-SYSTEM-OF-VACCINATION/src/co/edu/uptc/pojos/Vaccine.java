package co.edu.uptc.pojos;

import java.util.Date;

public class Vaccine {
    private String vaccineName;
    private String manufacterName;
    private String diseaseName;
    private Date expirationDate;
    private String vaccineType;
    private String batchNumber;
    private int dose;

    public Vaccine() {
    }

    public Vaccine(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public Vaccine(String vaccineName, String manufacterName, String diseaseName,
            Date expirationDate, String vaccineType, String batchNumber, int dose) {
        this.vaccineName = vaccineName;
        this.manufacterName = manufacterName;
        this.diseaseName = diseaseName;
        this.expirationDate = expirationDate;
        this.vaccineType = vaccineType;
        this.batchNumber = batchNumber;
        this.dose = dose;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getManufacterName() {
        return manufacterName;
    }

    public void setManufacterName(String manufacterName) {
        this.manufacterName = manufacterName;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }
}
