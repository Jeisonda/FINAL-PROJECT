package co.edu.uptc.model;

import java.util.Date;

public class Vaccinate implements Comparable<Vaccinate>{
    private Vaccine vaccine;
    private Date applicationDate;
    private int dose;
    
    public Vaccinate() {
    }

    public Vaccinate(Person person, Vaccine vaccine, Date applicationDate , int dose) {
        this.vaccine = vaccine;
        this.applicationDate = applicationDate;
        this.dose = dose;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    @Override
    public int compareTo(Vaccinate compareVaccine){
        return vaccine.getVaccineName().compareTo(compareVaccine.getVaccine().getVaccineName());
    }
}
