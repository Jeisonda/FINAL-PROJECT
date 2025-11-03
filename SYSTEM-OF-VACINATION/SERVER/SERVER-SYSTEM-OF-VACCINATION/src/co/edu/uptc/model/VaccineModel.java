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
import co.edu.uptc.net.OperationResult;
import co.edu.uptc.persistence.ConfigGlobal;

public class VaccineModel {
    private BinaryTree<Person> persons;
    private BinaryTree<Vaccine> vaccines;

    public VaccineModel() {
    }

    // public VaccineModel(ViewInterface view){
    //     this.view = view;
    // }

    public boolean createUser(String firstName, String middleName, String lastName, String seconLastName, 
    String documentType, String documentNumber, Date bornDate, String phoneNumber, String email){
        if (userExist(documentNumber)) return false;
        Person newPerson = new Person(firstName, middleName, lastName, seconLastName, documentType, email, documentNumber,
            phoneNumber, bornDate);
        persons.add(newPerson);
        saveJsonUser(persons);
        return true;
    }

    public boolean createVaccine(String vaccineName, String manufacterName, String diseaseName, String vaccineType,
            String batchNumber, Date expirationDate, String totalDose){
        if (vaccineExist(vaccineName)) return false;
        Vaccine newVaccine = new Vaccine(vaccineName, manufacterName, diseaseName, vaccineType, batchNumber, expirationDate, Integer.parseInt(totalDose));
        vaccines.add(newVaccine);
        saveJsonVaccine(vaccines);
        return true;
    }


    public OperationResult vaccinate(String documentNumber, String vaccineName, Date applicationDate) {
        Person person = getUserByDocument(documentNumber);
        Vaccine vaccine = getVaccineByName(vaccineName);
        OperationResult operationResult = validations(documentNumber, vaccineName);
        if (operationResult != null) return operationResult;
        
        int appliedDoses = getAppledDoses(documentNumber, vaccineName);
        int totalDoses = vaccine.getTotalDose();

        if (appliedDoses >= totalDoses) {
            return new OperationResult(false, "Esta persona ya recibió todas las dosis (" + totalDoses + ") de la vacuna " + vaccineName);
        }

        int currentDoseNumber = appliedDoses + 1;

        Vaccinate existingRecord = null;
        BinaryTree<Vaccinate> vaccinates = person.getMyVacinations();
        Vaccinate vaccinateRecord = vaccinates.get(new Vaccinate(vaccine));
        if (isSameDay(vaccinateRecord.getApplicationDate(), applicationDate)) {
                existingRecord = vaccinateRecord;
        }

        if (existingRecord != null) {
            existingRecord.setDose(currentDoseNumber);
            persons.get(person).getMyVacinations().remove(vaccinateRecord);
            persons.get(person).getMyVacinations().add(existingRecord);
            saveJsonUser(persons);
            return new OperationResult(true, "Vacuna agregada al registro existente");
        } else {
            Vaccinate newRecord = new Vaccinate(getVaccineByName(vaccineName), applicationDate, currentDoseNumber);
            persons.get(person).getMyVacinations().add(newRecord);
            saveJsonUser(persons);
            return new OperationResult(true, "Nuevo registro de vacunación creado");
        }
    }

    //////////////////////////
    private OperationResult validations(String documentNumber, String vaccineName) {
        Person person = getUserByDocument(documentNumber);
        if (person == null) {
            return new OperationResult(false, "Usuario no encontrado");
        }

        Vaccine vaccine = getVaccineByName(vaccineName);
        if (vaccine == null) {
            return new OperationResult(false, "Vacuna no encontrada");
        }
        return null;
    }

    // private Vaccine createVaccineWithDoseNumber(Vaccinate vaccinate, int doseNumber){
    //     Vaccine vaccineWithDose = originalVaccine;
    //     vaccineWithDose.setTotalDose(doseNumber);

    //     vaccineWithDose.setVaccineName(originalVaccine.getVaccineName());
    //     vaccineWithDose.setManufacterName(originalVaccine.getManufacterName());
    //     vaccineWithDose.setDiseaseName(originalVaccine.getDiseaseName());
    //     vaccineWithDose.setExpirationDate(originalVaccine.getExpirationDate());
    //     vaccineWithDose.setVaccineType(originalVaccine.getVaccineType());
    //     vaccineWithDose.setBatchNumber(originalVaccine.getBatchNumber());
    //     vaccineWithDose.setDose(doseNumber);
    //     return vaccineWithDose;
    // }

    public List<String> getVaccineNames(){
        List<String> vaccineNames = new ArrayList<>();
        List<Vaccine> vaccines = loadJsonVaccine();
        for (Vaccine vaccine : vaccines) {
            vaccineNames.add(vaccine.getVaccineName());
        }
        return vaccineNames;
    }

    public Vaccine getVaccineByName(String vaccineName){
        Vaccine tempVaccine = new Vaccine(vaccineName);
        return vaccines.get(tempVaccine);
    }

    public BinaryTree<Vaccinate> getVaccinesForUsers(String documentNumber){
        Person person = getUserByDocument(documentNumber);
        return person.getMyVacinations();
    }

    public Person getUserByDocument(String documentNumber){
        Person tempPerson = new Person(documentNumber);
        return persons.get(tempPerson);
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

    private boolean userExist(String documentNumber){
        Person tempPerson = new Person();
        tempPerson.setDocumentNumber(documentNumber);
        return persons.contains(tempPerson);
    }


    private boolean vaccineExist(String vaccineName){
        Vaccine tempVaccine = new Vaccine();
        tempVaccine.setVaccineName(vaccineName);
        return vaccines.contains(tempVaccine);
    }

    private boolean isSameDay(Date date1, Date date2){
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    private int getAppledDoses(String documentNumber, String vaccineName){
        Person person = getUserByDocument(documentNumber);
        return person.getMyVacinations().get(new Vaccinate(new Vaccine(vaccineName))).getDose();
    }
}
