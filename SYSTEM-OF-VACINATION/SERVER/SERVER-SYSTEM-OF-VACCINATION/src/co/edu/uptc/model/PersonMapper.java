package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.model.dto.PersonResponseDTO;
import co.edu.uptc.model.dto.VaccinateDTO;
import co.edu.uptc.model.dto.VaccineDTO;

public class PersonMapper {

    public static PersonResponseDTO toResponseDTO(Person person) {
        List<VaccinateDTO> vaccinationList = new ArrayList<>();

        if (person.getMyVacinations() != null) {
            List<Vaccinate> vaccinateList = person.getMyVacinations().inOrder();

            for (Vaccinate vaccinate : vaccinateList) {
                Vaccine vaccine = vaccinate.getVaccine();

                VaccineDTO vaccineDTO = new VaccineDTO(
                    vaccine.getVaccineName(),
                    vaccine.getManufacterName(),
                    vaccine.getDiseaseName(),
                    vaccine.getVaccineType(),
                    vaccine.getBatchNumber(),
                    vaccine.getExpirationDate(),
                    vaccine.getTotalDose()
                );

                VaccinateDTO vaccinateDTO = new VaccinateDTO(
                    vaccineDTO,
                    vaccinate.getApplicationDate(),
                    vaccinate.getDose()
                );

                vaccinationList.add(vaccinateDTO);
            }
        }

        return new PersonResponseDTO(
            person.getFirstName(),
            person.getMiddleName(),
            person.getLastName(),
            person.getSecondLastName(),
            person.getDocumentType(),
            person.getEmail(),
            person.getDocumentNumber(),
            person.getPhoneNumber(),
            person.getBornDate(),
            vaccinationList
        );
    }
}
