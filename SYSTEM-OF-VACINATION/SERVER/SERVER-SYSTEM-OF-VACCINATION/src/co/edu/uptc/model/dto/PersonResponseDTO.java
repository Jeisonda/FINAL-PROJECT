package co.edu.uptc.model.dto;

import java.util.Date;
import java.util.List;

public class PersonResponseDTO {
private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private String documentType;
    private String email;
    private String documentNumber;
    private String phoneNumber;
    private Date bornDate;
    private List<VaccinationDTO> vaccinations;

    public PersonResponseDTO(String firstName, String middleName, String lastName, String secondLastName,
            String documentType, String email, String documentNumber, String phoneNumber, Date bornDate,
            List<VaccinationDTO> vaccinations) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.documentType = documentType;
        this.email = email;
        this.documentNumber = documentNumber;
        this.phoneNumber = phoneNumber;
        this.bornDate = bornDate;
        this.vaccinations = vaccinations;
    }

    public PersonResponseDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getEmail() {
        return email;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public List<VaccinationDTO> getVaccinations() {
        return vaccinations;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public void setVaccinations(List<VaccinationDTO> vaccinations) {
        this.vaccinations = vaccinations;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

