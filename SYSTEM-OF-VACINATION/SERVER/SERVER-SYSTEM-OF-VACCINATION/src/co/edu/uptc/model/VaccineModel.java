package co.edu.uptc.model;

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
            p.rebuildTreeFromTempList();
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
        OperationResult validation = validations(documentNumber, vaccineName);
        if (validation != null) return validation;
        Person person = getUserByDocument(documentNumber);
        Vaccine vaccine = getVaccineByName(vaccineName);
        return vaccinateActions(documentNumber, vaccineName, vaccine, person, applicationDate);
    }

    private OperationResult vaccinateActions(String documentNumber, String vaccineName, Vaccine vaccine, Person person, Date applicationDate) {
        OperationResult entityValidation = validateEntities(person, vaccine);
        if (entityValidation != null) return entityValidation;
        OperationResult doseCheck = checkDoseLimit(documentNumber, vaccineName, vaccine);
        if (doseCheck != null) return doseCheck;
        int currentDoseNumber = getAppledDoses(documentNumber, vaccineName) + 1;
        return vaccinateOperationResultReturn(documentNumber, vaccineName, person, applicationDate, vaccine, currentDoseNumber);
    }

    private OperationResult vaccinateOperationResultReturn(String documentNumber, String vaccineName, Person person, Date applicationDate, Vaccine vaccine, int currentDoseNumber) {
        BinaryTree<Vaccinate> vaccinations = getOrCreateVaccinations(person);
        Vaccinate vaccinateRecord = findExistingVaccinateRecord(vaccinations, vaccine);
        Vaccinate existingRecordToday = findSameDayRecord(vaccinateRecord, applicationDate);
        if (existingRecordToday != null) {
            return updateExistingRecord(person, vaccinateRecord, existingRecordToday, currentDoseNumber);
        } else {
            return createNewVaccinationRecord(person, vaccineName, applicationDate, currentDoseNumber);
        }
    }

    private OperationResult validateEntities(Person person, Vaccine vaccine) {
        if (person == null) return new OperationResult(false, "Usuario no encontrado");
        if (vaccine == null) return new OperationResult(false, "Vacuna no encontrada");
        return null;
    }

    private OperationResult checkDoseLimit(String documentNumber, String vaccineName, Vaccine vaccine) {
        int appliedDoses = getAppledDoses(documentNumber, vaccineName);
        int totalDoses = vaccine.getDose();

        if (appliedDoses >= totalDoses) {
            return new OperationResult(false, "Esta persona ya recibió todas las dosis (" + totalDoses + ") de la vacuna " + vaccineName);
        }
        return null;
    }

    private BinaryTree<Vaccinate> getOrCreateVaccinations(Person person) {
        BinaryTree<Vaccinate> vaccinates = person.getMyVacinations();
        if (vaccinates == null) {
            vaccinates = new BinaryTree<>();
            person.setMyVacinations(vaccinates);
        }
        return vaccinates;
    }

    private Vaccinate findExistingVaccinateRecord(BinaryTree<Vaccinate> vaccinates, Vaccine vaccine) {
        Vaccinate search = new Vaccinate();
        search.setVaccine(vaccine);
        try {
            return vaccinates.get(search);
        } catch (Exception e) {
            return null;
        }
    }

    private Vaccinate findSameDayRecord(Vaccinate vaccinateRecord, Date applicationDate) {
        if (vaccinateRecord != null && applicationDate != null && vaccinateRecord.getApplicationDate() != null) {
            if (isSameDay(vaccinateRecord.getApplicationDate(), applicationDate)) {
                return vaccinateRecord; 
            }
        }
        return null;
    }

    private OperationResult updateExistingRecord(Person person, Vaccinate vaccinateRecord, Vaccinate existingRecord, int currentDoseNumber) {
        existingRecord.setDose(currentDoseNumber);

        persons.get(person).getMyVacinations().remove(vaccinateRecord);
        persons.get(person).getMyVacinations().add(existingRecord);
        
        saveUsersData();
        return new OperationResult(true, "Vacuna agregada al registro existente");
    }

    private OperationResult createNewVaccinationRecord(Person person, String vaccineName, Date applicationDate, int currentDoseNumber) {
        Vaccinate newRecord = new Vaccinate(getVaccineByName(vaccineName), applicationDate, currentDoseNumber,
                person.getDocumentNumber());
        
        persons.get(person).getMyVacinations().add(newRecord);
        saveUsersData();
        return new OperationResult(true, "Nuevo registro de vacunación creado");
    }

    private OperationResult validations(String documentNumber, String vaccineName) {
        Person person = getUserByDocument(documentNumber);
        if (person == null) return new OperationResult(false, "Usuario no encontrado");

        Vaccine vaccine = getVaccineByName(vaccineName);
        if (vaccine == null) return new OperationResult(false, "Vacuna no encontrada");
        return null;
    }

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
        if (person == null) return List.of();
        BinaryTree<Vaccinate> tree = person.getMyVacinations();
        if (tree == null) return List.of();
        return tree.inOrder();
    }

    public Person getUserByDocument(String documentNumber) {
        Person tempPerson = new Person(documentNumber);
        return persons.get(tempPerson);
    }

    public void updateVaccineFromTable(int rowIndex, Vaccine updatedVaccine, Date applicationDate,
            String documentNumber) {
        Person person = getUserByDocument(documentNumber);
        if (person == null || person.getMyVacinations() == null) return;
        List<Vaccinate> vaccinateList = person.getMyVacinations().inOrder();
        if (rowIndex < 0 || rowIndex >= vaccinateList.size()) return;
        updateVaccinateRecord(vaccinateList, updatedVaccine, applicationDate);
        updatedTree(vaccinateList, person);
    }

    private void updateVaccinateRecord(List<Vaccinate> vaccinateList, Vaccine updatedVaccine, Date applicationDate) {
        for (Vaccinate record : vaccinateList) {
            if (record.getApplicationDate() != null && applicationDate != null && isSameDay(record.getApplicationDate(), applicationDate)) {
                record.setVaccine(updatedVaccine);
                break;
            }
        }
    }

    private void updatedTree(List<Vaccinate> vaccinateList, Person person) {
        BinaryTree<Vaccinate> updatedTree = new BinaryTree<>();
        for (Vaccinate v : vaccinateList) {
            updatedTree.add(v);
        }
        person.setMyVacinations(updatedTree);
        saveUsersData();
    }

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
        if (date1 == null || date2 == null) return false;
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    private int getAppledDoses(String documentNumber, String vaccineName) {
        Person person = getUserByDocument(documentNumber);
        if (person == null || person.getMyVacinations() == null) return 0;
        int appliedDoses = 0;
        List<Vaccinate> vaccinateList = person.getMyVacinations().inOrder();
        for (Vaccinate record : vaccinateList) {
            if (record.getVaccine() != null &&
                    record.getVaccine().getVaccineName().equalsIgnoreCase(vaccineName))
                appliedDoses++;
        }
        return appliedDoses;
    }
}
