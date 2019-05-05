package com.ksptooi.FL.playerThread;


import org.bukkit.entity.Player;
import com.ksptooi.FL.Entity.PlayerDataEntity;
import com.ksptooi.FL.MultiVersion.AdvMultiVersionAsyncProcess;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.Performance.PerformanceMonitorManager;
import com.ksptooi.playerData_BLL.PlayerDataBLL_Interface;
import com.ksptooi.playerData_BLL.PlayerDataBLLimpl;


public class PlayerLoginMessageSendThread implements Runnable{
	
	
	Player pl=null;
	int LoginTime=0;
	int sendtime=0;
	AdvMultiVersionAsyncProcess AMVAP = null;

	
	PlayerDataBLL_Interface playerDataBLL=null;
	
	public PlayerLoginMessageSendThread(Player pl) {
		this.pl=pl;		
		playerDataBLL=new PlayerDataBLLimpl();
		AMVAP = new AdvMultiVersionAsyncProcess();

	}
	
	
	public void run(){

		//添加线程性能计数
		PerformanceMonitorManager.addPATC();
		
		PlayerDataEntity PDE=playerDataBLL.getPlayerData(pl);
		
		
		//已登录则关闭线程
		if(PDE.isLogin()){
			FUtil.NoDamagePlayer.remove(pl.getName());
			PerformanceMonitorManager.removePATC();
			return;
		}
		
		
		
		if(PDE.isRegister()){
			
			pl.sendMessage(FUtil.language.getNotlogin());
			
		}else{
			
			pl.sendMessage(FUtil.language.getNotRegister());
			
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
			if(LoginTime > FUtil.config.getLoginTimeOut()){
				
				AMVAP.AsyncKickPlayer(pl,FUtil.language.getLoginTimeOutKick());
							
				break;
			}
			
			
			PDE=playerDataBLL.getPlayerData(pl);
			//已登录则关闭线程
			if(PDE.isLogin()){
				FUtil.NoDamagePlayer.remove(pl.getName());
				break;
			}
			
					
			if(!(sendtime >= FUtil.config.getMessageInterval())){
				continue;
			}
					
			sendtime=0;	
				
			
			//发送登录/注册消息
			if(PDE.isRegister()){
				
				pl.sendMessage(FUtil.language.getNotlogin());
				continue;
				
			}else{
				
				pl.sendMessage(FUtil.language.getNotRegister());
				continue;
			}
			
			
			
		}
		
		//清除线程性能计数
		PerformanceMonitorManager.removePATC();
		
		
	}
	

}
