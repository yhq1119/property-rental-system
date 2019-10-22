package exceptions;

import view.Alert_Information;

class EmptyInputException extends Exception {
    public void popWindow(String head, String content) {
        new Alert_Information(head, content);
    }
}