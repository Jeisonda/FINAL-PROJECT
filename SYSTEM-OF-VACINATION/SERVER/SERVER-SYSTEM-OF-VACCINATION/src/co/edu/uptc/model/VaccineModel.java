package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import co.edu.uptc.structures.BinaryTree;
import co.edu.uptc.net.OperationResult;
import co.edu.uptc.persistence.JsonRepository;

public class VaccineModel {
    private BinaryTree<Person> persons;
    private BinaryTree<Vaccine> vaccines;
    private JsonRepository repository;

    public VaccineModel() {
        repository = new JsonRepository();
        loadUsersData();
        loadVaccinesData();
    }

    public void loadUsersData() {
        persons = new BinaryTree<>();

        List<Person> loadedPersons = repository.loadUsers();

        for (Person p : loadedPersons) {
            p.rebuildTreeFromTempList(); // reconstruir el árbol
            persons.add(p);
        }
    }

    public void saveUsersData() {
        repository.saveUsers(persons);
    }

    public void loadVaccinesData() {
        vaccines = new BinaryTree<>();

        List<Vaccine> loadedVaccines = repository.loadVaccines();

        for (Vaccine v : loadedVaccines) {
            vaccines.add(v);
        }
    }

    public void saveVaccinesData() {
        repository.saveVaccines(vaccines);
    }

    public boolean createUser(Person person) {
        if (userExist(person.getDocumentNumber()))
            return false;
        persons.add(person);

        repository.saveUsers(persons);
        return true;
    }

    public boolean createVaccine(Vaccine vaccine) {
        if (vaccineExist(vaccine.getVaccineName()))
            return false;
        vaccines.add(vaccine);
        saveVaccinesData();
        return true;
    }

    public OperationResult vaccinate(String documentNumber, String vaccineName, Date applicationDate) {
        Person person = getUserByDocument(documentNumber);
        Vaccine vaccine = getVaccineByName(vaccineName);
        OperationResult operationResult = validations(documentNumber, vaccineName);
        if (operationResult != null)
            return operationResult;

        int appliedDoses = getAppledDoses(documentNumber, vaccineName);
        int totalDoses = vaccine.getDose();

        if (appliedDoses >= totalDoses)
            return new OperationResult(false,
                    "Esta persona ya recibió todas las dosis (" + totalDoses + ") de la vacuna " + vaccineName);

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
            saveUsersData();
            return new OperationResult(true, "Vacuna agregada al registro existente");
        } else {
            Vaccinate newRecord = new Vaccinate(getVaccineByName(vaccineName), applicationDate, currentDoseNumber,
                    person.getDocumentNumber());
            persons.get(person).getMyVacinations().add(newRecord);
            saveUsersData();
            return new OperationResult(true, "Nuevo registro de vacunación creado");
        }
    }

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

    // private Vaccine createVaccineWithDoseNumber(Vaccinate vaccinate, int
    // doseNumber){
    // Vaccine vaccineWithDose = originalVaccine;
    // vaccineWithDose.setTotalDose(doseNumber);

    // vaccineWithDose.setVaccineName(originalVaccine.getVaccineName());
    // vaccineWithDose.setManufacterName(originalVaccine.getManufacterName());
    // vaccineWithDose.setDiseaseName(originalVaccine.getDiseaseName());
    // vaccineWithDose.setExpirationDate(originalVaccine.getExpirationDate());
    // vaccineWithDose.setVaccineType(originalVaccine.getVaccineType());
    // vaccineWithDose.setBatchNumber(originalVaccine.getBatchNumber());
    // vaccineWithDose.setDose(doseNumber);
    // return vaccineWithDose;
    // }

    public List<String> getVaccineNames() {
        loadVaccinesData();
        return vaccines.inOrder()
                .stream()
                .map(Vaccine::getVaccineName)
                .toList();
    }

    public Vaccine getVaccineByName(String vaccineName) {
        Vaccine tempVaccine = new Vaccine(vaccineName);
        return vaccines.get(tempVaccine);
    }

    public List<Vaccinate> getVaccinesForUsers(String documentNumber) {
        Person person = getUserByDocument(documentNumber);
        return person.getMyVacinations().inOrder();
    }

    public Person getUserByDocument(String documentNumber) {
        Person tempPerson = new Person(documentNumber);
        return persons.get(tempPerson);
    }

    public void updateVaccineFromTable(int rowIndex, Vaccine updatedVaccine, Date applicationDate,
            String documentNumber) {
        Person person = getUserByDocument(documentNumber);
        if (person == null || person.getMyVacinations() == null) {
            return;
        }

        // Convertir el árbol a lista para iterar
        List<Vaccinate> vaccinateList = person.getMyVacinations().inOrder();
        if (rowIndex < 0 || rowIndex >= vaccinateList.size()) {
            return;
        }

        // Buscar el registro por fecha
        for (Vaccinate record : vaccinateList) {
            if (isSameDay(record.getApplicationDate(), applicationDate)) {
                // Actualiza la vacuna en la posición indicada
                record.setVaccine(updatedVaccine);
                break;
            }
        }

        // Reconstruir el árbol con los cambios
        BinaryTree<Vaccinate> updatedTree = new BinaryTree<>();
        for (Vaccinate v : vaccinateList) {
            updatedTree.add(v);
        }
        person.setMyVacinations(updatedTree);

        // Guardar cambios
        saveUsersData();
    }

    // public void updateVaccineFromTable(int rowIndex, Vaccine updateVaccine, Date
    // applicationDate, String documentNumber) {
    // BinaryTree<Vaccinate> vaccinate =
    // getUserByDocument(documentNumber).getMyVacinations();
    // for (Vaccinate record : history) {
    // if (record.getPerson().getDocumentNumber() == documentNumber &&
    // isSameDay(record.getApplicationDate(), applicationDate) ) {
    // List<Vaccine> vaccines = record.getVaccines();
    // if (rowIndex < vaccines.size()) {
    // vaccines.set(rowIndex, updateVaccine);
    // saveJsonHistory(history);
    // return;
    // }
    // }
    // }
    // }

    private boolean userExist(String documentNumber) {
        Person tempPerson = new Person();
        tempPerson.setDocumentNumber(documentNumber);
        return persons.contains(tempPerson);
    }

    private boolean vaccineExist(String vaccineName) {
        Vaccine tempVaccine = new Vaccine();
        tempVaccine.setVaccineName(vaccineName);
        return vaccines.contains(tempVaccine);
    }

    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    private int getAppledDoses(String documentNumber, String vaccineName) {
        Person person = getUserByDocument(documentNumber);
        if (person == null || person.getMyVacinations() == null) {
            return 0;
        }

        int appliedDoses = 0;
        List<Vaccinate> vaccinateList = person.getMyVacinations().inOrder();

        for (Vaccinate record : vaccinateList) {
            if (record.getVaccine() != null &&
                    record.getVaccine().getVaccineName().equalsIgnoreCase(vaccineName)) {
                appliedDoses++;
            }
        }
        return appliedDoses;
    }
}
