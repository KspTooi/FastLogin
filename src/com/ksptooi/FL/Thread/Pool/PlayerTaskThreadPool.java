package com.ksptooi.FL.Thread.Pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlayerTaskThreadPool {

	
	
	ExecutorService es = null;
	
	public PlayerTaskThreadPool() {
		
		es = Executors.newFixedThreadPool(32);
		
	}
	
	
	public void runTask(Runnable runable) {
	
		es.execute(runable);
				
	}
	
	
	
}
