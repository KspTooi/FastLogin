package com.ksptooi.FL.Event.FastEvent;

import org.bukkit.entity.Player;
import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Config.Entity.Language;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Player.Effect.PlayerEffectManager;
import com.ksptooi.FL.PlayerProcess.PlayerPasswordProcess;
import com.ksptooi.FL.Util.FUtil;

public class PlayerLoginEvent implements LittleEvent{

	
	Player pl = null;
	String[] param = null;
	PlayerDataManager pdm = null;
	PlayerPasswordProcess PlayerPasswordProcess = null;
	Language lang = null;
	PlayerEffectManager playerEffectManager = null;
	
	
	public PlayerLoginEvent(Player pl,String[] args) {
		
		this.pl=pl;
		this.param=args;
		this.pdm = DataManager.getPlayerDataManager();
		this.PlayerPasswordProcess = new PlayerPasswordProcess();	
		this.lang = ConfigManager.getLanguage();
		this.playerEffectManager = FastLogin.getPlayerEffectManager();
	}
	
	
	
	
	@Override
	public void run() {
		
		PlayerEntity pe = pdm.getPlayerData(pl);
		
		
		//已登录则关闭线程
		if (pe.isLogin()) {		
			pl.sendMessage(ConfigManager.getLanguage().getRepeatLogin());
			return;
		}
		
		
		// 未注册则关闭线程
		if(!pe.isRegister()){	
			pl.sendMessage(ConfigManager.getLanguage().getNotRegister2());
			return;
		}
		
		
		// 有2名同名玩家在线则全部踢出并关闭线程(影分身)
		if(FUtil.LGD.isGhostPlayer(pl)){		
			FastLogin.getAsyncProcess().AsyncKickPlayer(pl, "有两名名字相同的玩家在线!");			
			return;
		}
		
		//参数错误则关闭线程
		if(param.length<1) {
			pl.sendMessage(lang.getNullPassword());
			return;
		}
		
		
		try {
			
			
			//如果密码错误则关闭线程
			if(!PlayerPasswordProcess.isRightPassword(pe, param[0])) {
				pl.sendMessage(lang.getPasswordError());
				return ;
			}
			
			
			//登陆成功 运行事件
			PlayerLoginSuccessEvent pls=new PlayerLoginSuccessEvent(pe,pl);
			
			FastLogin.getEventManager().runEvent(pls);
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			pl.sendMessage("登陆发生错误!,请联系管理员.");
		}
		
		
		
	}
	

	
	
}
