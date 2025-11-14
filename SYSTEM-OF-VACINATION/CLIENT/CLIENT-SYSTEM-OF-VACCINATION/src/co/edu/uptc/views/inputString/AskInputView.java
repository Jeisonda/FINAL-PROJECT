package co.edu.uptc.views.inputString;

import java.awt.Panel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AskInputView {
    public AskInputView() {
    }

    public String askInput(String message) {
        return JOptionPane.showInputDialog(this, message);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(new JPanel(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showConfirmMessage(String message) {
        JOptionPane.showMessageDialog(new Panel(), message, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}
