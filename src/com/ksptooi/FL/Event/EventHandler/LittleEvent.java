package com.ksptooi.FL.Event.EventHandler;

public interface LittleEvent extends Runnable{

	
	
	public void onPlayerLogin(LittlePlayerLoginEvent event);
	
	
	public void onPlayerLoginSuccess(LittlePlayerLoginSuccessEvent event);
	
	
	public void onPlayerRegister(LittlePlayerRegisterEvent event);
	
	
	public void onPlayerRegisterSuccess(LittlePlayerRegisterSuccessEvent event);
	
	
}
