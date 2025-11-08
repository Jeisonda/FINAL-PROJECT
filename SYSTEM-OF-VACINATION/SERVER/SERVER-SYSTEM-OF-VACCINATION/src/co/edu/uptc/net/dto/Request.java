package co.edu.uptc.net.dto;

public class Request {
    private String action; // ejemplo: "CREATE_USER", "GET_VACCINE"
    private String data;   // JSON del objeto asociado
    
    public Request() {
    }

    public Request(String action, String data) {
        this.action = action;
        this.data = data;
    }

    public String getAction() { 
        return action;
    }

    public String getData() {
        return data;
    }
}
