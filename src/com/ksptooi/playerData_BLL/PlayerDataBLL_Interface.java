package com.ksptooi.playerData_BLL;

import org.bukkit.entity.Player;
import com.ksptooi.FL.Entity.PlayerDataEntity;

public interface PlayerDataBLL_Interface {

	
	public boolean createPlayerData(String playerName);
	
	public boolean createPlayerData(Player playerEntity);
	
	public PlayerDataEntity getPlayerData(String playerName);
	
	public PlayerDataEntity getPlayerData(Player playerEntity);
	
	public boolean updatePlayerData(PlayerDataEntity PDE);
	
	
}
