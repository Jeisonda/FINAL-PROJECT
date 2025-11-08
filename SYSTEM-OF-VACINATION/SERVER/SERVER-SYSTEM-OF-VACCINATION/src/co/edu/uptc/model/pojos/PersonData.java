package co.edu.uptc.model.pojos;

import java.util.List;

import co.edu.uptc.model.Person;
import co.edu.uptc.model.Vaccinate;

public class PersonData {
    private Person person;
    private List<Vaccinate> vaccinateList;

    public PersonData(Person person, List<Vaccinate> vaccinateList) {
        this.person = person;
        this.vaccinateList = vaccinateList;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Vaccinate> getVaccinateList() {
        return vaccinateList;
    }

    public void setVaccinateList(List<Vaccinate> vaccinateList) {
        this.vaccinateList = vaccinateList;
    }
}
