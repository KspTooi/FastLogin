package com.ksptooi.FL.security;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.Logger;

public class LoginGhostDefense {

	Logger lm=null;
	
	public LoginGhostDefense(){
		lm=new Logger();
	}
	
	public boolean isGhostPlayer(Player pl){
		
		int i=0;

		
		for(Player p:Bukkit.getServer().getOnlinePlayers()){
			
			if(p.getName().equalsIgnoreCase(pl.getName())){
				i++;
			}
			
		}
		
		
		if(i>1){
			return true;
		}
		
		return false;
		
	}
	
	public void kickPlayer(Player pl){
		
		
		if(ConfigManager.getConfig().isEnable_SecurityWarning()){
			
			
			for(Player p:Bukkit.getServer().getOnlinePlayers()){
				p.sendMessage("§e[FastLoginSecurity]§c·玩家"+pl.getName()+"因为触发了影分身Bug而被踢出服务器.");
			}
			
			lm.logError("玩家"+pl.getName()+"因为触发了影分身Bug而被踢出服务器");
			
		}
		
		
		Bukkit.getScheduler().runTask(FUtil.MainClass, new Runnable() {

			@Override
			public void run() {
				
				for(Player p:Bukkit.getServer().getOnlinePlayers()){
					
					if(p.getName().equalsIgnoreCase(pl.getName())){
						p.kickPlayer("有两名名字相同的玩家在线!");
					}
				
				}
				
			}
			

		});
		
	}
	
	
	
}
