package co.edu.uptc.presenter;

import java.util.Date;
import java.util.List;

import com.google.gson.JsonSyntaxException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import co.edu.uptc.model.Person;
import co.edu.uptc.model.PersonMapper;
import co.edu.uptc.model.Vaccinate;
import co.edu.uptc.model.Vaccine;
import co.edu.uptc.model.VaccineModel;
import co.edu.uptc.model.dto.PersonResponseDTO;
import co.edu.uptc.net.Connection;
import co.edu.uptc.net.dto.Request;
import co.edu.uptc.net.dto.Response;

public class ServerController extends Thread implements PresenterInterface {
   private Connection connection;
   private VaccineModel model;
   private DataInputStream input;
   private DataOutputStream output;
   private Socket socket;

   public ServerController(Socket socket, VaccineModel model) {
      try {
         this.connection = new Connection(socket);
         this.model = model;
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void VaccineFunctions() {
      int option = 0;
      do {
         try {
            response.
         }
      }
    }

   private void dictionaryFunctions() {
      int option = 0;
      do {
         Request request = new Request();
         Response response = new Response();
         try {
            response.setDictionaries(dictionaryLibrary.dictionariesNames());
            connection.getOutput().writeUTF(connection.getMyGson().toJson(response, response.getClass()));
            request = connection.getMyGson().fromJson(connection.getInput().readUTF(), request.getClass());
            option = request.getOption();
            if (option != 4) {
               handleOptions(option, response, request);
            }
         } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
            option = 4;
         }
      } while (option != 4);
   }

   public void run() {
      try {
         output = new DataOutputStream(socket.getOutputStream());
         input = new DataInputStream(socket.getInputStream());
         while (true) {
            init();
         }
      } catch (IOException e) {
      }
   }

   private void init() {
      try {

      } catch (IOException e) {
      }
   }

   private void dictionaryFunctions() {
      int option = 0;
      do {
         Request request = new Request();
         Response response = new Response();
         try {
            response.setDictionaries(dictionaryLibrary.dictionariesNames());
            connection.getOutput().writeUTF(connection.getMyGson().toJson(response, response.getClass()));
            request = connection.getMyGson().fromJson(connection.getInput().readUTF(), request.getClass());
            option = request.getOption();
            if (option != 4) {
               handleOptions(option, response, request);
            }
         } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
            option = 4;
         }
      } while (option != 4);
   }

   private void handleOptions(int option, Response response, Request request) {
      switch (option) {
         case 1:
            response.setTraduction(dictionaryLibrary.searchTraduction(request.getWord(), request.getDictionary()));
            break;
         case 2:
            dictionaryLibrary.addTerm(new Term(request.getWord(), request.getTraduction()),
                  request.getDictionary());
            break;
         case 3:
            response.setTotalWords(dictionaryLibrary.obtainTotalWords(request.getDictionary()));
            break;
      }
      try {
         connection.getOutput().writeUTF(connection.getMyGson().toJson(response, response.getClass()));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   // Correguir

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
      VaccinationDTO vaccinate = connection.getMyGson().fromJson(request.getData(), VaccinationDTO.class);
      if (vaccinate.getApplicationDate() == null)
         vaccinate.setApplicationDate(new Date());
      this.model.vaccinate(vaccinate.getDocumentNumber(), vaccinate.getVaccineName(), vaccinate.getApplicationDate());
   }

   public void searchVaccineByName(String vaccineName) {
      Vaccine vaccine = this.model.getVaccineByName(vaccineName);
      if (vaccine != null) {
         Response<Vaccine> response = new Response<>(true, null, vaccine);
         String jsonResponse = connection.getMyGson().toJson(response);
         connection.getOutput().writeUTF(jsonResponse);
      }
   }

   public Response<Person> searchUserById(Request request) {
      try {
         Person person = this.model.getUserByDocument(request.getData());
         if (person != null)
            return new Response<>(true, null, person);
         else
            return new Response<>(false, "El usuario no fue encontrado", null);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void searchUser(Request request) {
      try {
         Person person = this.model.getUserByDocument(request.getData());
         if (person != null) {
            PersonResponseDTO dto = PersonMapper.toResponseDTO(person);
            Response<PersonResponseDTO> response = new Response<>(true,"Usuario encontrado", dto);
            connection.getOutput().writeUTF(connection.getMyGson().toJson(response));
         } else {
            Response<PersonResponseDTO> response = new Response<>(false,"Usuario no encontrado",null);
            connection.getOutput().writeUTF(connection.getMyGson().toJson(response));
         }
      } catch (Exception e) {
         e.printStackTrace();
         Response<PersonResponseDTO> response = new Response<>(false,"Error al buscar usuario: " + e.getMessage(),null);
         try {
            connection.getOutput().writeUTF(connection.getMyGson().toJson(response));
         } catch (Exception ex) {
         }
      }
   }

   public List<String> getVaccineNames() {
      return this.model.getVaccineNames();
   }

   public List<Vaccinate> getVaccinesForUsers(String documentNumber) {
      return this.model.getVaccinesForUsers(documentNumber);
   }

   public void updateVaccineFromTable(int rowIndex, VaccineModel updateVaccine, Date applicationDate,
         String documentNumber) {
      this.model.updateVaccineFromTable(rowIndex, updateVaccine, applicationDate, documentNumber);
   }
}
