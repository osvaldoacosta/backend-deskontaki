package com.elcoma.api.services.exceptions;

public class DataIntegretyException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DataIntegretyException(String msg) {
		super(msg);
	}

	public DataIntegretyException(String msg, Throwable cause) {
		super(msg,cause);
	}

}
