package model;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import persistence.ConfigGlobal;

public class MainModel {

    public MainModel(ViewInterface view){
        this.view = view;
    }

    public List<UserModel> createUser(String firstName, String middleName, String lastName, String seconLastName, 
    String documentType, String documentNumber, Date bornDate, String phoneNumber, String email){
        List<UserModel> users = loadJsonUser();
        long docNum = Long.parseLong(documentNumber);
    
        if (userExist(users, docNum)) {
            view.showErrorMessage("Este usuario ya existe");
            return users;
        }

        UserModel newUser = new UserModel();
        newUser.setFirstName(firstName);
        newUser.setMiddleName(middleName);
        newUser.setLastName(seconLastName);
        newUser.setSecondLastName(seconLastName);
        newUser.setDocumentType(documentType);
        newUser.setDocumentNumber(Long.parseLong(documentNumber));
        newUser.setBornDate(bornDate);
        newUser.setPhoneNumber(Long.parseLong(phoneNumber));
        newUser.setEmail(email);
        users.add(newUser);
        saveJsonUser(users);
        return users;
    }

    public List<VaccineModel> createVaccine(String vaccineName, String manuName, String disease, Date expiDate, String vaccineType, 
    String batchNumber, String dose){
        String name = vaccineName;
        List<VaccineModel> vaccines = loadJsonVaccine();
        if (vaccineExist(vaccines, name)) {
            view.showErrorMessage("Esta vacuna ya existe");
        }
        VaccineModel newVaccine = new VaccineModel();
        newVaccine.setVaccineName(vaccineName);
        newVaccine.setManufacterName(manuName);
        newVaccine.setDiseaseName(disease);
        newVaccine.setExpirationDate(expiDate);
        newVaccine.setVaccineType(vaccineType);
        newVaccine.setBatchNumber(batchNumber);
        newVaccine.setDose(Integer.parseInt(dose));

        vaccines.add(newVaccine);
        saveJsonVaccine(vaccines);

        return vaccines;
    }


    public void vaccinate(long documentNumber, String vaccineName, Date applicationDate){
        UserModel user = getUserByDocument(documentNumber);
        if (user == null) {
            view.showErrorMessage("Usuario no encontrado");
            return;
        }

        VaccineModel vaccine = getVaccineByName(vaccineName);
        if (vaccine == null) {
            view.showErrorMessage("Vacuna no encontrada");
            return;
        }

        int appliedDoses = countAppledDoses(documentNumber, vaccineName);
        int totalDoses = vaccine.getDose();
        
        if (appliedDoses >= totalDoses) {
            view.showErrorMessage("Esta persona ya ha recibido todas las dosis ("+ totalDoses + ") de la vacuna: " + vaccineName);
            return;
        }      

        int currentDoseNumber = appliedDoses + 1;

        List<DateModel> history = loadJsonHistory();

        DateModel exixtingRecord = null;
        for (DateModel record : history) {
            if (record.getPerson().getDocumentNumber() == documentNumber &&
                isSameDay(record.getApplicationDate(), applicationDate)) {
                exixtingRecord = record;
                break;
            }
        }

        VaccineModel vaccineWithDose = createVaccineWithDoseNumber(vaccine, currentDoseNumber);
        if (exixtingRecord != null) {
            exixtingRecord.getVaccines().add(vaccineWithDose);
            view.showConfirmMessage("Vacunar agregada al registro existente");
        }else{
            DateModel newRecord = new DateModel();
            newRecord.setPerson(user);
            newRecord.setVaccines(new ArrayList<>(List.of(vaccineWithDose)));
            newRecord.setApplicationDate(applicationDate);

            history.add(newRecord);
            view.showConfirmMessage("Nuevo Registo de vacunación creado");
        }
        saveJsonHistory(history);
    }

    private VaccineModel createVaccineWithDoseNumber(VaccineModel originalVaccine, int doseNumber){
        VaccineModel vaccineWithDose = new VaccineModel();
        vaccineWithDose.setVaccineName(originalVaccine.getVaccineName());
        vaccineWithDose.setManufacterName(originalVaccine.getManufacterName());
        vaccineWithDose.setDiseaseName(originalVaccine.getDiseaseName());
        vaccineWithDose.setExpirationDate(originalVaccine.getExpirationDate());
        vaccineWithDose.setVaccineType(originalVaccine.getVaccineType());
        vaccineWithDose.setBatchNumber(originalVaccine.getBatchNumber());
        vaccineWithDose.setDose(doseNumber);
        return vaccineWithDose;
    }
    public List<String> getVaccineNames(){
        List<String> vaccineNames = new ArrayList<>();
        List<VaccineModel> vaccines = loadJsonVaccine();
        for (VaccineModel vaccine : vaccines) {
            vaccineNames.add(vaccine.getVaccineName());
        }
        return vaccineNames;
    }

    public VaccineModel getVaccineByName(String vaccineName){
        List<VaccineModel> vaccines = loadJsonVaccine();
        for (VaccineModel vaccineModel : vaccines) {
            if (vaccineModel.getVaccineName().equalsIgnoreCase(vaccineName)) {
                return vaccineModel;
            }
        }
        return null;
    }

    public List<DateModel> getVaccinesForUsers(long documentNumber){
        List<DateModel> appliedHistory = new ArrayList<>();
        List<DateModel> history = loadJsonHistory();
        for (DateModel dateModel : history) {
            if (dateModel.getPerson().getDocumentNumber() == documentNumber) {
                appliedHistory.add(dateModel);
            }
        }
        return appliedHistory;
    }

    public UserModel getUserByDocument(long documentNumber){
        List<UserModel> users = loadJsonUser();
        for (UserModel userModel : users) {
            if (userModel.getDocumentNumber() == documentNumber) {
                return userModel;
            }
        }
        return null;
    }

    public void updateVaccineFromTable(int rowIndex, VaccineModel  updateVaccine, Date applicationDate, long documentNumber){
        List<DateModel> history = loadJsonHistory();
        for (DateModel record : history) {
            if (record.getPerson().getDocumentNumber() == documentNumber && isSameDay(record.getApplicationDate(), applicationDate) ) {
                List<VaccineModel> vaccines = record.getVaccines();
                if (rowIndex < vaccines.size()) {
                    vaccines.set(rowIndex, updateVaccine);
                    saveJsonHistory(history);  
                    return;
                }
            }
        }
    }

    private List<UserModel> loadJsonUser() {
        try (FileReader reader = new FileReader(ConfigGlobal.userData)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(reader, new TypeReference<List<UserModel>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<VaccineModel> loadJsonVaccine() {
        try (FileReader reader = new FileReader(ConfigGlobal.vaccineData)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(reader, new TypeReference<List<VaccineModel>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<DateModel> loadJsonHistory() {
        try (FileReader reader = new FileReader(ConfigGlobal.historyData)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(reader, new TypeReference<List<DateModel>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveJsonUser(List<UserModel> data){
        try (FileWriter writer = new FileWriter(ConfigGlobal.userData)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveJsonVaccine(List<VaccineModel> data){
        try (FileWriter writer = new FileWriter(ConfigGlobal.vaccineData)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveJsonHistory(List<DateModel> data){
        try (FileWriter writer = new FileWriter(ConfigGlobal.historyData)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean userExist(List<UserModel> users, long documentNumber){
        for (UserModel user : users) {
            if (user.getDocumentNumber() == documentNumber) {
                return true;
            }
        }
        return false;
    }


    private boolean vaccineExist(List<VaccineModel> vaccines, String vaccineName){
        for (VaccineModel vaccine : vaccines) {
            if (vaccine.getVaccineName().equalsIgnoreCase(vaccineName)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSameDay(Date date1, Date date2){
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    private int countAppledDoses(long documentNumber, String vaccineName){
        int count = 0;
        List<DateModel> history = loadJsonHistory();

        for (DateModel record : history) {
            if (record.getPerson().getDocumentNumber() == documentNumber) {
                for (VaccineModel vaccine : record.getVaccines()) {
                    if (vaccine.getVaccineName().equalsIgnoreCase(vaccineName)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
