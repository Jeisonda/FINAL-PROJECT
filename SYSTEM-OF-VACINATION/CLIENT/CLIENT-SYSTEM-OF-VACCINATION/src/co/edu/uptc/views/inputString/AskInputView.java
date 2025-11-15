package co.edu.uptc.views.inputString;

import java.awt.Panel;

import javax.swing.JOptionPane;

public class AskInputView {
    public AskInputView() {
    }

    public String askInput(String message) {
        return JOptionPane.showInputDialog(null, message);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showConfirmMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}
