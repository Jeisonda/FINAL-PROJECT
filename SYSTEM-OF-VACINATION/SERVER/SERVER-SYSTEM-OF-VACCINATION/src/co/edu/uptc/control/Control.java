package co.edu.uptc.control;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import co.edu.uptc.model.VaccineModel;

public class Control {
    private int port;
    private ServerSocket server;
    private VaccineModel model;

    public Control(VaccineModel model) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el puerto para el servidor: ");
        port = scanner.nextInt();
        scanner.close();
        try {
            this.server = new ServerSocket(port);
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
