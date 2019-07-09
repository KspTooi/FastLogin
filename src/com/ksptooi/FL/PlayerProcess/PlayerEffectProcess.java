package com.ksptooi.FL.PlayerProcess;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Process.Player.PlayerAsyncProcess;
import com.ksptooi.FL.Util.FUtil;

public class PlayerEffectProcess {

	
	PlayerAsyncProcess AMVAP=null;
	
	
	public PlayerEffectProcess(){
		AMVAP = new PlayerAsyncProcess();
	}
	
	
	
	
	
	
	
	/**
	 * 为玩家添加一个粒子效果
	 */
	
	public boolean addLoginedEffect(Player pl){
		
		if(ConfigManager.getConfig().isEnable_PlayerLoginedEffect()){
			pl.getWorld().playEffect(pl.getLocation().add(-1, 1.0D, 1),Effect.ENDER_SIGNAL, 0);
			pl.getWorld().playEffect(pl.getLocation().add(-1, 1.0D, -1),Effect.ENDER_SIGNAL, 0);
			pl.getWorld().playEffect(pl.getLocation().add(1, 1.0D, 1),Effect.ENDER_SIGNAL, 0);
			pl.getWorld().playEffect(pl.getLocation().add(1, 1.0D, -1),Effect.ENDER_SIGNAL, 0);
			return true;
		}
		
		
		return false;
		
	}
	
	
	/**
	 * 为玩家添加失明效果
	 */
	public boolean addPreLoginEffect(Player pl){
	
		
		if(ConfigManager.getConfig().isEnable_PlayerPreLoginEffect()){
			
			AMVAP.AsyncAddPotionEffect(pl, new PotionEffect(PotionEffectType.BLINDNESS , 150000, 1));
			
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * 为玩家移除失明效果
	 */
	public boolean removePreLoginEffect(Player pl){
		
		if(ConfigManager.getConfig().isEnable_PlayerPreLoginEffect()){
			
			
			Bukkit.getScheduler().runTask(FUtil.MainClass, new Runnable() {

				public void run() {
					AMVAP.AsyncRemovePotionEffect(pl, PotionEffectType.BLINDNESS);
				}

			});

			
			return true;
		}
		
		return false;
		
	}
	
	
}
