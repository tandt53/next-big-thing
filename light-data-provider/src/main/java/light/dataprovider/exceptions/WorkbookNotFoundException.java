package light.dataprovider.exceptions;

public class WorkbookNotFoundException extends Exception {

    public WorkbookNotFoundException(String message){
        super(message);
    }

    public WorkbookNotFoundException(String message, Throwable t){
        super(message, t);
    }
}
