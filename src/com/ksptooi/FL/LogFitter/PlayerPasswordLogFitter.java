package com.ksptooi.FL.LogFitter;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class PlayerPasswordLogFitter implements Filter{

	
	@Override
	public boolean isLoggable(LogRecord arg0) {
		
		String log=arg0.getMessage();
		
		System.out.println("Log-------------");
		System.out.println("Log-"+arg0.getMessage());
		System.out.println("Log-------------");
		
		if(log.contains("/l")|log.contains("/login")|log.contains("/reg")|log.contains("/register")){
			System.out.println("¹ýÂË:"+log);
			return false;
		}
		
		return true;
	}

	
	
	
	
	

	
	
	
}
