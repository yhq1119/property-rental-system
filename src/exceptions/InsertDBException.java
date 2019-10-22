package exceptions;

import view.Alert_Information;

public class InsertDBException extends  Exception {

    public void popWindow() {
        new Alert_Information("Error", "Could Not Insert To DataBase.");
}
}
