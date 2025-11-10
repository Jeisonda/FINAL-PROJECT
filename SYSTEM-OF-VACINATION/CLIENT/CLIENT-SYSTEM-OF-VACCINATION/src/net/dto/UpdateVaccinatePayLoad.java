package pojos;

import java.util.Date;

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
