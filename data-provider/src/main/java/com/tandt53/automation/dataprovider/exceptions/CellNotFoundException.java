package com.tandt53.automation.dataprovider.exceptions;

public class CellNotFoundException extends Exception {

    public CellNotFoundException(String message){
        super(message);
    }

    public CellNotFoundException(String message, Throwable t){
        super(message, t);
    }
}
