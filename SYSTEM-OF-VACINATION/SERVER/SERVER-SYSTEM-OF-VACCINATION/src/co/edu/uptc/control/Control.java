package co.edu.uptc.control;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import co.edu.uptc.model.VaccineModel;

public class Control {
    private final static int PORT = 12890;
    private ServerSocket server;
    private VaccineModel model;

    public Control(VaccineModel model) {
        try {
            this.server = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.model = model;
    }

    public void listen() {
        while (true) {
            Socket client;
            try {
                client = server.accept();
                new ServerController(client, model).start();
            } catch(IOException e) {
            }
        }
    }
}
