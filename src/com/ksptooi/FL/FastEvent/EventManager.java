package com.ksptooi.FL.FastEvent;

public class EventManager {

	
	EventHandler handler=new EventHandler();
	
	
	//开始玩家登陆事件
	public void startPlayerLoginEvent(PlayerLoginEvent event) {
		
		//执行自带的事件处理
		handler.onPlayerLoginEvent(event);
		
	}
	
	
	
	
	
	
	
}
