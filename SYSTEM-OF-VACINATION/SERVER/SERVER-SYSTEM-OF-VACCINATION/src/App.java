import co.edu.uptc.control.Control;
import co.edu.uptc.model.VaccineModel;

public class App {
    public static void main(String[] args) throws Exception {
        VaccineModel model = new VaccineModel();
        new Control(model).listen();
    }
}
