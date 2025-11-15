package co.edu.uptc.control;

import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.net.Socket;
import co.edu.uptc.model.Person;
import co.edu.uptc.model.Vaccinate;
import co.edu.uptc.model.Vaccine;
import co.edu.uptc.model.VaccineModel;
import co.edu.uptc.model.pojos.PersonData;
import co.edu.uptc.net.Connection;
import co.edu.uptc.net.dto.Request;
import co.edu.uptc.net.dto.Response;
import co.edu.uptc.net.dto.UpdateVaccinatePayLoad;
import co.edu.uptc.net.OperationResult;

public class ServerController extends Thread {
   private Connection connection;
   private VaccineModel model;

   public ServerController(Socket socket, VaccineModel model) {
      try {
         this.connection = new Connection(socket);
         this.model = model;
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void run() {
      try {
         systemOfVaccinationFunctions();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         closeConnection();
      }
   }

   public void systemOfVaccinationFunctions() {
      while (true) {
         Request request = new Request();
         try {
            String raw = connection.getInput().readUTF();
            request = connection.getMyGson().fromJson(raw, Request.class);
            String option = request.getAction();
            handleOptions(option, request);
         } catch (IOException e) { break; } 
         catch (Exception e) {
            e.printStackTrace();
            break;
         }
      }
      closeConnection();
   }

   private void handleOptions(String option, Request request) {
      try {
         switch (option) {
            case "CREATE_PERSON" -> createUser(request);
            case "CREATE_VACCINE" -> createVaccine(request);
            case "VACCINATE" -> vaccined(request);
            case "CLOSE_CONNECTION" -> closeClient(request);
            default -> programOptions(option, request);
         }
      } catch (Exception e) {
      }
   }

   private void programOptions(String option, Request request) {
      try {
         switch (option) {
            case "SEARCH_VACCINE" -> searchVaccineByName(request.getData());
            case "SEARCH_PERSON" -> searchUserById(request);
            case "GET_VACCINE_NAMES" -> getVaccineNames();
            case "GET_VACCINE_FOR_USER" -> getVaccinesForUser(request);
            case "UPDATE_VACCINATE" -> updateVaccine(request);
            default -> {
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private void closeConnection() {
      try {
         if (connection.getClient() != null && !connection.getClient().isClosed())
            connection.getClient().close();
      } catch (IOException e) {
      }
   }

   public void createUser(Request request) {
      try {
         Person person = connection.getMyGson().fromJson(request.getData(), Person.class);
         boolean created = this.model.createUser(person);
         Response<String> response = isCreatedUser(created);
         String jsonResponse = connection.getMyGson().toJson(response);
         connection.getOutput().writeUTF(jsonResponse);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private Response<String> isCreatedUser(boolean created) {
      if (created)
         return new Response<>(true, "Usuario creado con éxito", null);
      else
         return new Response<>(false, "El usuario ya existe", null);
   }

   public void createVaccine(Request request) {
      try {
         Vaccine vaccine = connection.getMyGson().fromJson(request.getData(), Vaccine.class);
         boolean created = this.model.createVaccine(vaccine);
         Response<String> response = isCreatedVaccine(created);
         String jsonResponse = connection.getMyGson().toJson(response);
         connection.getOutput().writeUTF(jsonResponse);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private Response<String> isCreatedVaccine(boolean created) {
      if (created)
         return new Response<>(true, "Vacuna creada con éxito", null);
      else
         return new Response<>(false, "La Vacuna ya existe", null);
   }

   public void vaccined(Request request) {
      try {
         Vaccinate vaccinate = connection.getMyGson().fromJson(request.getData(), Vaccinate.class);
         if (vaccinate.getApplicationDate() == null) vaccinate.setApplicationDate(new Date());
         OperationResult result = model.vaccinate(vaccinate.getDocumentNumber(), vaccinate.getVaccine().getVaccineName(),
               vaccinate.getApplicationDate());
         if (result != null && result.isSuccess()) responseSuccessfullVacinate(result);
         else responseUnsuccessfullVacinate(result);
      } catch (Exception e) {
         catchVacciante();
      }
   }

   private void responseSuccessfullVacinate(OperationResult result) throws IOException {
      Response<String> response = new Response<>(true, result.getMessage(), null);
      String jsonResponse = connection.getMyGson().toJson(response);
      connection.getOutput().writeUTF(jsonResponse);
   }

   private void responseUnsuccessfullVacinate(OperationResult result) throws IOException {
      String msg = (result != null && result.getMessage() != null) ? result.getMessage() : "Error al registrar vacunación";
      Response<String> response = new Response<>(false, msg, null);
      connection.getOutput().writeUTF(connection.getMyGson().toJson(response));
   }

   private void catchVacciante() {
      try {
         Response<String> response = new Response<>(false, "Error al registrar vacunación", null);
         connection.getOutput().writeUTF(connection.getMyGson().toJson(response));
      } catch (IOException ex) {
         ex.printStackTrace();
      }
   }

   public void searchVaccineByName(String vaccineName) {
      try {
         Vaccine vaccine = this.model.getVaccineByName(vaccineName);
         if (vaccine != null) {
            responseSuccesfullSearchVaccine(vaccine);
         } else {
            Response<String> response = new Response<>(false, "Vacuna no encontrada", null);
            connection.getOutput().writeUTF(connection.getMyGson().toJson(response));
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void responseSuccesfullSearchVaccine(Vaccine vaccine) throws IOException {
      Response<Vaccine> response = new Response<>(true, null, vaccine);
      String jsonResponse = connection.getMyGson().toJson(response);
      connection.getOutput().writeUTF(jsonResponse);
   }

   private void searchUserById(Request request) {
      try {
         String documentNumber = request.getData();
         Person person = model.getUserByDocument(documentNumber);

         if (person != null)
            responseSuccesfullSearchUser(documentNumber, person);
         else {
            Response<String> response = new Response<>(false, "Usuario no encontrado", null);
            connection.getOutput().writeUTF(connection.getMyGson().toJson(response));
         }
      } catch (Exception e) {
         catchSearchUser(e);
      }
   }

   private void responseSuccesfullSearchUser(String documentNumber, Person person) throws IOException {
      List<Vaccinate> vaccinateList = model.getVaccinesForUsers(documentNumber);
      PersonData personData = new PersonData(person, vaccinateList);
      Response<PersonData> response = new Response<>(true,"Usuario encontrado con su historial", personData);
      String jsonResponse = connection.getMyGson().toJson(response);
      connection.getOutput().writeUTF(jsonResponse);
   }

   private void catchSearchUser(Exception e) {
      try {
         Response<String> response = new Response<>(false, "Error al buscar usuario: " + e.getMessage(), null);
         connection.getOutput().writeUTF(connection.getMyGson().toJson(response));
      } catch (IOException ex) {
         ex.printStackTrace();
      }
   }

   private void getVaccineNames() {
      try {
         List<String> names = model.getVaccineNames();
         Response<List<String>> response = new Response<>(true, "Lista de vacunas obtenida", names);
         String jsonResponse = connection.getMyGson().toJson(response);
         connection.getOutput().writeUTF(jsonResponse);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void getVaccinesForUser(Request request) {
      try {
         List<Vaccinate> vaccines = model.getVaccinesForUsers(request.getData());
         Response<List<Vaccinate>> response = new Response<>(true, "Vacunas obtenidas", vaccines);
         String json = connection.getMyGson().toJson(response);
         connection.getOutput().writeUTF(json);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private void updateVaccine(Request request) {
      try {
         UpdateVaccinatePayLoad payload = connection.getMyGson().fromJson(request.getData(),
               UpdateVaccinatePayLoad.class);
         model.updateVaccineFromTable(payload.getRowIndex(), payload.getVaccine(), payload.getApplicationDate(), 
            payload.getDocumentNumber());
         Response<String> response = new Response<>(true, "Vacunación actualizada", null);
         String json = connection.getMyGson().toJson(response);
         connection.getOutput().writeUTF(json);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public List<Vaccinate> getVaccinesForUsers(String documentNumber) {
      return this.model.getVaccinesForUsers(documentNumber);
   }

   public void updateVaccineFromTable(int rowIndex, Vaccine updateVaccine, Date applicationDate,
         String documentNumber) {
      this.model.updateVaccineFromTable(rowIndex, updateVaccine, applicationDate, documentNumber);
   }

   private void closeClient(Request request) {
      try {
         Response<String> response = new Response<>(true, "Conexión cerrada correctamente", null);
         connection.getOutput().writeUTF(connection.getMyGson().toJson(response));
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         closeConnection();
      }
   }
}
