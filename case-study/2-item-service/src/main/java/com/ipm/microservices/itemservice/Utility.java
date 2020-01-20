package com.ipm.microservices.itemservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utility {
	
	public static String objToStr(Object obj) {
		String str = "Error Occured in Utility.objToStr";
		try {
			str = new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

}
