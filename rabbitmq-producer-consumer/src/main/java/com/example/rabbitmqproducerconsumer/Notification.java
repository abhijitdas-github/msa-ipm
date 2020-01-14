package com.example.rabbitmqproducerconsumer;

import java.io.Serializable;

public class Notification implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String notificationType;
    private String msg;
    
	public String getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
    
    
}