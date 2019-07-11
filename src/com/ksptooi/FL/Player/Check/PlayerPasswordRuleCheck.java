package com.ksptooi.FL.Player.Check;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Config.Entity.Language;
import com.ksptooi.FL.Data.Hash.PasswordHash;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;


public class PlayerPasswordRuleCheck {


	private PasswordHash passwordHash = null;
	private Language lang = null;
	
	public PlayerPasswordRuleCheck() {
		
		this.passwordHash = DataManager.getAdvPasswordHash();
		
		this.lang = ConfigManager.getLanguage();
		
	}
	
	
	
	/**
	 * 检查玩家的密码是否符合规则(不符合规则将会提示玩家)
	 * 
	 * @param Passwd 要检查的密码
	 */
	public boolean pwdIsValid(FastPlayer pl,String Passwd){
		
		if(Passwd.length()>ConfigManager.getConfig().getPasswordMaxLength()){
			pl.sendMessage(lang.getPasswdTooLong());
			return false;
		}
		
		if(Passwd.length()<ConfigManager.getConfig().getPasswordMinLength()){
			pl.sendMessage(lang.getPasswdTooShost());
			return false;
		}
		
		return true;
		
	}
	
	
	//检查玩家的更改密码请求是否有效(将会自动提示玩家)
	public boolean isValid(FastPlayer pl,String OldPasswd,String NewPasswd,String ConfirmNewPasswd) {
		
		
		//密码长度检查
		
		if( ! this.pwdIsValid(pl, NewPasswd)) {
			return false;
		}	
		
		
		//判断输入的旧密码是否正确
		if( ! pl.getPassword().equals(OldPasswd)){
			pl.sendMessage(lang.getChangePw_OldpwErr());
			return false;
		}
		
		//判断两次输入的密码是否一致
		if(!(NewPasswd.equals(ConfirmNewPasswd))){
			pl.sendMessage(lang.getChangePw_ConfirmPwError());
			return false;
		}
		
		//判断新密码是否与旧密码一致
		if(NewPasswd.equalsIgnoreCase(OldPasswd)){
			pl.sendMessage(lang.getReModifyPasswd());
			return false;
		}
		
		
		return true;
		
	}
	
	
	//检查玩家的更改密码请求是否有效(将会自动提示玩家) 支持MD5
	public boolean isValidMD5(FastPlayer pl,String OldPasswd,String NewPasswd,String ConfirmNewPasswd) {
		
		
		//密码长度检查
		
		if( ! this.pwdIsValid(pl, NewPasswd)) {
			return false;
		}	
		
		
		//判断输入的旧密码是否正确
		if( ! pl.getPassword().equals(passwordHash.autoCompression(OldPasswd))){
			pl.sendMessage(lang.getChangePw_OldpwErr());
			return false;
		}
		
		//判断两次输入的密码是否一致
		if(!(NewPasswd.equals(ConfirmNewPasswd))){
			pl.sendMessage(lang.getChangePw_ConfirmPwError());
			return false;
		}
		
		//判断新密码是否与旧密码一致
		if(NewPasswd.equalsIgnoreCase(OldPasswd)){
			pl.sendMessage(lang.getReModifyPasswd());
			return false;
		}
		
		
		return true;
		
	}
	
	
}
