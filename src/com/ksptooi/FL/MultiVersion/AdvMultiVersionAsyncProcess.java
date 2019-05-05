package com.ksptooi.FL.MultiVersion;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.ksptooi.FL.Util.FUtil;

public class AdvMultiVersionAsyncProcess {

	
	
	
	//异步转同步踢出玩家
	public void AsyncKickPlayer(final Player pl,final String KickMessage){
		
		Bukkit.getScheduler().runTask(FUtil.MainClass, new Runnable() {

			public void run() {
				pl.kickPlayer(KickMessage);
			}

		});
		
	}
	
	
	//异步转同步设置玩家的游戏模式
	public void AsyncSetPlayerGameMode(final Player pl,final int GameModeCode){
		
		Bukkit.getScheduler().runTask(FUtil.MainClass, new Runnable() {

			public void run() {
				
				if(GameModeCode==0){
					pl.setGameMode(GameMode.SURVIVAL);
				}
				
				if(GameModeCode==1){
					pl.setGameMode(GameMode.CREATIVE);
				}
				
				if(GameModeCode==2){
					pl.setGameMode(GameMode.ADVENTURE);
				}
				
			}

		});
	}
	
	//异步转同步为玩家添加Effect
	public void AsyncAddPotionEffect(final Player pl,final PotionEffect PE){
		
		
		Bukkit.getScheduler().runTask(FUtil.MainClass, new Runnable() {

			public void run() {
				
				
				pl.addPotionEffect(PE,false);
				
			}

		});
		
		
	}
	
	//异步转同步为玩家移除Effect
	public void AsyncRemovePotionEffect(final Player pl,final PotionEffectType PET){
		
		
		Bukkit.getScheduler().runTask(FUtil.MainClass, new Runnable() {

			public void run() {
				
				
				pl.removePotionEffect(PET);
				
			}

		});
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
}
