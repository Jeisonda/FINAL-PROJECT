package co.edu.uptc.model;

import java.util.Date;
import co.edu.uptc.structures.BinaryTree;
import co.edu.uptc.model.Vaccinate;

public class Person implements Comparable<Person> {
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private String documentType;
    private String email;
    private long documentNumber;
    private long phoneNumber;
    private Date bornDate;
    private BinaryTree<Vaccinate> myVacinations;
    
    public Person(String firstName, String middleName, String lastName, String secondLastName, String documentType,
    String email, long documentNumber, long phoneNumber, Date bornDate) {
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
    
    public Person() {
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

    public long getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(long documentNumber) {
        this.documentNumber = documentNumber;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    @Override
    public int compareTo(Person comparePerson){
        return firstName.compareTo(comparePerson.getFirstName());
    }
}
