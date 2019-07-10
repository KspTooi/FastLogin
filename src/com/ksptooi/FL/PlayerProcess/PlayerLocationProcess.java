package com.ksptooi.FL.PlayerProcess;

import org.bukkit.entity.Player;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;


public class PlayerLocationProcess {

	
	/**
	 * 将玩家传送到默认登陆位置
	 * 
	 * @param PlayerEntity 玩家实例
	 * @return 成功传送返回True 默认登陆位置不存在返回False
	 */
	public boolean TelePort_DefaultLoginLocation(Player playerEntity){
		
		
		if(! ConfigManager.getLocation().getLocation_world().equalsIgnoreCase("empty")){
			
//			playerEntity.teleport(ConfigManager.getLocation().getLoginLocation());
			
			FastLogin.getAsyncProcess().AsyncTP(playerEntity, ConfigManager.getLocation().getLoginLocation());
			
			
			return true;
			
		}	
		
		return false;
		
	}
	
	
}
