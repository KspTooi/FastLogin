package com.ksptooi.FL.playerThread;

import org.bukkit.entity.Player;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;
import com.ksptooi.FL.Data.PlayerData.PlayerData_Interface;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Performance.PerformanceMonitorManager;
import com.ksptooi.FL.PlayerProcess.PlayerEffectProcess;
import com.ksptooi.FL.PlayerProcess.PlayerLocationProcess;
import com.ksptooi.FL.PlayerProcess.PlayerPasswordProcess;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.Logger;
import com.ksptooi.FL.security.AdvPasswordHash;


public class AdvPlayerRegThread implements Runnable{

	
	Player pl=null;	
	String[] args=null;	
	PlayerData_Interface playerDataBLL=null;
	PlayerPasswordProcess pwdProcess=null;
	PlayerLocationProcess playerLocationProcess=null;
	PlayerEffectProcess PEP=null;
	Logger lm=null;
	AdvPasswordHash APH=null;
	

	
	public AdvPlayerRegThread(Player PlayerEntity,String[] args){
		
		playerDataBLL=new PlayerDataManager();
		this.pl=PlayerEntity;		
		this.args=args;		
		pwdProcess=new PlayerPasswordProcess();
		playerLocationProcess=new PlayerLocationProcess();
		PEP = new PlayerEffectProcess();
		lm=new Logger();
		APH = new AdvPasswordHash();
		
	}
	
	
	public void run() {
		
		//添加线程性能计数
		PerformanceMonitorManager.addPATC();
			
		try{		
			
			
			String Passwd=null;
			String ConfirmPasswd=null;
				
			//检查注册参数是否合法
			if(args.length < 3){
				pl.sendMessage(ConfigManager.getLanguage().getNoConfirmPasswd());
				PerformanceMonitorManager.removePATC();
				return ;
			}
			
			Passwd=args[1];
			ConfirmPasswd=args[2];


			//判断是否已注册
			PlayerEntity PDE = playerDataBLL.getPlayerData(pl);
			
			if(PDE.isRegister()){
				pl.sendMessage(ConfigManager.getLanguage().getRepeatRegister());
				PerformanceMonitorManager.removePATC();
				return;
			}
			
			
			
			//判断ip是否已经达到限额	
			if(FUtil.RIC.playerIp_isMaxReg(pl)){
				pl.sendMessage("注册失败,每个IP最多只能注册"+ConfigManager.getConfig().getMaxRegisterIP()+"个账号,你已超出限额！");
				PerformanceMonitorManager.removePATC();
				return;
			}
				
			
			//检查密码长度
			if(!pwdProcess.passWordLengthIsAccess(pl, ConfirmPasswd)){
				PerformanceMonitorManager.removePATC();
				return;
			}
			
			
			//检查密码与确认密码是否一致
			if(!(Passwd.equals(ConfirmPasswd))){
				pl.sendMessage(ConfigManager.getLanguage().getConfirmPasswdError());
				PerformanceMonitorManager.removePATC();
				return ;
			}
			
			
			PDE.setPassword(APH.autoCompression(ConfirmPasswd));
			PDE.setRegister(true);
			PDE.setLogin(true);
			
			playerDataBLL.updatePlayerData(PDE);
			
			pl.sendMessage(ConfigManager.getLanguage().getRegisterSuccess());
			
			FUtil.RIC.addIP(pl);
			
			//为玩家添加粒子效果
			PEP.addLoginedEffect(pl);			
			//移除玩家的失明效果
			PEP.removePreLoginEffect(pl);
			
			// 自定义登录消息
			lm.ShowMessage(pl);

			
			//将玩家传送到默认登陆位置
			playerLocationProcess.TelePort_DefaultLoginLocation(pl);
		
			PerformanceMonitorManager.removePATC();
			return;
						
			
		} catch (Exception e){
			
			pl.sendMessage(ConfigManager.getLanguage().getNullPassword());
			PerformanceMonitorManager.removePATC();
			return;
			
		}
		
			
		
	}

	
	
}
