package exceptions;

import view.Alert_Information;

class PicNotSetException extends Exception {
    public void popWindow(String head, String content) {
        new Alert_Information(head, content);
    }
}
