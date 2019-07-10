package com.ksptooi.FL.Event.FastEvent;

import com.ksptooi.FL.Thread.Pool.PlayerTaskThreadPool;

public class EventManager {

	
	PlayerTaskThreadPool taskThreadPool = null;
	
	
	public EventManager() {
		taskThreadPool = new PlayerTaskThreadPool();
	}
	
	
	//开始事件
	public void runEvent(LittleEvent event) {
		
		
		taskThreadPool.runTask(event);
		
		
	}
	
	
	
	
	
	
	
}
