package co.edu.uptc.net.dto;

public class Request {
    private String action;
    private String data;
    
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
