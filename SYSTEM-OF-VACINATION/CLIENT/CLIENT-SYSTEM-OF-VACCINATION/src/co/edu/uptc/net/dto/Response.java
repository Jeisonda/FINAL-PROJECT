package co.edu.uptc.net.dto;

public class Response<T> {
    private boolean succes;
    private String message;
    private T data;

    public Response() {}

    public Response(boolean succes, String message, T data) {
        this.succes = succes;
        this.message = message;
        this.data = data;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
}
