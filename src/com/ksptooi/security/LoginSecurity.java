package com.ksptooi.security;

import java.util.ArrayList;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import com.ksptooi.FL.Entity.PlayerDataEntity;
import com.ksptooi.FL.MultiVersion.AdvMultiVersionAsyncProcess;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.playerData_BLL.PlayerDataBLL_Interface;
import com.ksptooi.playerData_BLL.PlayerDataBLLimpl;

public class LoginSecurity {

	private ArrayList<String> opList=null;
	private ArrayList<String> creativeList=null;
	
	PlayerDataBLL_Interface PDBI=null;
	AdvMultiVersionAsyncProcess AMVAP = null;
	
	public LoginSecurity(){
		opList = new ArrayList<String>();
		creativeList = new ArrayList<String>();		
		PDBI=new PlayerDataBLLimpl();
		AMVAP = new AdvMultiVersionAsyncProcess();
	}
	
	
	
	public void joinServer_OpSecurityProcess(Player pl){
		
		PlayerDataEntity PD = PDBI.getPlayerData(pl);
		
		//判断有没有开启OP安全
		if(!FUtil.config.isEnable_OPSecurity()){
			return;
		}
		
		
		//判断此玩家是否为OP
		if(!pl.isOp()){
			return;
		}
		
		
		//如果玩家没有注册则清除他的OP
		if( ! PD.isRegister()){
			pl.sendMessage(FUtil.language.getOPHasBeenCleared());
			pl.setOp(false);
			return;
		}
			
		//如果玩家没有登录则取消他的OP
		if( ! PD.isLogin()){
			pl.sendMessage(FUtil.language.getOPHasbeenCanceld());
			opList.add(pl.getName());
			pl.setOp(false);
			return;
		}
				
		
	}
	
	
	public void loginSuccess_OpSecurityProcess(Player pl){
		
		String name=pl.getName();
		
		for(int i=0;i<opList.size();i++){
			
			if(opList.get(i).equals(name)){
				
				pl.setOp(true);
				pl.sendMessage(FUtil.language.getOPRestore());
				opList.remove(i);
			}
			
		}
		
	}
	
	
	public void joinServer_CreativeSecurityProcess(Player pl){
		
		PlayerDataEntity PDE=PDBI.getPlayerData(pl);
		
		//判断是否开启 CreativeModeSecurity
		if(! FUtil.config.isEnable_CreativeModeSecurity()){
			return;
		}
	
		//判断玩家是否为CREATIVE模式
		if(! (pl.getGameMode() == GameMode.CREATIVE)){
			return;
		}
		
		//判断玩家是否注册
		if(! PDE.isRegister()){
			pl.sendMessage(FUtil.language.getCreativeHasBeenCleared());	
			AMVAP.AsyncSetPlayerGameMode(pl, 0);
			pl.setGameMode(GameMode.SURVIVAL);
			return;
		}
		
		//是否登录
		if(! PDE.isLogin()){
			pl.sendMessage(FUtil.language.getCreativeModeHasbeenCanceld());	
			AMVAP.AsyncSetPlayerGameMode(pl, 0);
			creativeList.add(pl.getName());
			return;
		}
		
	}
	
	
	public void loginSuccess_CreativeSecurityProcess(Player pl){
		
		String name=pl.getName();
		
		for(int i=0;i<creativeList.size();i++){
			
			if(creativeList.get(i).equals(name)){
				
				AMVAP.AsyncSetPlayerGameMode(pl, 1);
		
				pl.sendMessage(FUtil.language.getCreativeModeRestore());
				creativeList.remove(i);
			}
			
		}
		
		
	}
	
	
}
