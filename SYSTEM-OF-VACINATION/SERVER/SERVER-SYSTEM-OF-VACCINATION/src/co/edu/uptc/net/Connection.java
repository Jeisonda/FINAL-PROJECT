package co.edu.uptc.net;

import java.io.*;
import java.net.Socket;
import com.google.gson.Gson;
import co.edu.uptc.net.dto.Request;
import co.edu.uptc.net.dto.Response;

public class Connection {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private Gson gson;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.gson = new Gson();
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
    }

    public void sendRequest(Request request) throws IOException {
        String json = gson.toJson(request);
        output.writeUTF(json);
        output.flush();
    }

    public void sendResponse(Response response) throws IOException {
        String json = gson.toJson(response);
        output.writeUTF(json);
        output.flush();
    }

    public Request readRequest() throws IOException {
        String json = input.readUTF();
        return gson.fromJson(json, Request.class);
    }

    public Response readResponse() throws IOException {
        String json = input.readUTF();
        return gson.fromJson(json, Response.class);
    }
}
