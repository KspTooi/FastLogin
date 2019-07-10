package com.ksptooi.FL.PlayerProcess;

import org.bukkit.entity.Player;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Hash.PasswordHash;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;
import com.ksptooi.FL.Data.Player.Entity.PlayerData;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Data.PlayerData.PlayerData_Interface;
import com.ksptooi.FL.Thread.Player.PlayerLoginMessageSendThread;
import com.ksptooi.FL.Util.Logger;


public class PlayerPasswordProcess {

	PlayerData_Interface playerDataBLL=null;
	PasswordHash APH=null;
	Logger logManager = null;
	PlayerData_Interface PDBI=null;
	
	public PlayerPasswordProcess(){
		playerDataBLL=new PlayerDataManager();
		APH = new PasswordHash();
		logManager = new Logger();
		PDBI = new PlayerDataManager();
	}
	
	
	/**
	 * 检查玩家的密码是否符合规则(不符合规则将会提示玩家)
	 * 
	 * @param Passwd 要检查的密码
	 */
	public boolean passWordLengthIsAccess(FastPlayer pl,String Passwd){
		
		if(Passwd.length()>ConfigManager.getConfig().getPasswordMaxLength()){
			pl.sendMessage(ConfigManager.getLanguage().getPasswdTooLong());
			return false;
		}
		
		if(Passwd.length()<ConfigManager.getConfig().getPasswordMinLength()){
			pl.sendMessage(ConfigManager.getLanguage().getPasswdTooShost());
			return false;
		}
		
		return true;
		
	}
	
	
	/**
	 * 更改玩家的密码
	 * 
	 * @param PlayerData 玩家实例
	 * @param OldPasswd 原密码
	 * @param NewPasswd 新密码
	 * @param ConfirmNewPasswd 确认新密码
	 */
	public void ChangePasswd(Player playerEntity, String OldPasswd, String NewPasswd,String ConfirmNewPasswd) {
		
		
		//判断有无加密
		if(ConfigManager.getConfig().getEnable_passwordHash().equalsIgnoreCase("md5")){
			this.ChangePasswdMD5(playerEntity, OldPasswd, NewPasswd, ConfirmNewPasswd);
			return;
		}	
		
		
		PlayerData PDE = playerDataBLL.getPlayerData(playerEntity);
		
		
		
		//判断输入的旧密码是否正确
		if( ! PDE.getPassword().equals(OldPasswd)){
			playerEntity.sendMessage(ConfigManager.getLanguage().getChangePw_OldpwErr());
			return;
		}
		
		//判断两次输入的密码是否一致
		if(!(NewPasswd.equals(ConfirmNewPasswd))){
			playerEntity.sendMessage(ConfigManager.getLanguage().getChangePw_ConfirmPwError());
			return;
		}
		
		//判断新密码是否与旧密码一致
		if(NewPasswd.equalsIgnoreCase(OldPasswd)){
			playerEntity.sendMessage(ConfigManager.getLanguage().getReModifyPasswd());
			return;
		}
		
		/** 更改密码 - 开始 **/
		
		PDE.setPassword(NewPasswd);
		PDE.setLogin(false);
		
		playerEntity.sendMessage(ConfigManager.getLanguage().getChangePw_Success());
		playerEntity.sendMessage(ConfigManager.getLanguage().getLoginOut());
		
		//同步至数据库
		playerDataBLL.updatePlayerData(PDE);
		
		
		new Thread(new PlayerLoginMessageSendThread(new FastPlayer(playerEntity))).start();
		
		/** 更改密码 - 结束 **/
	}
	
	
	
	/**
	 * 更改玩家的密码 - 加密适用
	 */
	
	public void ChangePasswdMD5(Player playerEntity, String OldPasswd, String NewPasswd,String ConfirmNewPasswd) {
		
		
		PlayerData PDE = playerDataBLL.getPlayerData(playerEntity);
		
		
		//判断输入的旧密码是否正确
		if( ! PDE.getPassword().equals(APH.autoCompression(OldPasswd))){
			playerEntity.sendMessage(ConfigManager.getLanguage().getChangePw_OldpwErr());
			return;
		}
		
		//判断两次输入的密码是否一致
		if(!(NewPasswd.equals(ConfirmNewPasswd))){
			playerEntity.sendMessage(ConfigManager.getLanguage().getChangePw_ConfirmPwError());
			return;
		}
		
		//判断新密码是否与旧密码一致
		if(NewPasswd.equalsIgnoreCase(OldPasswd)){
			playerEntity.sendMessage(ConfigManager.getLanguage().getReModifyPasswd());
			return;
		}
		
		/** 更改密码 - 开始 **/
		
		PDE.setPassword(APH.autoCompression(NewPasswd));
		PDE.setLogin(false);
		
		playerEntity.sendMessage(ConfigManager.getLanguage().getChangePw_Success());
		playerEntity.sendMessage(ConfigManager.getLanguage().getLoginOut());
		
		//同步至数据库
		playerDataBLL.updatePlayerData(PDE);
		
		
		new Thread(new PlayerLoginMessageSendThread(new FastPlayer(playerEntity))).start();
		
		/** 更改密码 - 结束 **/
	}
	
	
	
	
	
	
	
}
