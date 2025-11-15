package co.edu.uptc.netWork;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import co.edu.uptc.net.dto.Request;
import co.edu.uptc.net.dto.Response;

public class ServerConnection {

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private Gson gson;

    public ServerConnection(String host, int port) throws IOException {
        socket = new Socket(host, port);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
        gson = new Gson();
    }

    public <T> Response<T> sendResponse(Request request, Type responseType) throws IOException {
        String jsonRequest = gson.toJson(request);
        output.writeUTF(jsonRequest);
        String jsonResponse = input.readUTF();
        Response<T> response = gson.fromJson(jsonResponse, responseType);
        return response;
    }

    public <T> Response<T> sendResponse(Request request, Class<T> responseClass) throws IOException {
        Type type = TypeToken.getParameterized(Response.class, responseClass).getType();
        return sendResponse(request, type);
    }

    public Gson getGson() {
        return gson;
    }

    public void close() throws IOException {
        socket.close();
    }
}
