package co.edu.uptc.pojos;

import java.util.List;

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

    public List<Vaccinate> getVaccinateList() {
        return vaccinateList;
    }
}