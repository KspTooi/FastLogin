package com.ksptooi.FL.Data.PlayerData;

import org.bukkit.entity.Player;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.PlayerData;
import com.ksptooi.FL.Data.PlayerDataIO.PlayerDataIO;
import com.ksptooi.FL.Data.PlayerDataIO.PlayerDataIO_Interfrace;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.Logger;

public class PlayerDataManager implements PlayerData_Interface{

	Logger lm=null;
	PlayerDataIO_Interfrace playerDataIO=null;
	PlayerSqlDataManager PSDB=null;
	
	public PlayerDataManager(){
		lm = new Logger();
		playerDataIO = new PlayerDataIO();
		PSDB=FUtil.playerSqlDataBLL;
	}

	
	@Override
	public boolean createPlayerData(String playerName) {

		
		playerName=playerName.toLowerCase();
		
		PSDB=FUtil.playerSqlDataBLL;
		
		
		if(ConfigManager.getConfig().getPlayerDataType().equalsIgnoreCase("mysql")){
	
			return PSDB.createPlayerData(playerName);
			
		}
		
		return playerDataIO.createPlayerData(playerName);
	}
	

	@Override
	public boolean createPlayerData(Player playerEntity) {
		return this.createPlayerData(playerEntity.getName());
	}

	@Override
	public PlayerData getPlayerData(String playerName) {
		
		playerName=playerName.toLowerCase();
		
		PSDB=FUtil.playerSqlDataBLL;
		
		if(ConfigManager.getConfig().getPlayerDataType().equalsIgnoreCase("mysql")){
			
			return PSDB.getPlayerData(playerName.toLowerCase());
			
		}
		
		this.createPlayerData(playerName);
		
		return playerDataIO.queryPlayerDataByName(playerName);
	}

	@Override
	public PlayerData getPlayerData(Player playerEntity) {
		return this.getPlayerData(playerEntity.getName());
	}


	@Override
	public boolean updatePlayerData(PlayerData PDE) {
		
		
		PSDB=FUtil.playerSqlDataBLL;
		
		if(ConfigManager.getConfig().getPlayerDataType().equalsIgnoreCase("mysql")){
			
			return PSDB.updatePlayerData(PDE);
			
		}
		
		return playerDataIO.updatePlayerData(PDE);
	}	
	

}

