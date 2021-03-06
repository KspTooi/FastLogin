package com.ksptooi.FL.security;

import java.util.ArrayList;

import org.bukkit.GameMode;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Data.PlayerData.PlayerData_Interface;
import com.ksptooi.FL.PAsync.Task.AsyncTask;

public class LoginSecurity {

	private ArrayList<String> opList=null;
	private ArrayList<String> creativeList=null;
	
	PlayerData_Interface PDBI=null;
	AsyncTask asyncProcess = null;
	
	public LoginSecurity(){
		opList = new ArrayList<String>();
		creativeList = new ArrayList<String>();		
		PDBI=new PlayerDataManager();
		asyncProcess = new AsyncTask();
	}
	
	
	
	public void joinServer_OpSecurityProcess(FastPlayer pl){
		
		pl.reload();
		
		//判断有没有开启OP安全
		if(!ConfigManager.getConfig().isEnable_OPSecurity()){
			return;
		}
		
		
		//判断此玩家是否为OP
		if(!pl.isOp()){
			return;
		}
		
		
		//如果玩家没有注册则清除他的OP
		if( ! pl.isRegister()){
			pl.sendMessage(ConfigManager.getLanguage().getOPHasBeenCleared());
			pl.setOp(false);
			return;
		}
			
		//如果玩家没有登录则取消他的OP
		if( ! pl.isLogin()){
			pl.sendMessage(ConfigManager.getLanguage().getOPHasbeenCanceld());
			opList.add(pl.getName());
			pl.setOp(false);
			return;
		}
				
		
	}
	
	
	public void loginSuccess_OpSecurityProcess(FastPlayer pl){
		
		String name=pl.getName();
		
		for(int i=0;i<opList.size();i++){
			
			if(opList.get(i).equals(name)){
				
				pl.setOp(true);
				pl.sendMessage(ConfigManager.getLanguage().getOPRestore());
				opList.remove(i);
			}
			
		}
		
	}
	
	
	public void joinServer_CreativeSecurityProcess(FastPlayer pl){
		
		pl.reload();
		
		//判断是否开启 CreativeModeSecurity
		if(!ConfigManager.getConfig().isEnable_CreativeModeSecurity()){
			return;
		}
	
		//判断玩家是否为CREATIVE模式
		if(! (pl.getGameMode() == GameMode.CREATIVE)){
			return;
		}
		
		//判断玩家是否注册
		if(! pl.isRegister()){
			pl.sendMessage(ConfigManager.getLanguage().getCreativeHasBeenCleared());	
			pl.setGameMode(0);
			return;
		}
		
		//是否登录
		if(! pl.isLogin()){
			pl.sendMessage(ConfigManager.getLanguage().getCreativeModeHasbeenCanceld());	
			pl.setGameMode(0);
			creativeList.add(pl.getName());
			return;
		}
		
	}
	
	
	public void loginSuccess_CreativeSecurityProcess(FastPlayer pl){
		
		String name=pl.getName();
		
		for(int i=0;i<creativeList.size();i++){
			
			if(creativeList.get(i).equals(name)){
				
				pl.setGameMode(1);
				
		
				pl.sendMessage(ConfigManager.getLanguage().getCreativeModeRestore());
				creativeList.remove(i);
			}
			
		}
		
		
	}
	
	
}
