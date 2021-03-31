package com.elcoma.api.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> erros = new ArrayList<>();
	public ValidationError(Integer statusHttp, String msg, Long timeStamp) {
		super(statusHttp, msg, timeStamp);
	}
	public List<FieldMessage> getErros() {
		return erros;
	}
	public void addError(String fieldName, String messagem) {
		erros.add(new FieldMessage(fieldName, messagem));
	}
	
	

}
