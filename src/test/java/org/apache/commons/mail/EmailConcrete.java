package org.apache.commons.mail;

import java.util.Map;
import java.util.Properties;

import javax.mail.Session;

public class EmailConcrete extends Email{
	
	public Email setMsg(String msg) throws EmailException{
		return null;
	}
	
	public Map<String, String> getHeaders(){
		return this.headers;
	}
	public String getContentType() {
		return this.contentType;
	}
}
