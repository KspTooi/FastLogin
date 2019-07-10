package com.ksptooi.FL.Event.FastEvent;

import java.util.ArrayList;

import com.ksptooi.FL.Event.EventHandler.LittleEvent;
import com.ksptooi.FL.Event.EventHandler.LittlePlayerLoginEvent;
import com.ksptooi.FL.Event.EventHandler.LittlePlayerLoginSuccessEvent;
import com.ksptooi.FL.Event.EventHandler.LittlePlayerRegisterEvent;
import com.ksptooi.FL.Event.EventHandler.LittlePlayerRegisterSuccessEvent;
import com.ksptooi.FL.Thread.Pool.PlayerTaskThreadPool;

public class EventManager {

	
	private PlayerTaskThreadPool taskThreadPool = null;
	
	private ArrayList<LittleEvent> listLittleEvent = null;
	
	public EventManager() {
		taskThreadPool = new PlayerTaskThreadPool();
		listLittleEvent = new ArrayList<LittleEvent>();
	}
	
	
	//开始Fast事件
	public void runFastEvent(FastEvent event) {
			
		taskThreadPool.runTask(event);			
		
	}
	
	
	//执行Little事件(玩家登陆)
	public void startPlayerLoginEvent(LittlePlayerLoginEvent event) {
		
		taskThreadPool.runTask(new Runnable(){
			
			public void run() {
				
				for(LittleEvent le:listLittleEvent){
					le.onPlayerLogin(event);
				}
				
			}
			
		});		

		
	}
	
	//执行Little事件(玩家登陆完成)
	public void startPlayerLoginSuccessEvent(LittlePlayerLoginSuccessEvent event) {
		
		
		taskThreadPool.runTask(new Runnable(){
			
			public void run() {
				
				for(LittleEvent le:listLittleEvent){
					le.onPlayerLoginSuccess(event);
				}
				
			}	
			
		});	
		
	
		
	}
	
	//执行Little事件(玩家注册)
	public void startPlayerRegisterEvent(LittlePlayerRegisterEvent event) {
		
		
		taskThreadPool.runTask(new Runnable(){
			
			public void run() {
				
				for(LittleEvent le:listLittleEvent){
					le.onPlayerRegister(event);
				}
				
			}	
			
		});	
		

		
	}
	
	//执行Little事件(玩家登陆)
	public void startPlayerRegisterSuccessEvent(LittlePlayerRegisterSuccessEvent event) {
		
		
		taskThreadPool.runTask(new Runnable(){
			
			public void run() {
				
				for(LittleEvent le:listLittleEvent){
					le.onPlayerRegisterSuccess(event);
				}
				
			}	
			
		});	
		
		
	}
	
	
	
	//注册事件
	public void regLittleEvent(LittleEvent event) {
		listLittleEvent.add(event);	
	}
	
	
	
	
	
	
}
