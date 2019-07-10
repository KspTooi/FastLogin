package com.ksptooi.FL.Data.PlayerData;

import org.bukkit.entity.Player;

import com.ksptooi.FL.Data.Player.Entity.PlayerData;

public interface PlayerData_Interface {

	
	public boolean createPlayerData(String playerName);
	
	public boolean createPlayerData(Player playerEntity);
	
	public PlayerData getPlayerData(String playerName);
	
	public PlayerData getPlayerData(Player playerEntity);
	
	public boolean updatePlayerData(PlayerData PDE);
	
	
}
