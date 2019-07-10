package com.ksptooi.FL.Event.FastEvent;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Cache.PlayerDataCache;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;
import com.ksptooi.FL.Player.Check.PlayerNameRuleCheck;
import com.ksptooi.FL.Util.FUtil;

public class PlayerJo1nEvent implements FastEvent{

	FastPlayer pl = null;
	
	public PlayerJo1nEvent(FastPlayer pl) {
		this.pl = pl;
	}
	
	
	
	@Override
	public void run() {
		
		PlayerNameRuleCheck nameRuleCheck = DataManager.getPlayerNameRuleCheck();
		
		
		//验证是否为realPlayer
		if(!pl.isRealPlayer()){
			return;
		}
		
		//清除玩家数据缓存
		PlayerDataCache.removePlayerData(pl.getName());
		
		//初始化玩家属性
		pl.createData();
			
		pl.setLogin(false);
		
		pl.save();
			
		
		pl.tpFastSpawn();
		
		
		//OP安全检测
		FUtil.LS.joinServer_OpSecurityProcess(pl);
		//创造安全检测
		FUtil.LS.joinServer_CreativeSecurityProcess(pl);
		
		
		//玩家名称检测
		if(! nameRuleCheck.nameValid(pl)){
			return;
		}
		
		//为玩家添加失明效果
		pl.addPreLoginEffect();
		
		//添加Online列表
		FastLogin.addOnlinePlayer(pl.getName(), pl);	
		
		//全部通过则开启一个玩家登录监测线程
		PlayerTelemetryEvent eve=new PlayerTelemetryEvent(pl);
		FastLogin.getEventManager().runFastEvent(eve);
			
	}

}
