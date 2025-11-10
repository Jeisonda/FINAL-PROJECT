package co.edu.uptc.interfaces;

public interface ModeInfertace {
    
    void createUser(String firstName, String middleName, String lastName, String seconLastName, 
    String documentType, String documentNumber, String bornDate, String phoneNumber, String email);

    void createVaccine(String vaccineName, String manuName, String disease, String expiDate, String vaccineType, 
    String batchNumber, String dose, String id);

    void vaccined(String documentName, String vaccineName);

    void searchUser(String documentNumber);
} 
