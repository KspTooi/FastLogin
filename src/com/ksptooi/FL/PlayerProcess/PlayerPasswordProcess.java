package com.ksptooi.FL.PlayerProcess;

import org.bukkit.entity.Player;

import com.ksptooi.FL.Entity.PlayerDataEntity;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.LogManager;
import com.ksptooi.FL.playerThread.PlayerLoginMessageSendThread;
import com.ksptooi.playerData_BLL.PlayerDataBLL_Interface;
import com.ksptooi.playerData_BLL.PlayerDataBLLimpl;
import com.ksptooi.security.AdvPasswordHash;


public class PlayerPasswordProcess {

	PlayerDataBLL_Interface playerDataBLL=null;
	AdvPasswordHash APH=null;
	LogManager logManager = null;
	PlayerDataBLL_Interface PDBI=null;
	
	public PlayerPasswordProcess(){
		playerDataBLL=new PlayerDataBLLimpl();
		APH = new AdvPasswordHash();
		logManager = new LogManager();
		PDBI = new PlayerDataBLLimpl();
	}
	
	
	/**
	 * 检查玩家的密码是否符合规则(不符合规则将会提示玩家)
	 * 
	 * @param Passwd 要检查的密码
	 */
	public boolean passWordLengthIsAccess(Player pl,String Passwd){
		
		if(Passwd.length()>FUtil.config.getPasswordMaxLength()){
			pl.sendMessage(FUtil.language.getPasswdTooLong());
			return false;
		}
		
		if(Passwd.length()<FUtil.config.getPasswordMinLength()){
			pl.sendMessage(FUtil.language.getPasswdTooShost());
			return false;
		}
		
		return true;
		
	}
	
	
	/**
	 * 更改玩家的密码
	 * 
	 * @param PlayerEntity 玩家实例
	 * @param OldPasswd 原密码
	 * @param NewPasswd 新密码
	 * @param ConfirmNewPasswd 确认新密码
	 */
	public void ChangePasswd(Player playerEntity, String OldPasswd, String NewPasswd,String ConfirmNewPasswd) {
		
		
		//判断有无加密
		if(FUtil.config.getEnable_passwordHash().equalsIgnoreCase("md5")){
			this.ChangePasswdMD5(playerEntity, OldPasswd, NewPasswd, ConfirmNewPasswd);
			return;
		}	
		
		
		PlayerDataEntity PDE = playerDataBLL.getPlayerData(playerEntity);
		
		
		
		//判断输入的旧密码是否正确
		if( ! PDE.getPassword().equals(OldPasswd)){
			playerEntity.sendMessage(FUtil.language.getChangePw_OldpwErr());
			return;
		}
		
		//判断两次输入的密码是否一致
		if(!(NewPasswd.equals(ConfirmNewPasswd))){
			playerEntity.sendMessage(FUtil.language.getChangePw_ConfirmPwError());
			return;
		}
		
		//判断新密码是否与旧密码一致
		if(NewPasswd.equalsIgnoreCase(OldPasswd)){
			playerEntity.sendMessage(FUtil.language.getReModifyPasswd());
			return;
		}
		
		/** 更改密码 - 开始 **/
		
		PDE.setPassword(NewPasswd);
		PDE.setLogin(false);
		
		playerEntity.sendMessage(FUtil.language.getChangePw_Success());
		playerEntity.sendMessage(FUtil.language.getLoginOut());
		
		//同步至数据库
		playerDataBLL.updatePlayerData(PDE);
		
		
		new Thread(new PlayerLoginMessageSendThread(playerEntity)).start();
		
		/** 更改密码 - 结束 **/
	}
	
	
	
	/**
	 * 更改玩家的密码 - 加密适用
	 */
	
	public void ChangePasswdMD5(Player playerEntity, String OldPasswd, String NewPasswd,String ConfirmNewPasswd) {
		
		
		PlayerDataEntity PDE = playerDataBLL.getPlayerData(playerEntity);
		
		
		//判断输入的旧密码是否正确
		if( ! PDE.getPassword().equals(APH.autoCompression(OldPasswd))){
			playerEntity.sendMessage(FUtil.language.getChangePw_OldpwErr());
			return;
		}
		
		//判断两次输入的密码是否一致
		if(!(NewPasswd.equals(ConfirmNewPasswd))){
			playerEntity.sendMessage(FUtil.language.getChangePw_ConfirmPwError());
			return;
		}
		
		//判断新密码是否与旧密码一致
		if(NewPasswd.equalsIgnoreCase(OldPasswd)){
			playerEntity.sendMessage(FUtil.language.getReModifyPasswd());
			return;
		}
		
		/** 更改密码 - 开始 **/
		
		PDE.setPassword(APH.autoCompression(NewPasswd));
		PDE.setLogin(false);
		
		playerEntity.sendMessage(FUtil.language.getChangePw_Success());
		playerEntity.sendMessage(FUtil.language.getLoginOut());
		
		//同步至数据库
		playerDataBLL.updatePlayerData(PDE);
		
		
		new Thread(new PlayerLoginMessageSendThread(playerEntity)).start();
		
		/** 更改密码 - 结束 **/
	}
	
	
	
	
	
	
	
	
	/**用于判断玩家提供的密码是否正确**/
	public boolean isRightPassword(PlayerDataEntity PDE,String password){
		
		
		String Hash = FUtil.config.getEnable_passwordHash();
		
		
		Boolean isSupportOldpwd = FUtil.config.isEnable_SupportOldPassword();
		
		String SaltPassword = password;
		
		
		
		//使用MD5
		if(Hash.equalsIgnoreCase("MD5")){
			
			logManager.DM("使用MD5加密");
			
			
			
			if(APH.autoCompression(SaltPassword).equals(PDE.getPassword())){
				logManager.DM("密码正确");
				return true;
			}
			
			
			
			
			//判断是否支持旧密码
			if(! isSupportOldpwd){
				logManager.DM("密码错误");
				return false;
			}
			
			logManager.DM("支持使用旧密码");
			
			
			//启用旧密码支持
			if(password.equals(PDE.getPassword())){
				logManager.DM("旧密码正确");			
				this.updatePlayerPassword(PDE,SaltPassword);
				return true;
				
			}
			
			logManager.DM("旧密码错误");		
			return false;
		}

		
		
		//没有加密算法 - false & Other	
		if(SaltPassword.equals(PDE.getPassword())){
			return true;
		}
		
		return false;	
		
		
		
	}
	
	
	/**用于升级玩家的旧密码**/
	public void updatePlayerPassword(PlayerDataEntity PDE,String Password){
		
		
	
		PDE.setPassword(APH.autoCompression(Password));
		PDBI.updatePlayerData(PDE);
		


	}
	
	
	
}
