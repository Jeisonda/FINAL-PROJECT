package co.edu.uptc.control;

import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.net.dto.Request;
import co.edu.uptc.net.dto.Response;
import co.edu.uptc.net.dto.UpdateVaccinatePayLoad;
import co.edu.uptc.pojos.Person;
import co.edu.uptc.pojos.PersonData;
import co.edu.uptc.pojos.Vaccinate;
import co.edu.uptc.pojos.Vaccine;
import co.edu.uptc.views.inputString.AskInputView;
import co.edu.uptc.netWork.ServerConnection;

public class ClientController implements PresenterInterface {

    private ViewInterface view;
    private ServerConnection connection;
    private int port1;
    private String host2;

    public ClientController(ViewInterface view) {
        this.view = view;
    }

    public ClientController() {
        askForConnectionData(new AskInputView());
    }

    @Override
    public void createUser(String firstName, String middleName, String lastName, String seconLastName, String documentType, String documentNumber, Date bornDate, String phoneNumber, String email) {
        if (firstName.isEmpty() || lastName.isEmpty() || seconLastName.isEmpty() || documentType.isEmpty() || documentNumber.isEmpty() || bornDate == null || phoneNumber.isEmpty() || email.isEmpty()) {
            view.showErrorMessage("Todos los campos tienen que llenarse");
            return;
        }
        try {
            tryCreateUserPerson(firstName, middleName, lastName, seconLastName, documentType, documentNumber, bornDate, phoneNumber, email);
        } catch (Exception e) {
            view.showErrorMessage("Error al crear usuario");
        }
    }

    private void tryCreateUserPerson(String firstName, String middleName, String lastName, String seconLastName, String documentType, String documentNumber, Date bornDate, String phoneNumber, String email) throws IOException {
        Person person = new Person(firstName, middleName, lastName, seconLastName, documentType, documentNumber, bornDate, phoneNumber, email);
        Request request = new Request("CREATE_PERSON", connection.getGson().toJson(person));
        Response<String> response = connection.sendResponse(request, String.class);
        if (response.isSuccess()) view.showConfirmMessage(response.getMessage());
        else view.showErrorMessage(response.getMessage());
    }

    @Override
    public void createVaccine(String vaccineName, String manuName, String disease, Date expiDate, String vaccineType, String batchNumber, String dose) {
        if (vaccineName.isEmpty() || manuName.isEmpty() || disease.isEmpty() || expiDate == null || vaccineType.isEmpty() || batchNumber.isEmpty() || dose.isEmpty()) {
            view.showErrorMessage("Todos los campos deben llenarse");
            return;
        }
        try {
            tryCreateVaccine(vaccineName, manuName, disease, expiDate, vaccineType, batchNumber, dose);
        } catch (Exception e) {
            view.showErrorMessage("Error al crear vacuna");
        }
    }

    private void tryCreateVaccine(String vaccineName, String manuName, String disease, Date expiDate, String vaccineType, String batchNumber, String dose) throws IOException {
        Vaccine vaccine = new Vaccine(vaccineName, manuName, disease, expiDate, vaccineType, batchNumber, Integer.parseInt(dose));
        Request request = new Request("CREATE_VACCINE", connection.getGson().toJson(vaccine));
        Response<String> response = connection.sendResponse(request, String.class);
        if (response.isSuccess()) {
            view.showConfirmMessage(response.getMessage());
            view.refreshComboFindVaccine();
        } else view.showErrorMessage(response.getMessage());
    }

    @Override
    public void vaccined(String documentNumber, String vaccineName, Date applicatoinDate) {
        if (connection == null) {
            view.showErrorMessage("No hay conexión con el servidor");
            return;
        }
        try {
            tryVaccined(documentNumber, vaccineName, applicatoinDate);
        } catch (Exception e) {
            view.showErrorMessage("Error al registrar vacunación");
        }
    }

    private void tryVaccined(String documentNumber, String vaccineName, Date applicatoinDate) throws IOException {
        Vaccinate vaccinate = new Vaccinate(documentNumber, new Vaccine(vaccineName), applicatoinDate, 1);
        Request request = new Request("VACCINATE", connection.getGson().toJson(vaccinate));
        Response<String> response = connection.sendResponse(request, String.class);
        if (response != null && response.isSuccess())
            view.showConfirmMessage(response.getMessage() != null ? response.getMessage() : "Vacunación registrada con éxito");
        else {
            String msg = (response != null) ? response.getMessage() : "Respuesta inválida del servidor";
            view.showErrorMessage(msg != null ? msg : "Error al registrar vacunación");
        }
    }

    public void searchPersonById(String documentNumber) {
        try {
            trySearchPersonById(documentNumber);
        } catch (Exception e) {
            e.printStackTrace();
            view.showErrorMessage("Error al buscar usuario");
        }
    }

    private void trySearchPersonById(String documentNumber) throws IOException {
        Request request = new Request("SEARCH_PERSON", documentNumber);
        Type type = new TypeToken<Response<PersonData>>() {}.getType();
        Response<PersonData> response = connection.sendResponse(request, type);
        searchPersonViewActions(documentNumber, response);
    }

    private void searchPersonViewActions(String documentNumber, Response<PersonData> response) {
        if (response.isSuccess() && response.getData() != null) {
            PersonData data = response.getData();
            view.fillUserLabels(data);
            view.fillVaccineTable(data.getVaccinateList());
        } else
            view.showErrorMessage(response.getMessage());
    }

    public void searchVaccineByName(String vaccineName) {
        try {
            Request request = new Request("SEARCH_VACCINE", vaccineName);
            Response<Vaccine> response = connection.sendResponse(request, Vaccine.class);
            if (response.isSuccess() && response.getData() != null) {
                view.fillVaccineLabels(response.getData());
            } else {
                view.showErrorMessage("Vacuna no encontrada");
            }
        } catch (Exception e) {
            view.showErrorMessage("Error al buscar vacuna");
        }
    }

    @Override
    public void searchUser(String documentNumber) {
        try {
            Request request = new Request("SEARCH_PERSON", documentNumber);
            Response<PersonData> response = connection.sendResponse(request, PersonData.class);
            if (!response.isSuccess() || response.getData() == null) {
                view.showErrorMessage("Usuario no encontrado");
                return;
            }
            view.fillUserLabels(response.getData());
            if (response.getData().getVaccinateList() != null) {
                view.fillVaccineTable(response.getData().getVaccinateList());
            } else {
                view.fillVaccineTable(List.of());
            }
        } catch (Exception e) {
            view.showErrorMessage("Error al buscar usuario");
        }
    }

    public List<String> getVaccineNames() {
        try {
            return tryGetVaccineNames();
        } catch (Exception e) {
            view.showErrorMessage("Error al cargar nombres de vacunas");
            return List.of();
        }
    }

    private List<String> tryGetVaccineNames() throws IOException {
        Request request = new Request("GET_VACCINE_NAMES", "");
        Type responseType = new TypeToken<Response<List<String>>>() {}.getType();
        Response<List<String>> response = connection.sendResponse(request, responseType);
        if (!response.isSuccess() || response.getData() == null) {
            view.showErrorMessage("No se pudieron cargar los nombres de las vacunas");
            return List.of();
        }
        return response.getData();
    }

    public void closeConnection() {
        try {
            if (connection != null) connection.close();
        } catch (Exception e) {
            view.showErrorMessage("Error al cerrar la conexión con el servidor");
        }
    }

    public List<Vaccinate> getVaccinesForUsers(String documentNumber) {
        try {
            Request request = new Request("GET_VACCINE_FOR_USER", documentNumber);
            Response<Vaccinate[]> response = connection.sendResponse(request, Vaccinate[].class);
            if (response.isSuccess() && response.getData() != null)
                return List.of(response.getData());
            else 
                return List.of();
        } catch (Exception e) {
            view.showErrorMessage("Error al obtener vacunas del usuario");
            return List.of();
        }
    }

    public void updateVaccineFromTable(int rowIndex, Vaccine updateVaccine, Date applicationDate, long documentNumber) {
        try {
            UpdateVaccinatePayLoad payLoad = new UpdateVaccinatePayLoad(rowIndex, String.valueOf(documentNumber), updateVaccine, applicationDate);
            Request request = new Request("UPDATE_VACCINATE", connection.getGson().toJson(payLoad));
            Response<String> response = connection.sendResponse(request, String.class);
            if (response.isSuccess()) {
                List<Vaccinate> vaccines = getVaccinesForUsers(String.valueOf(documentNumber));
                view.fillVaccineTable(vaccines);
            } else
                view.showErrorMessage(response.getMessage());
        } catch (Exception e) {
            view.showErrorMessage("Error al cargar datos");
        }
    }

    public void sendCloseMessage() {
        try {
            Request request = new Request("CLOSE_CONNECTION", "Cliente cerrando sesión");
            Response<String> response = connection.sendResponse(request, String.class);
        } catch (Exception e) {
        }
    }

    public void selectHostAndPort(String host, int port, AskInputView inputView) {
        this.host2 = host;
        this.port1 = port;
        try {
            connection = new ServerConnection(host2, port1);
            inputView.showConfirmMessage("Conectado al servidor correctamente");
        } catch (Exception e) {
            inputView.showErrorMessage("No se pudo conectar al servidor.\nSe recomienda verificar:\n -El servidor esta corriendo\n -El host y el port son correctos\n -Reinicie el programa");
        }
    }

    public void askForConnectionData(AskInputView inputView) {
        String portString = inputView.askInput("Ingrese el puerto:");
        String host = inputView.askInput("Ingrese el host del servidor:");
        try {
            int port = Integer.parseInt(portString);
            selectHostAndPort(host, port, inputView);
        } catch (NumberFormatException e) {
            inputView.showErrorMessage("El puerto debe ser un número.");
        }
    }

    public void setView(ViewInterface view) {
        this.view = view;
    }
}
