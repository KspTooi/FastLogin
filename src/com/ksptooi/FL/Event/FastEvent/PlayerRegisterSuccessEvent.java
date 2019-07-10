package com.ksptooi.FL.Event.FastEvent;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Hash.PasswordHash;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;
import com.ksptooi.FL.Util.FUtil;

public class PlayerRegisterSuccessEvent implements FastEvent{

	FastPlayer pl = null;
	
	String passWord = null;
	
	public PlayerRegisterSuccessEvent(FastPlayer pe,String passWord) {
		
		this.pl=pe;
		this.passWord=passWord;
		
	}

	@Override
	public void run() {
		
		try {
		


			pl.reload();

			PasswordHash passwordHash = DataManager.getAdvPasswordHash();

			pl.setPassword(passwordHash.autoCompression(passWord));
			pl.setRegister(true);
			pl.setLogin(true);

			pl.save();

			pl.sendMessage(ConfigManager.getLanguage().getRegisterSuccess());

			FUtil.RIC.addIP(pl);

			// 为玩家添加粒子效果
			pl.addLoginedEffect();
			// 移除玩家的失明效果
			pl.removePreLoginEffect();

			// 自定义登录消息
			FastLogin.getLoggerr().ShowMessage(pl);

			// 将玩家传送到默认登陆位置
			

			
		} catch (Exception e) {
			e.printStackTrace();
			pl.sendMessage("注册发生错误!,请联系管理员.");
		}
		
	}
	
	
	
	
	
	
	
	
}
