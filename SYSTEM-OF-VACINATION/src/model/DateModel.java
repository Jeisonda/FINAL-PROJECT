package model;

import java.util.Date;
import java.util.List;

public class DateModel {
    private UserModel person;
    private List<VaccineModel> vaccines;
    private Date applicationDate;
    
    public DateModel() {
    }

    public DateModel(UserModel person, List<VaccineModel> vaccines, Date applicationDate) {
        this.person = person;
        this.vaccines = vaccines;
        this.applicationDate = applicationDate;
    }

    public UserModel getPerson() {
        return person;
    }

    public void setPerson(UserModel person) {
        this.person = person;
    }

    public List<VaccineModel> getVaccines() {
        return vaccines;
    }

    public void setVaccines(List<VaccineModel> vaccines) {
        this.vaccines = vaccines;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }


}
