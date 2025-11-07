package co.edu.uptc.model.dto;

import java.util.Date;

public class VaccinateDTO {
    private VaccineDTO vaccine;
    private Date applicationDate;
    private int dose;

    public VaccinateDTO(VaccineDTO vaccine, Date applicationDate, int dose) {
        this.vaccine = vaccine;
        this.applicationDate = applicationDate;
        this.dose = dose;
    }

    public VaccinateDTO() {}
}
