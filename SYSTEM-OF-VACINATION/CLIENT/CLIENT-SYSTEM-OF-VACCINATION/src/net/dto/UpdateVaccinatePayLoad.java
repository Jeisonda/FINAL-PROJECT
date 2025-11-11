package net.dto;

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

    
}
