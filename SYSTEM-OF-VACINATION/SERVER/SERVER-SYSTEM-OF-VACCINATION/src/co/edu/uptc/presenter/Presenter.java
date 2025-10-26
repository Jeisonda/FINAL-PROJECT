package co.edu.uptc.presenter;

import interfaces.PresenterInterface;
import interfaces.ViewInterface;
import java.util.Date;
import java.util.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import co.edu.uptc.model.VaccineModel;

public class Presenter extends Thread implements PresenterInterface {
    private VaccineModel model;
    private DataInputStream input;
    private DataOutputStream output;
    private Socket socket;

    public Presenter(Socket socket, VaccineModel model) {
        this.socket = socket;
        this.model = model;
    }

    public void run() {
        try {
            output = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(socket.getInputStream());
            while (true) {
                init();
            }
        } catch (IOException e) {}
    }

    private void init() {
        try {
            
        } catch (IOException e) {}
    }

    // Correguir

   public void createUser(String firstName, String middleName, String lastName, String seconLastName, String documentType, String documentNumber, Date bornDate, String phoneNumber, String email) {
      if (!firstName.isEmpty() && !lastName.isEmpty() && !seconLastName.isEmpty() && !documentType.isEmpty() && !documentNumber.isEmpty() && bornDate != null && !phoneNumber.isEmpty() && !email.isEmpty()) {
         try {
            this.model.createUser(firstName, middleName, lastName, seconLastName, documentType, documentNumber, bornDate, phoneNumber, email);
            this.view.showConfirmMessage("Creado con exito");
         } catch (Exception var11) {
            this.view.showErrorMessage("Error al crear usuario");
         }

      } else {
         this.view.showErrorMessage("Todos los campos tienen que llenarse");
      }
   }

   public void createVaccine(String vaccineName, String manuName, String disease, Date expiDate, String vaccineType, String batchNumber, String dose) {
      try {
         this.model.createVaccine(vaccineName, manuName, disease, expiDate, vaccineType, batchNumber, dose);
         this.view.showConfirmMessage("Creada con exito");
         this.view.refreshComboFindVaccine();
      } catch (Exception var9) {
         if (vaccineName.isEmpty() || manuName.isEmpty() || disease.isEmpty() || expiDate == null || vaccineType.isEmpty() || batchNumber.isEmpty() || dose.isEmpty()) {
            this.view.showErrorMessage("Todos los campos tienen que llenarse");
         }
      }

   }

   public void vaccined(String documentNumber, String vaccineName, Date applicatoinDate) {
      if (applicatoinDate == null) {
         applicatoinDate = new Date();
      }

      this.model.vaccinate(Long.parseLong(documentNumber), vaccineName, applicatoinDate);
   }

   public void searchVaccineByName(String vaccineName) {
      VaccineModel vaccine = this.model.getVaccineByName(vaccineName);
      if (vaccine != null) {
         this.view.fillVaccineLabels(vaccine);
      }

   }

   public void searchUserById(String documentNumber) {
      try {
         UserModel user = this.model.getUserByDocument(Long.parseLong(documentNumber));
         if (user != null) {
            this.view.fillUserLabels(user);
         }
      } catch (Exception var3) {
         this.view.showErrorMessage("Usuarió no encontrado");
      }

   }

   public void searchUser(String documentNumber) {
      try {
         long document = Long.parseLong(documentNumber);
         UserModel user = this.model.getUserByDocument(document);
         if (user != null) {
            this.view.fillUserLabels(user);
            List<DateModel> vaccines = this.model.getVaccinesForUsers(document);
            this.view.fillVaccineTable(vaccines);
         }
      } catch (Exception var6) {
         this.view.showErrorMessage("Usuario no encontrado");
      }

   }

   public List<String> getVaccineNames() {
      return this.model.getVaccineNames();
   }

   public List<DateModel> getVaccinesForUsers(String documentNumber) {
      return this.model.getVaccinesForUsers(Long.parseLong(documentNumber));
   }

   public void updateVaccineFromTable(int rowIndex, VaccineModel updateVaccine, Date applicationDate, long documentNumber) {
      this.model.updateVaccineFromTable(rowIndex, updateVaccine, applicationDate, documentNumber);
   }
}
