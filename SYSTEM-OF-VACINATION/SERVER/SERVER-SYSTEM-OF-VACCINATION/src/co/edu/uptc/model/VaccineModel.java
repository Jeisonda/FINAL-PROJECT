package co.edu.uptc.model;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.uptc.structures.BinaryTree;
import persistence.ConfigGlobal;

public class VaccineModel {
    private BinaryTree<Person> person;
    private BinaryTree<Vaccine> vaccines;

    public VaccineModel() {
    }

    public VaccineModel(ViewInterface view){
        this.view = view;
    }

    public List<Person> createUser(String firstName, String middleName, String lastName, String seconLastName, 
    String documentType, String documentNumber, Date bornDate, String phoneNumber, String email){
        List<Person> users = loadJsonUser();
        long docNum = Long.parseLong(documentNumber);
    
        if (userExist(users, docNum)) {
            view.showErrorMessage("Este usuario ya existe");
            return users;
        }

        Person newUser = new Person();
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

    public List<Vaccine> createVaccine(String vaccineName, String manuName, String disease, Date expiDate, String vaccineType, 
    String batchNumber, String dose){
        String name = vaccineName;
        List<Vaccine> vaccines = loadJsonVaccine();
        if (vaccineExist(vaccines, name)) {
            view.showErrorMessage("Esta vacuna ya existe");
        }
        Vaccine newVaccine = new Vaccine();
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
        Person user = getUserByDocument(documentNumber);
        if (user == null) {
            view.showErrorMessage("Usuario no encontrado");
            return;
        }

        Vaccine vaccine = getVaccineByName(vaccineName);
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

        List<Vaccinate> history = loadJsonHistory();

        Vaccinate exixtingRecord = null;
        for (Vaccinate record : history) {
            if (record.getPerson().getDocumentNumber() == documentNumber &&
                isSameDay(record.getApplicationDate(), applicationDate)) {
                exixtingRecord = record;
                break;
            }
        }

        Vaccine vaccineWithDose = createVaccineWithDoseNumber(vaccine, currentDoseNumber);
        if (exixtingRecord != null) {
            exixtingRecord.getVaccines().add(vaccineWithDose);
            view.showConfirmMessage("Vacunar agregada al registro existente");
        }else{
            Vaccinate newRecord = new Vaccinate();
            newRecord.setPerson(user);
            newRecord.setVaccines(new ArrayList<>(List.of(vaccineWithDose)));
            newRecord.setApplicationDate(applicationDate);

            history.add(newRecord);
            view.showConfirmMessage("Nuevo Registo de vacunación creado");
        }
        saveJsonHistory(history);
    }

    private Vaccine createVaccineWithDoseNumber(Vaccine originalVaccine, int doseNumber){
        Vaccine vaccineWithDose = new Vaccine();
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
        List<Vaccine> vaccines = loadJsonVaccine();
        for (Vaccine vaccine : vaccines) {
            vaccineNames.add(vaccine.getVaccineName());
        }
        return vaccineNames;
    }

    public Vaccine getVaccineByName(String vaccineName){
        List<Vaccine> vaccines = loadJsonVaccine();
        for (Vaccine vaccineModel : vaccines) {
            if (vaccineModel.getVaccineName().equalsIgnoreCase(vaccineName)) {
                return vaccineModel;
            }
        }
        return null;
    }

    public List<Vaccinate> getVaccinesForUsers(long documentNumber){
        List<Vaccinate> appliedHistory = new ArrayList<>();
        List<Vaccinate> history = loadJsonHistory();
        for (Vaccinate dateModel : history) {
            if (dateModel.getPerson().getDocumentNumber() == documentNumber) {
                appliedHistory.add(dateModel);
            }
        }
        return appliedHistory;
    }

    public Person getUserByDocument(long documentNumber){
        List<Person> users = loadJsonUser();
        for (Person userModel : users) {
            if (userModel.getDocumentNumber() == documentNumber) {
                return userModel;
            }
        }
        return null;
    }

    public void updateVaccineFromTable(int rowIndex, Vaccine  updateVaccine, Date applicationDate, long documentNumber){
        List<Vaccinate> history = loadJsonHistory();
        for (Vaccinate record : history) {
            if (record.getPerson().getDocumentNumber() == documentNumber && isSameDay(record.getApplicationDate(), applicationDate) ) {
                List<Vaccine> vaccines = record.getVaccines();
                if (rowIndex < vaccines.size()) {
                    vaccines.set(rowIndex, updateVaccine);
                    saveJsonHistory(history);  
                    return;
                }
            }
        }
    }

    private List<Person> loadJsonUser() {
        try (FileReader reader = new FileReader(ConfigGlobal.userData)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(reader, new TypeReference<List<Person>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Vaccine> loadJsonVaccine() {
        try (FileReader reader = new FileReader(ConfigGlobal.vaccineData)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(reader, new TypeReference<List<Vaccine>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Vaccinate> loadJsonHistory() {
        try (FileReader reader = new FileReader(ConfigGlobal.historyData)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(reader, new TypeReference<List<Vaccinate>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveJsonUser(List<Person> data){
        try (FileWriter writer = new FileWriter(ConfigGlobal.userData)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveJsonVaccine(List<Vaccine> data){
        try (FileWriter writer = new FileWriter(ConfigGlobal.vaccineData)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveJsonHistory(List<Vaccinate> data){
        try (FileWriter writer = new FileWriter(ConfigGlobal.historyData)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean userExist(List<Person> users, long documentNumber){
        for (Person user : users) {
            if (user.getDocumentNumber() == documentNumber) {
                return true;
            }
        }
        return false;
    }


    private boolean vaccineExist(List<Vaccine> vaccines, String vaccineName){
        for (Vaccine vaccine : vaccines) {
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
        List<Vaccinate> history = loadJsonHistory();

        for (Vaccinate record : history) {
            if (record.getPerson().getDocumentNumber() == documentNumber) {
                for (Vaccine vaccine : record.getVaccines()) {
                    if (vaccine.getVaccineName().equalsIgnoreCase(vaccineName)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
