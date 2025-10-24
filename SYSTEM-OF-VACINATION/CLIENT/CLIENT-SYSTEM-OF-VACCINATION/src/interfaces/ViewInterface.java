package interfaces;

import java.util.List;

import model.DateModel;
import model.UserModel;
import model.VaccineModel;

public interface ViewInterface {
    void showErrorMessage(String message);
    void showConfirmMessage(String message);
    void fillUserLabels(UserModel user);
    void fillVaccineLabels(VaccineModel vaccine);
    void fillVaccineTable(List<DateModel> vaccines);
    void refreshComboFindVaccine();
}
