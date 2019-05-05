package com.ksptooi.Performance;

import com.ksptooi.FL.PDEContainer.PDECManager;

public class PerformanceMonitorManager {

	
	private static long pluginFileProcessCount=0;
	
	private static long pluginActiveThreadCount=0;
	
	
	public static void addPFPC(){
		pluginFileProcessCount++;
	}
	
	
	public static long getPFPC(){
		
		return pluginFileProcessCount;
		
	}
	
	
	public static int getPDECCount(){
		
		return PDECManager.getPDEC().size();	
		
	}
	
	
	public static void addPATC(){
		pluginActiveThreadCount++;
	}
	
	public static void removePATC(){
		pluginActiveThreadCount--;
	}
	
	public static long getPATC(){
		return pluginActiveThreadCount;
	}
	
	
	
	
	
}
