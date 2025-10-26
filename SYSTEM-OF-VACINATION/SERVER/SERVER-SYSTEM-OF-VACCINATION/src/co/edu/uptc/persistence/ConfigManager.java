package co.edu.uptc.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigManager {
    
    public static Properties properties;
    private String data;
    
    public ConfigManager(String data){
        this.data = data;
        properties = new Properties();
    }

    public void loadProperties(){
        try {
            properties.load(new InputStreamReader(new FileInputStream(data), "UTF-8"));
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public String getValue(String key){
        if (properties.isEmpty()) {
            loadProperties();
        }
        if (properties.getProperty(key) == null) {
            System.out.println("Ruta no encontrada");
        }

        return properties.getProperty(key);
    }
}
