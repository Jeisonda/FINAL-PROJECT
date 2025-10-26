package co.edu.uptc.presenter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import co.edu.uptc.model.VaccineModel;

public class Control {
    private final int PORT = 12728;
    private Socket socket;
    private ServerSocket serverSocket;

    public Control(VaccineModel model) throws IOException {
        serverSocket = new ServerSocket(PORT);
        while (true) {
            socket = serverSocket.accept();
            new Presenter(socket, model).start();
        }
    }
}
