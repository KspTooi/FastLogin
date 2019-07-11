package com.ksptooi.FL.Data.PlayerData;

import org.bukkit.entity.Player;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Entity.PlayerData;
import com.ksptooi.FL.Data.PlayerDataIO.PlayerDataIO;
import com.ksptooi.FL.Data.PlayerDataIO.PlayerDataIO_Interfrace;
import com.ksptooi.FL.Util.Logger;

public class PlayerDataManager implements PlayerData_Interface{

	Logger lm=null;
	PlayerDataIO_Interfrace playerDataIO=null;
	
	PlayerSqlDataManager sqlDataManager=null;
	
	public PlayerDataManager(){
		
		lm = new Logger();
		
		playerDataIO = new PlayerDataIO();
		
		sqlDataManager=DataManager.getPlayerSqlDataManager();
	}

	
	@Override
	public boolean createPlayerData(String playerName) {

		
		playerName=playerName.toLowerCase();
		
		
		if(ConfigManager.getConfig().getPlayerDataType().equalsIgnoreCase("mysql")){
	
			return sqlDataManager.createPlayerData(playerName);
			
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
		
		
		if(ConfigManager.getConfig().getPlayerDataType().equalsIgnoreCase("mysql")){
			
			return sqlDataManager.getPlayerData(playerName.toLowerCase());
			
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
		
		
		
		if(ConfigManager.getConfig().getPlayerDataType().equalsIgnoreCase("mysql")){
			
			return sqlDataManager.updatePlayerData(PDE);
			
		}
		
		return playerDataIO.updatePlayerData(PDE);
	}	
	

}

