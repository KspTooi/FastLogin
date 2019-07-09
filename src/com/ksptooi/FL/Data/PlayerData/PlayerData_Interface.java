package com.ksptooi.FL.Data.PlayerData;

import org.bukkit.entity.Player;

import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;

public interface PlayerData_Interface {

	
	public boolean createPlayerData(String playerName);
	
	public boolean createPlayerData(Player playerEntity);
	
	public PlayerEntity getPlayerData(String playerName);
	
	public PlayerEntity getPlayerData(Player playerEntity);
	
	public boolean updatePlayerData(PlayerEntity PDE);
	
	
}
