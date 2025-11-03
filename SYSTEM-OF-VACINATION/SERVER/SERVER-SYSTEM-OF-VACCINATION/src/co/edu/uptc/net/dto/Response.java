package co.edu.uptc.net.dto;


public class Response {
    private String status; // "OK" o "ERROR"
    private String message;
    private String data; // JSON (si devuelve algo)

    public Response(String status, String message, String data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getData() {
        return data;
    }
}