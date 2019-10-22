package exceptions;

import view.Alert_Information;

public class ReadRentalRecordException extends Exception {
    public void popWindow(String head,String content){
        new Alert_Information(head,content);
    }
}
