package com.ksptooi.FL.Thread.Player;


import org.bukkit.entity.Player;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;
import com.ksptooi.FL.Data.PlayerData.PlayerData_Interface;
import com.ksptooi.FL.General.Performance.PerformanceMonitorManager;
import com.ksptooi.FL.Player.Async.PlayerAsyncProcess;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Util.FUtil;


public class PlayerLoginMessageSendThread implements Runnable{
	
	
	Player pl=null;
	int LoginTime=0;
	int sendtime=0;
	PlayerAsyncProcess AMVAP = null;

	
	PlayerData_Interface playerDataBLL=null;
	
	public PlayerLoginMessageSendThread(Player pl) {
		this.pl=pl;		
		playerDataBLL=new PlayerDataManager();
		AMVAP = new PlayerAsyncProcess();

	}
	
	
	public void run(){

		//添加线程性能计数
		PerformanceMonitorManager.addPATC();
		
		PlayerEntity PDE=playerDataBLL.getPlayerData(pl);
		
		
		//已登录则关闭线程
		if(PDE.isLogin()){
			FUtil.NoDamagePlayer.remove(pl.getName());
			PerformanceMonitorManager.removePATC();
			return;
		}
		
		
		
		if(PDE.isRegister()){
			
			pl.sendMessage(ConfigManager.getLanguage().getNotlogin());
			
		}else{
			
			pl.sendMessage(ConfigManager.getLanguage().getNotRegister());
			
		}
		
					 
		while(true){
			
			//刷新玩家状态
			PDE=playerDataBLL.getPlayerData(pl);
			
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
				
				AMVAP.AsyncKickPlayer(pl,ConfigManager.getLanguage().getLoginTimeOutKick());
							
				break;
			}
			
			
			PDE=playerDataBLL.getPlayerData(pl);
			//已登录则关闭线程
			if(PDE.isLogin()){
				FUtil.NoDamagePlayer.remove(pl.getName());
				break;
			}
			
					
			if(!(sendtime >= ConfigManager.getConfig().getMessageInterval())){
				continue;
			}
					
			sendtime=0;	
				
			
			//发送登录/注册消息
			if(PDE.isRegister()){
				
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
