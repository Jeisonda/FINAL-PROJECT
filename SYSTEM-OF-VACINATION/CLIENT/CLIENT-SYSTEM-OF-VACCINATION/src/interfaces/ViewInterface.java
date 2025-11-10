package interfaces;

import java.util.List;

import pojos.Person;
import pojos.Vaccinate;
import pojos.Vaccine;


public interface ViewInterface {
    void showErrorMessage(String message);
    void showConfirmMessage(String message);
    void fillUserLabels(Person person);
    void fillVaccineLabels(Vaccine vaccine);
    void fillVaccineTable(List<Vaccinate> vaccines);
    void refreshComboFindVaccine();
}
