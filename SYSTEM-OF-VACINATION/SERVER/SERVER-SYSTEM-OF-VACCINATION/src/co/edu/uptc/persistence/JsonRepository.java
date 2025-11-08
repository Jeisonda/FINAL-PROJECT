package co.edu.uptc.persistence;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import co.edu.uptc.model.Person;
import co.edu.uptc.model.Vaccine;
import co.edu.uptc.model.Vaccinate;
import co.edu.uptc.structures.BinaryTree;

public class JsonRepository {
    private final ObjectMapper mapper = new ObjectMapper();

    public void saveUsers(BinaryTree<Person> data) {
        try (FileWriter writer = new FileWriter(ConfigGlobal.userData)) {

            // Convertir el BinaryTree de personas a lista
            List<Person> personsList = data.inOrder();

            // Por cada persona, convertir su árbol interno de vacunaciones a lista
            for (Person p : personsList) {
                if (p.getMyVacinations() != null) {
                    p.setTempVaccinateList(p.getMyVacinations().inOrder());
                }
            }

            // Guardar todo como JSON
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, personsList);

            // Limpiar el campo temporal para no dejarlo en memoria
            for (Person p : personsList) {
                p.setTempVaccinateList(null);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }    

    public List<Person> loadUsers() {
        try (FileReader reader = new FileReader(ConfigGlobal.userData)) {
            System.out.println("holas");
            // Leer lista simple de personas
            List<Person> persons = mapper.readValue(reader, new TypeReference<List<Person>>() {});

            // Reconstruir el árbol interno de vacunaciones
            for (Person p : persons) {
                if (p.getTempVaccinateList() != null) {
                    BinaryTree<Vaccinate> vaccinateTree = new BinaryTree<>();
                    for (Vaccinate v : p.getTempVaccinateList()) {
                        vaccinateTree.add(v);
                    }
                    p.setMyVacinations(vaccinateTree);
                    p.setTempVaccinateList(null);
                }
            }

            return persons;

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveVaccines(BinaryTree<Vaccine> data) {
        try (FileWriter writer = new FileWriter(ConfigGlobal.vaccineData)) {
            List<Vaccine> vaccineList = data.inOrder();
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, vaccineList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Vaccine> loadVaccines() {
        try (FileReader reader = new FileReader(ConfigGlobal.vaccineData)) {
            return mapper.readValue(reader, new TypeReference<List<Vaccine>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
