package co.edu.uptc;
import co.edu.uptc.presenter.Presenter;
import co.edu.uptc.views.MainFrame;

public class App {
    public static void main(String[] args) throws Exception {
        Presenter presenter = new Presenter();
        MainFrame view = new MainFrame(presenter);
        view.setVisible(true);
    }
}
