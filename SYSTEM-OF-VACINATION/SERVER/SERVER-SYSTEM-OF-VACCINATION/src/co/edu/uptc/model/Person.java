package co.edu.uptc.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.uptc.structures.BinaryTree;

public class Person implements Comparable<Person> {
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private String documentType;
    private String email;
    private String documentNumber;
    private String phoneNumber;
    private Date bornDate;
    // Este campo NO se deserializa directamente
    @JsonIgnore
    private BinaryTree<Vaccinate> myVacinations = new BinaryTree<>();

    // Este sí se carga desde JSON como una lista normal
    @JsonProperty("myVaccinations")
    private List<Vaccinate> tempVaccinateList;
    
    public Person() {
    }

    public Person(String firstName, String middleName, String lastName, String secondLastName, String documentType,
    String email, String documentNumber, String phoneNumber, Date bornDate) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.documentType = documentType;
        this.email = email;
        this.documentNumber = documentNumber;
        this.phoneNumber = phoneNumber;
        this.bornDate = bornDate;
    }

    public void rebuildTreeFromTempList() {
        if (tempVaccinateList != null) {
            myVacinations = new BinaryTree<>();
            for (Vaccinate v : tempVaccinateList) {
                myVacinations.add(v);
            }
        }
    }
    
    public Person(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public BinaryTree<Vaccinate> getMyVacinations() {
        return myVacinations;
    }

    public void setMyVacinations(BinaryTree<Vaccinate> myVacinations) {
        this.myVacinations = myVacinations;
    }

    public List<Vaccinate> getTempVaccinateList() {
        return tempVaccinateList;
    }

    public void setTempVaccinateList(List<Vaccinate> tempVaccinateList) {
        this.tempVaccinateList = tempVaccinateList;
    }
    
    @Override
    public int compareTo(Person comparePerson){
        return documentNumber.compareTo(comparePerson.getDocumentNumber());
    }
}
