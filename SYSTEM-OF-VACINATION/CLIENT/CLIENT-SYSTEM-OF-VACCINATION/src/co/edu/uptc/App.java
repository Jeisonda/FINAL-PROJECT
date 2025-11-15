package co.edu.uptc;
import co.edu.uptc.control.ClientController;
import co.edu.uptc.views.MainFrame;

public class App {
    public static void main(String[] args) throws Exception {
        ClientController presenter = new ClientController();
        MainFrame view = new MainFrame(presenter);
        view.setVisible(true);
    }
}
