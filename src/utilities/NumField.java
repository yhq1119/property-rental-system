package utilities;

import javafx.scene.control.TextField;


public class NumField extends TextField {
    @Override
    public void replaceText(int start, int end, String text) {

        if (!text.matches("[a-z]")) {
            super.replaceText(start, end, text);
        }

    }

    @Override
    public void replaceSelection(String text) {
        if (!text.matches("[a-z]")) {
            super.replaceSelection(text);
        }

    }

}
