package persistence;

public class ConfigGlobal {
    static ConfigManager configManager = new ConfigManager("src/resources/application.properties");
    
    public static String userData = configManager.getValue("userPath");
    public static String vaccineData = configManager.getValue("vaccinePath");
    public static String historyData = configManager.getValue("historyPath");

}
