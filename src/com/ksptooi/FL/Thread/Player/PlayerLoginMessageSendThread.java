package com.ksptooi.FL.Thread.Player;


import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Data.PlayerData.PlayerData_Interface;
import com.ksptooi.FL.General.Performance.PerformanceMonitorManager;
import com.ksptooi.FL.PAsync.Task.AsyncTask;
import com.ksptooi.FL.Util.FUtil;


public class PlayerLoginMessageSendThread implements Runnable{
	
	
	FastPlayer pl=null;
	int LoginTime=0;
	int sendtime=0;
	AsyncTask AMVAP = null;

	
	PlayerData_Interface playerDataBLL=null;
	
	public PlayerLoginMessageSendThread(FastPlayer pl) {
		this.pl=pl;		
		playerDataBLL=new PlayerDataManager();
		AMVAP = new AsyncTask();

	}
	
	
	public void run(){

		//添加线程性能计数
		PerformanceMonitorManager.addPATC();
		
		
		//已登录则关闭线程
		if(pl.isLogin()){
			FUtil.NoDamagePlayer.remove(pl.getName());
			PerformanceMonitorManager.removePATC();
			return;
		}
		
		
		
		if(pl.isRegister()){
			
			pl.sendMessage(ConfigManager.getLanguage().getNotlogin());
			
		}else{
			
			pl.sendMessage(ConfigManager.getLanguage().getNotRegister());
			
		}
		
					 
		while(true){
			
			//刷新玩家状态
			pl.reload();
			
			try {	
				Thread.sleep(1000);	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			sendtime++;
			LoginTime++;
			
			//不在线则关闭线程
			if(!pl.isOnline()){
				break;
			}
			
			
			//登陆超时则关闭线程 && 踢出玩家
			if(LoginTime > ConfigManager.getConfig().getLoginTimeOut()){
				
				pl.kickPlayer(ConfigManager.getLanguage().getLoginTimeOutKick());			
							
				break;
			}
			
			pl.reload();
			//已登录则关闭线程
			if(pl.isLogin()){
				FUtil.NoDamagePlayer.remove(pl.getName());
				break;
			}
			
			
			if(!(sendtime >= ConfigManager.getConfig().getMessageInterval())){
				continue;
			}
					
			sendtime=0;	
				
			
			//发送登录/注册消息
			if(pl.isRegister()){
				
				pl.sendMessage(ConfigManager.getLanguage().getNotlogin());
				continue;
				
			}else{
				
				pl.sendMessage(ConfigManager.getLanguage().getNotRegister());
				continue;
			}
			
			
			
		}
		
		//清除线程性能计数
		PerformanceMonitorManager.removePATC();
		
		
	}
	

}
