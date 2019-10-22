package exceptions;

import view.Alert_Information;

public class WriteFileException extends Exception {
    public void popWindow(String head,String content){
        new Alert_Information(head,content);
    }
}
