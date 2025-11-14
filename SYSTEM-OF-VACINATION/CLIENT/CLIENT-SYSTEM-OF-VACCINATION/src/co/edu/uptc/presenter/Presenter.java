package co.edu.uptc.presenter;

import java.util.Date;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.net.dto.Request;
import co.edu.uptc.net.dto.Response;
import co.edu.uptc.net.dto.UpdateVaccinatePayLoad;
import co.edu.uptc.netWork.ServerConnection;
import co.edu.uptc.pojos.Person;
import co.edu.uptc.pojos.PersonData;
import co.edu.uptc.pojos.Vaccinate;
import co.edu.uptc.pojos.Vaccine;
import co.edu.uptc.views.MainFrame;

public class Presenter implements PresenterInterface {

    private ViewInterface view;
    private ServerConnection connection;
    private int port1;
    private String host2;

    public Presenter(ViewInterface view) {
        this.view = view;
    }

    public Presenter() {
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
            Person person = new Person(firstName, middleName, lastName, seconLastName,
                    documentType, documentNumber, bornDate, phoneNumber, email);

            Request request = new Request("CREATE_PERSON", connection.getGson().toJson(person));
            Response<String> response = connection.sendResponse(request, String.class);

            if (response.isSuccess()) {
                view.showConfirmMessage(response.getMessage());
            } else {
                view.showErrorMessage(response.getMessage());
            }
        } catch (Exception e) {
            view.showErrorMessage("Error al crear usuario");
        }
    }

    @Override
    public void createVaccine(String vaccineName, String manuName, String disease, Date expiDate, String vaccineType,
            String batchNumber, String dose) {
        if (vaccineName.isEmpty() || manuName.isEmpty() || disease.isEmpty() || expiDate == null ||
                vaccineType.isEmpty() || batchNumber.isEmpty() || dose.isEmpty()) {
            view.showErrorMessage("Todos los campos deben llenarse");
            return;
        }
        try {
            Vaccine vaccine = new Vaccine(vaccineName, manuName, disease, expiDate, vaccineType,
                    batchNumber, Integer.parseInt(dose));

            Request request = new Request("CREATE_VACCINE", connection.getGson().toJson(vaccine));
            Response<String> response = connection.sendResponse(request, String.class);
            if (response.isSuccess()) { // 🔹 CAMBIO

                view.showConfirmMessage(response.getMessage());
                view.refreshComboFindVaccine();
            } else {
                view.showErrorMessage(response.getMessage());
            }

        } catch (Exception e) {
            view.showErrorMessage("Error al crear vacuna");
        }

    }

    @Override
    public void vaccined(String documentNumber, String vaccineName, Date applicatoinDate) {
        try {
            if (applicatoinDate == null) {
                applicatoinDate = new Date();
            }
            Vaccinate vaccinate = new Vaccinate(documentNumber, new Vaccine(vaccineName), applicatoinDate, 1);

            Request request = new Request("VACCINATE", connection.getGson().toJson(vaccinate));
            Response<String> response = connection.sendResponse(request, String.class);

            if (response.isSuccess()) {
                view.showConfirmMessage("Vacunación registrada con éxito");
            } else {
                view.showErrorMessage(response.getMessage());
            }

        } catch (Exception e) {
            view.showErrorMessage("Error al registrar vacunación");
        }
    }

    // public void searchUserById(String documentNumber) {
    // try {
    // Request request = new Request("SEARCH_PERSON", documentNumber);
    // Response<Person> response = connection.sendResponse(request, Person.class);

    // if (response.isSuccess() && response.getData() != null) { // 🔹 CAMBIO
    // view.fillUserLabels(response.getData());
    // } else {
    // view.showErrorMessage("Usuario no encontrado");
    // }
    // } catch (Exception e) {
    // view.showErrorMessage("Usuarió al buscar usuario");
    // }
    // }

    public void searchPersonById(String documentNumber) {
        try {
            Request request = new Request("SEARCH_PERSON", documentNumber);

            Type type = new TypeToken<Response<PersonData>>() {
            }.getType();
            Response<PersonData> response = connection.sendResponse(request, type);

            if (response.isSuccess() && response.getData() != null) {
                PersonData data = response.getData();
                view.fillUserLabels(data);
                view.fillVaccineTable(data.getVaccinateList());
            } else {
                view.showErrorMessage(response.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            view.showErrorMessage("Error al buscar usuario");
        }
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

            /*
             * Request reqVaccines = new Request("GET_VACCINES_FOR_USER", documentNumber);
             * Response<Vaccinate[]> vacResponse = connection.sendResponse(reqVaccines,
             * Vaccinate[].class);
             */
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
            Request request = new Request("GET_VACCINE_NAMES", "");
            
            Type responseType = new TypeToken<Response<List<String>>>() {
            }.getType();
            Response<List<String>> response = connection.sendResponse(request, responseType);
            if (!response.isSuccess() || response.getData() == null) { // 🔹 CAMBIO
                view.showErrorMessage("No se pudieron cargar los nombres de las vacunas");
                return List.of();
            }

            return response.getData();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            view.showErrorMessage("Error al cargar nombres de vacunas");
            return List.of();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            view.showErrorMessage("Error al cerrar la conexión con el servidor");
        }
    }

    public List<Vaccinate> getVaccinesForUsers(String documentNumber) {
        try {
            Request request = new Request("GET_VACCINE_FOR_USER", documentNumber);
            Response<Vaccinate[]> response = connection.sendResponse(request, Vaccinate[].class);

            if (response.isSuccess() && response.getData() != null) {
                return List.of(response.getData());
            } else {
                return List.of();
            }

        } catch (Exception e) {
            view.showErrorMessage("Error al obtener vacunas del usuario");
            return List.of();
        }
    }

    public void updateVaccineFromTable(int rowIndex, Vaccine updateVaccine, Date applicationDate,
            long documentNumber) {
        try {
            UpdateVaccinatePayLoad payLoad = new UpdateVaccinatePayLoad(rowIndex, String.valueOf(documentNumber),
                    updateVaccine, applicationDate);
            Request request = new Request("UPDATE_VACCINATE", connection.getGson().toJson(payLoad));
            Response<String> response = connection.sendResponse(request, String.class);
            if (response.isSuccess()) { // 🔹 CAMBIO
                List<Vaccinate> vaccines = getVaccinesForUsers(String.valueOf(documentNumber));
                view.fillVaccineTable(vaccines);
            } else {
                view.showErrorMessage(response.getMessage());
            }
        } catch (Exception e) {
            view.showErrorMessage("Error al cargar datos");
        }
    }

    public void sendCloseMessage() {
        try {
            Request request = new Request("CLOSE_CONNECTION", "Cliente cerrando sesión");
            Response<String> response = connection.sendResponse(request, String.class);
            System.out.println("Servidor confirmó cierre: " + response.getMessage());
        } catch (Exception e) {
            System.out.println("No se pudo notificar al servidor el cierre de conexión.");
        }
    }

    public void run() {
        MainFrame main = new MainFrame(this);
        main.setVisible(true);
    }

    public void selectHostAndPort(String host, int port) {
        this.host2 = host;
        this.port1 = port;
        try {
            if (connection != null) {
                connection.close();
            }
            connection = new ServerConnection(host2, port1);
            view.showConfirmMessage("Conectado al servidor correctamente");
        } catch (Exception e) {
            view.showErrorMessage("No se pudo conectar al servidor: " + e.getMessage());
        }
    }
}
