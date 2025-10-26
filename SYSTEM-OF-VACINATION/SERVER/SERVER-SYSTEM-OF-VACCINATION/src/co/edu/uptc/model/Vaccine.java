package co.edu.uptc.model;

import java.util.Date;

public class Vaccine implements Comparable<Vaccine> {
   
    private String vaccineName;
    private String manufacterName;
    private String diseaseName;
    private String vaccineType;
    private String batchNumber;
    private Date expirationDate;
    private int totalDose;

    public Vaccine(){

    }

    public Vaccine(String vaccineName, String manufacterName, String diseaseName, String vaccineType,
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

    public int getTotalDose() {
        return totalDose;
    }

    public void setTotalDose(int dose) {
        this.totalDose = totalDose;
    }

    @Override
    public int compareTo(Vaccine compareVaccine){
        return vaccineName.compareTo(compareVaccine.getVaccineName());
    }
    
}
