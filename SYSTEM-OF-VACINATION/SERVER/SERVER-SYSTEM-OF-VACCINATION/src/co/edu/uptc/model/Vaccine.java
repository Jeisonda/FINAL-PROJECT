package co.edu.uptc.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vaccine implements Comparable<Vaccine> {
    private String vaccineName;
    private String manufacterName;
    private String diseaseName;
    private String vaccineType;
    private String batchNumber;
    private Date expirationDate;
    @JsonProperty("dose")
    private int dose;

    public Vaccine(){
    }

    public Vaccine(String vaccineName, String manufacterName, String diseaseName, String vaccineType,
            String batchNumber, Date expirationDate, int dose) {
        this.vaccineName = vaccineName;
        this.manufacterName = manufacterName;
        this.diseaseName = diseaseName;
        this.vaccineType = vaccineType;
        this.batchNumber = batchNumber;
        this.expirationDate = expirationDate;
        this.dose = dose;
    }

    public Vaccine(String vaccineName) {
        this.vaccineName = vaccineName;
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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    @Override
    public int compareTo(Vaccine compareVaccine){
        return vaccineName.compareTo(compareVaccine.getVaccineName());
    }
    
}
