package co.edu.uptc.interfaces;

import java.util.List;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.pojos.PersonData;
import co.edu.uptc.pojos.Vaccinate;
import co.edu.uptc.pojos.Vaccine;


public interface ViewInterface {
    void showErrorMessage(String message);
    void showConfirmMessage(String message);
    void fillUserLabels(PersonData person);
    void fillVaccineTable(List<Vaccinate> vaccines);
    void fillVaccineLabels(Vaccine vaccine);
    void refreshComboFindVaccine();
}