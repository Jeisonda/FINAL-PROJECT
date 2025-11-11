package presenter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import co.edu.uptc.net.dto.Request;
import co.edu.uptc.net.dto.Response;

public class ServerConnection {

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private Gson gson;

    public ServerConnection(String host, int port) throws UnknownHostException, IOException {
        socket = new Socket(host, port);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
        gson = new Gson();
    }

    public <T> Response<T> sendResponse(Request request, Class<T> responseType) throws IOException {
        String jsonRequest = gson.toJson(request);
        output.writeUTF(jsonRequest);

        String jsonResponse = input.readUTF();
        @SuppressWarnings("unchecked")
        Response<T> response = gson.fromJson(jsonResponse, Response.class);
        return response;
    }

    public Gson getGson() {
        return gson;
    }

    public void close() throws IOException{
        socket.close();
    }
}