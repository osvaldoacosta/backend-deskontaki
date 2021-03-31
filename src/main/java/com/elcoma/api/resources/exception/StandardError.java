package com.elcoma.api.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer statusHttp;
	private String msg;
	private Long timeStamp;
	
	public StandardError(Integer statusHttp, String msg, Long timeStamp) {
		super();
		this.statusHttp = statusHttp;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}

	public Integer getStatusHttp() {
		return statusHttp;
	}

	public void setStatusHttp(Integer statusHttp) {
		this.statusHttp = statusHttp;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
