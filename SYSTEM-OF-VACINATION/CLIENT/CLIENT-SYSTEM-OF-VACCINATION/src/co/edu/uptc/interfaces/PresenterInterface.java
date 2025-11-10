package co.edu.uptc.interfaces;

import java.util.Date;

public interface PresenterInterface {
    
    void createUser(String firstName, String middleName, String lastName, String seconLastName, 
    String documentType, String documentNumber, Date bornDate, String phoneNumber, String email);

    void createVaccine(String vaccineName, String manuName, String disease, Date expiDate, String vaccineType, 
    String batchNumber, String dose);

    void vaccined(String documentNumber, String vaccineName, Date applicationDate);

    void searchUser(String documentNumber);
}
