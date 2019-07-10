package com.ksptooi.FL.Event.FastEvent;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Config.Entity.Language;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Player.Check.PlayerPasswordRuleCheck;
import com.ksptooi.FL.Player.Effect.PlayerEffectManager;

public class PlayerLoginEvent implements FastEvent{

	
	FastPlayer pl = null;
	String[] param = null;
	PlayerDataManager pdm = null;
	PlayerPasswordRuleCheck PlayerPasswordProcess = null;
	Language lang = null;
	PlayerEffectManager playerEffectManager = null;
	
	
	public PlayerLoginEvent(FastPlayer pl,String[] args) {
		
		this.pl=pl;
		this.param=args;
		this.pdm = DataManager.getPlayerDataManager();
		this.PlayerPasswordProcess = new PlayerPasswordRuleCheck();	
		this.lang = ConfigManager.getLanguage();
		this.playerEffectManager = FastLogin.getPlayerEffectManager();
	}
	
	
	
	
	@Override
	public void run() {
		
		pl.reload();
		
		
		//已登录则关闭线程
		if (pl.isLogin()) {		
			pl.sendMessage(ConfigManager.getLanguage().getRepeatLogin());
			return;
		}
		
		
		// 未注册则关闭线程
		if(!pl.isRegister()){	
			pl.sendMessage(ConfigManager.getLanguage().getNotRegister2());
			return;
		}
		
		
		// 有2名同名玩家在线则全部踢出并关闭线程(影分身)
		if(pl.isGhostPlayer()){		
			pl.kickPlayer("有两名名字相同的玩家在线!");	
			return;
		}
		
		//参数错误则关闭线程
		if(param.length<1) {
			pl.sendMessage(lang.getNullPassword());
			return;
		}
		
		
		try {
			
			
			//如果密码错误则关闭线程
			if(!pl.isRightPassword(param[0])) {
				pl.sendMessage(lang.getPasswordError());
				return ;
			}
			
			
			//登陆成功 运行事件
			PlayerLoginSuccessEvent pls=new PlayerLoginSuccessEvent(pl);
			
			FastLogin.getEventManager().runFastEvent(pls);
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			pl.sendMessage("登陆发生错误!,请联系管理员.");
		}
		
		
		
	}
	

	
	
}
