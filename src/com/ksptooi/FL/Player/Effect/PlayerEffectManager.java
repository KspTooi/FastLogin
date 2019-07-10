package com.ksptooi.FL.Player.Effect;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.PAsync.Task.AsyncTask;

public class PlayerEffectManager {

	
	AsyncTask task=null;
	
	public PlayerEffectManager(){
		task = FastLogin.getAsyncTask();
	}
	
	
	
	/**
	 * 为玩家添加一个粒子效果
	 */
	
	public boolean addLoginedEffect(Player pl){
		
		if(ConfigManager.getConfig().isEnable_PlayerLoginedEffect()){
			
			World world = pl.getWorld();
			Location loc = pl.getLocation();
			
			task.taskPlayEffect(world, loc.add(-1, 1.0D, 1), Effect.ENDER_SIGNAL);
			task.taskPlayEffect(world, loc.add(-1, 1.0D, -1), Effect.ENDER_SIGNAL);
			task.taskPlayEffect(world, loc.add(1, 1.0D, 1), Effect.ENDER_SIGNAL);
			task.taskPlayEffect(world, loc.add(1, 1.0D, -1), Effect.ENDER_SIGNAL);
			
			
			return true;
		}
		
		
		return false;
		
	}
	
	
	/**
	 * 为玩家添加失明效果
	 */
	public boolean addPreLoginEffect(Player pl){
	
		
		if(ConfigManager.getConfig().isEnable_PlayerPreLoginEffect()){
			
			task.taskAddPotionEffect(pl, new PotionEffect(PotionEffectType.BLINDNESS , 150000, 1));
			
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * 为玩家移除失明效果
	 */
	public boolean removePreLoginEffect(Player pl){
		
		if(ConfigManager.getConfig().isEnable_PlayerPreLoginEffect()){
			
			
			task.taskRemovePotionEffect(pl, PotionEffectType.BLINDNESS);
			
			
			return true;
		}
		
		return false;
		
	}
	
	
}
