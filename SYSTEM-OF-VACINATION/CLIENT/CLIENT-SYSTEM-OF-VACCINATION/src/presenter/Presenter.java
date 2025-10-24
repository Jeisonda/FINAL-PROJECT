package presenter;

import java.util.Date;
import java.util.List;

import interfaces.PresenterInterface;
import interfaces.ViewInterface;
import model.DateModel;
import model.MainModel;
import model.UserModel;
import model.VaccineModel;

public class Presenter implements PresenterInterface {

    private ViewInterface view;
    public Presenter(ViewInterface view) {
        this.view = view;
    }

    @Override
    public void createUser(String firstName, String middleName, String lastName, String seconLastName,
            String documentType, String documentNumber, Date bornDate, String phoneNumber, String email) {
        if (firstName.isEmpty() || lastName.isEmpty() || seconLastName.isEmpty() ||
                documentType.isEmpty() || documentNumber.isEmpty() || bornDate == null || phoneNumber.isEmpty()
                || email.isEmpty()) {
            view.showErrorMessage("Todos los campos tienen que llenarse");
            return;
        }
        try {
            model.createUser(firstName, middleName, lastName, seconLastName, documentType, documentNumber, bornDate,
                    phoneNumber, email);
            view.showConfirmMessage("Creado con exito");
        } catch (Exception e) {
            view.showErrorMessage("Error al crear usuario");
        }
    }
    
    @Override
    public void createVaccine(String vaccineName, String manuName, String disease, Date expiDate, String vaccineType,
    String batchNumber, String dose) {
        try {
            model.createVaccine(vaccineName, manuName, disease, expiDate, vaccineType, batchNumber, dose);
            view.showConfirmMessage("Creada con exito");
            view.refreshComboFindVaccine();

        } catch (Exception e) {
            if (vaccineName.isEmpty() || manuName.isEmpty() || disease.isEmpty() || expiDate == null ||
                    vaccineType.isEmpty() || batchNumber.isEmpty() || dose.isEmpty()) {
                view.showErrorMessage("Todos los campos tienen que llenarse");
            }
        }

    }

    @Override
    public void vaccined(String documentNumber, String vaccineName, Date applicatoinDate) {
        if (applicatoinDate == null) {
            applicatoinDate = new Date();
        }
        model.vaccinate(Long.parseLong(documentNumber), vaccineName, applicatoinDate);
    }

    public void searchVaccineByName(String vaccineName) {
        VaccineModel vaccine = model.getVaccineByName(vaccineName);
        if (vaccine != null) {
            view.fillVaccineLabels(vaccine);
        }
    }

    public void searchUserById(String documentNumber) {
        try {
            UserModel user = model.getUserByDocument(Long.parseLong(documentNumber));
            if (user != null) {
                view.fillUserLabels(user);
            }
        } catch (Exception e) {
            view.showErrorMessage("Usuarió no encontrado");
        }
    }

    @Override
    public void searchUser(String documentNumber) {
        try {
            long document = Long.parseLong(documentNumber);
            UserModel user = model.getUserByDocument(document);
            if (user != null) {
                view.fillUserLabels(user);
                List<DateModel> vaccines = model.getVaccinesForUsers(document);
                view.fillVaccineTable(vaccines);

            }
        } catch (Exception e) {
            view.showErrorMessage("Usuario no encontrado");
        }
    }

    public List<String> getVaccineNames() {
        return model.getVaccineNames();
    }

    public List<DateModel> getVaccinesForUsers(String documentNumber) {
        return model.getVaccinesForUsers(Long.parseLong(documentNumber));
    }

    public void updateVaccineFromTable(int rowIndex, VaccineModel updateVaccine, Date applicationDate,
            long documentNumber) {
        model.updateVaccineFromTable(rowIndex, updateVaccine, applicationDate, documentNumber);
    }

}
