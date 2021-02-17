package tandt.dataprovider.exceptions;

public class WorksheetNotFoundException extends Exception {

    public WorksheetNotFoundException(String message){
        super(message);
    }

    public WorksheetNotFoundException(String message, Throwable t){
        super(message, t);
    }
}
