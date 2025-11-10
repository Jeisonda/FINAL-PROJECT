package co.edu.uptc.control;

import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.net.Socket;
import co.edu.uptc.model.Person;
import co.edu.uptc.model.Vaccinate;
import co.edu.uptc.model.Vaccine;
import co.edu.uptc.model.VaccineModel;
import co.edu.uptc.net.Connection;
import co.edu.uptc.net.dto.Request;
import co.edu.uptc.net.dto.Response;

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
            request = connection.getMyGson().fromJson(connection.getInput().readUTF(), request.getClass());
            String option = request.getAction();
            handleOptions(option, request);
         } catch (Exception e) {
         }
      }
   }

   private void handleOptions(String option, Request request) {
      try {
         switch (option) {
            case "CREATE_PERSON" -> createUser(request);
            case "CREATE_VACCINE" -> createVaccine(request);
            case "VACCINATE" -> vaccined(request);
            default -> programOptions(option, request);
         }
      } catch (Exception e) {
      }
   }

   private void programOptions(String option, Request request) {
      try {
         switch (option) {
            case "SEARCH_VACCINE" -> createUser(request);
            case "SEARCH_PERSON" -> createVaccine(request);
            case "GET_VACCINES" -> vaccined(request);
            case "GET_VACCINATE_PERSON" -> vaccined(request);
            case "UPDATE_VACCINE" -> vaccined(request);
            default -> vaccined(request);
         }
      } catch (Exception e) {
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
      Vaccinate vaccinate = connection.getMyGson().fromJson(request.getData(), Vaccinate.class);
      if (vaccinate.getApplicationDate() == null)
         vaccinate.setApplicationDate(new Date());
      this.model.vaccinate(vaccinate.getDocumentNumber(), vaccinate.getVaccine().getVaccineName(),
            vaccinate.getApplicationDate());
   }

   public void searchVaccineByName(String vaccineName) {
      try {
         Vaccine vaccine = this.model.getVaccineByName(vaccineName);
         if (vaccine != null) {
            Response<Vaccine> response = new Response<>(true, null, vaccine);
            String jsonResponse = connection.getMyGson().toJson(response);
            connection.getOutput().writeUTF(jsonResponse);
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void searchUserById(Request request) {
      try {
         Response<Person> response;
         Person person = this.model.getUserByDocument(request.getData());
         if (person != null)
            response = new Response<>(true, null, person);
         else
            response = new Response<>(false, "El usuario no fue encontrado", null);
         String jsonResponse = connection.getMyGson().toJson(response);
         connection.getOutput().writeUTF(jsonResponse);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /*
   public void searchUser(Request request) {
      try {
         Person person = this.model.getUserByDocument(request.getData());
         if (person != null) {
            PersonData personPojo = new PersonData(person,
            getVaccinesForUsers(person.getDocumentNumber()));
            Response<PersonData> response = new Response<>(true, "Usuario encontrado",
            personPojo);
            connection.getOutput().writeUTF(connection.getMyGson().toJson(response));
         } else {
            Response<PersonData> response = new Response<>(false,
            "Usuario no encontrado", null);
            connection.getOutput().writeUTF(connection.getMyGson().toJson(response));
         }
      } catch (Exception e) {
         e.printStackTrace();
         Response<PersonData> response = new Response<>(false,
         "Error al buscar usuario: " + e.getMessage(), null);
         try {
            connection.getOutput().writeUTF(connection.getMyGson().toJson(response));
         } catch (Exception ex) {
         }
      }
   }
    */

   public List<String> getVaccineNames() {
      return this.model.getVaccineNames();
   }

   public List<Vaccinate> getVaccinesForUsers(String documentNumber) {
      return this.model.getVaccinesForUsers(documentNumber);
   }

   public void updateVaccineFromTable(int rowIndex, Vaccine updateVaccine, Date applicationDate,
         String documentNumber) {
      this.model.updateVaccineFromTable(rowIndex, updateVaccine, applicationDate, documentNumber);
   }
}
