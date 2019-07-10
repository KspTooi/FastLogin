package com.ksptooi.FL.Event.FastEvent;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Config.Entity.Language;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;
import com.ksptooi.FL.Data.Player.Entity.PlayerData;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Util.FUtil;

public class PlayerLoginSuccessEvent implements FastEvent{

	PlayerData pe=null;
	FastPlayer pl = null;
	Language lang = null;

	
	public PlayerLoginSuccessEvent(FastPlayer pl) {
		this.lang = ConfigManager.getLanguage();
		this.pl=pl;
		
	}
	
	@Override
	public void run() {
		
		PlayerDataManager pdm = DataManager.getPlayerDataManager();
		
		pe.setLogin(true);
		pdm.updatePlayerData(pe);
		
		pl.sendMessage(ConfigManager.getLanguage().getLoginSuccess());
		
		
		//如果玩家登录前是OP 赋予玩家OP权限
		FUtil.LS.loginSuccess_OpSecurityProcess(pl);
		
		//如果玩家登录前是创造模式 赋予玩家创造
		FUtil.LS.loginSuccess_CreativeSecurityProcess(pl);
		
		// 自定义登录消息
		FastLogin.getLoggerr().ShowMessage(pl);
		
		//为玩家添加粒子效果
		pl.addLoginedEffect();
		
		//移除玩家的失明效果
		pl.removePreLoginEffect();
		
		//如果isEnable_LoginSecurity为True 则将玩家传送回最后下线的地方
		if(ConfigManager.getConfig().isEnable_LoginSecurity()==true){
			pl.tpLastQuitLocation();
		}
		
		
	}
	
	
	
	
}
