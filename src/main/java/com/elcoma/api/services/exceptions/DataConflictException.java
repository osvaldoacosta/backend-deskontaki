package com.elcoma.api.services.exceptions;

public class DataConflictException extends RuntimeException{

    public DataConflictException(String msg){
        super(msg);
    }
    public DataConflictException(String msg, Throwable cause){super(msg,cause);}
}
