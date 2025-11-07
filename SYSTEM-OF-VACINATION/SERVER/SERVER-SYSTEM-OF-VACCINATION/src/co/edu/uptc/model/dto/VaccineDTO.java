package co.edu.uptc.model.dto;

import java.util.Date;

public class VaccineDTO {
    private String vaccineName;
    private String manufacterName;
    private String diseaseName;
    private String vaccineType;
    private String batchNumber;
    private Date expirationDate;
    private int totalDose;

    public VaccineDTO(){
    }

    public VaccineDTO(String vaccineName, String manufacterName, String diseaseName, String vaccineType,
            String batchNumber, Date expirationDate, int totalDose) {
        this.vaccineName = vaccineName;
        this.manufacterName = manufacterName;
        this.diseaseName = diseaseName;
        this.vaccineType = vaccineType;
        this.batchNumber = batchNumber;
        this.expirationDate = expirationDate;
        this.totalDose = totalDose;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public String getManufacterName() {
        return manufacterName;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public int getTotalDose() {
        return totalDose;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public void setManufacterName(String manufacterName) {
        this.manufacterName = manufacterName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setTotalDose(int totalDose) {
        this.totalDose = totalDose;
    }
}
