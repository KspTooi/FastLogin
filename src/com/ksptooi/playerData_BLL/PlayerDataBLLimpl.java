package com.ksptooi.playerData_BLL;

import org.bukkit.entity.Player;
import com.ksptooi.FL.Entity.PlayerDataEntity;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.LogManager;
import com.ksptooi.gdc.FileAPI.IOController_V5;
import com.ksptooi.playerData_DAL.PlayerDataDAL_Interface;
import com.ksptooi.playerData_DAL.PlayerDataDALimpl;

public class PlayerDataBLLimpl implements PlayerDataBLL_Interface{

	LogManager lm=null;
	IOController_V5 v5=null;
	PlayerDataDAL_Interface playerDataDAL=null;
	PlayerSqlDataBLL PSDB=null;
	
	public PlayerDataBLLimpl(){
		lm = new LogManager();
		v5 = new IOController_V5();
		playerDataDAL = new PlayerDataDALimpl();
		PSDB=FUtil.playerSqlDataBLL;
	}

	
	@Override
	public boolean createPlayerData(String playerName) {

		
		playerName=playerName.toLowerCase();
		
		PSDB=FUtil.playerSqlDataBLL;
		
		
		if(FUtil.config.getPlayerDataType().equalsIgnoreCase("mysql")){
	
			return PSDB.createPlayerData(playerName);
			
		}
		
		return playerDataDAL.createPlayerData(playerName);
	}
	

	@Override
	public boolean createPlayerData(Player playerEntity) {
		return this.createPlayerData(playerEntity.getName());
	}

	@Override
	public PlayerDataEntity getPlayerData(String playerName) {
		
		playerName=playerName.toLowerCase();
		
		PSDB=FUtil.playerSqlDataBLL;
		
		if(FUtil.config.getPlayerDataType().equalsIgnoreCase("mysql")){
			
			return PSDB.getPlayerData(playerName.toLowerCase());
			
		}
		
		this.createPlayerData(playerName);
		
		return playerDataDAL.queryPlayerDataByName(playerName);
	}

	@Override
	public PlayerDataEntity getPlayerData(Player playerEntity) {
		return this.getPlayerData(playerEntity.getName());
	}


	@Override
	public boolean updatePlayerData(PlayerDataEntity PDE) {
		
		
		PSDB=FUtil.playerSqlDataBLL;
		
		if(FUtil.config.getPlayerDataType().equalsIgnoreCase("mysql")){
			
			return PSDB.updatePlayerData(PDE);
			
		}
		
		return playerDataDAL.updatePlayerData(PDE);
	}	
	

}

