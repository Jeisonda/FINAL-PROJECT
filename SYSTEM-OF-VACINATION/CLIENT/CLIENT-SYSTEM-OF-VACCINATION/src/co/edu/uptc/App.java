package co.edu.uptc;
import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.presenter.Presenter;
import co.edu.uptc.views.MainFrame;

public class App {
    public static void main(String[] args) throws Exception {
        Presenter presenter2 = new Presenter();
        ViewInterface view = new MainFrame(presenter2);
        Presenter presenter = new Presenter(view);
        presenter.run();
    }
}
