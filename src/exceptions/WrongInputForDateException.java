package exceptions;

import view.Alert_Information;

class WrongInputForDateException extends Exception {
    public void popWindow(String head,String content){
        new Alert_Information(head,content);
    }
}
