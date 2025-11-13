package co.edu.uptc.net.dto;

public class Response<T> {
    private boolean success;
    private String message;
    private T data;

    public Response() {}

    public Response(boolean s, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean succes) {
        this.success = succes;
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
