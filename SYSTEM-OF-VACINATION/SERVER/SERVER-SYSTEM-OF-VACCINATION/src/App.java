import co.edu.uptc.model.VaccineModel;
import co.edu.uptc.presenter.Control;

public class App {
    public static void main(String[] args) throws Exception {
        VaccineModel vaccineModel = new VaccineModel();
        Control presenter = new Control(vaccineModel);
    }
}
