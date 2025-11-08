package co.edu.uptc.persistence;

public class ConfigGlobal {
    static ConfigManager configManager = new ConfigManager("src/co/edu/uptc/resources/application.properties");
    
    public static String userData = configManager.getValue("userPath");
    public static String vaccineData = configManager.getValue("vaccinePath");
}
