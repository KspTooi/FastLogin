package com.ksptooi.FL.security;

import org.bukkit.entity.Player;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Util.Logger;
import com.ksptooi.gdc.v5.Manager.IOController_V5;

public class RegsterIPCount {

	IOController_V5 v5=null; 
	Logger logManager=null;
	
	public RegsterIPCount(){
		v5=new IOController_V5();
		logManager = new Logger();
	}
	
	public boolean playerIp_isMaxReg(Player pl){
		
		String PlayerIP=pl.getAddress().getHostName();
		v5.setTarget(ConfigManager.fastLoginIPCountFile);
		
		logManager.debugMessage("玩家:"+pl.getName()+"IP地址:"+PlayerIP);
		
		//如果MaxRegisterIP是0则关闭这个功能
		if(ConfigManager.getConfig().getMaxRegisterIP()==0){			
			logManager.debugMessage("ip限制已关闭!");		
			return false;
		}
		
		logManager.debugMessage("玩家:"+pl.getName()+"的IP计数:"+v5.getRepeatLineCount(PlayerIP));
		
		//判断IP有没有超出限额
		if(v5.getRepeatLineCount(PlayerIP) > ConfigManager.getConfig().getMaxRegisterIP()){
			logManager.debugMessage("玩家:"+pl.getName()+"超出了ip限额");
			return true;
		}
		
		//没有超出则返回一个false
		logManager.debugMessage("玩家:"+pl.getName()+"没有超出ip限额");
		
		return false;
		
	}

	
	public void addIP(Player pl){
		
		//如果MaxRegisterIP是0则关闭这个功能
		if(ConfigManager.getConfig().getMaxRegisterIP()==0){			
			logManager.debugMessage("ip限制已关闭!");		
			return;
		}
		
		logManager.debugMessage("添加IP计数:"+pl.getName()+","+pl.getAddress().getHostName());	
		
		String PlayerIP=pl.getAddress().getHostName();
		
		v5.setTarget(ConfigManager.fastLoginIPCountFile);
		
		v5.addLine(PlayerIP);
	}
	
	
	
}
