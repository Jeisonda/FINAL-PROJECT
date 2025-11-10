package co.edu.uptc.interfaces;

import java.util.List;

import co.edu.uptc.pojos.Person;
import co.edu.uptc.pojos.Vaccinate;
import co.edu.uptc.pojos.Vaccine;


public interface ViewInterface {
    void showErrorMessage(String message);
    void showConfirmMessage(String message);
    void fillUserLabels(Person person);
    void fillVaccineLabels(Vaccine vaccine);
    void fillVaccineTable(List<Vaccinate> vaccines);
    void refreshComboFindVaccine();
}
