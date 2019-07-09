package com.ksptooi.FL.Data.Manager;

import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.security.AdvPasswordHash;
import com.ksptooi.gdc.v6.Factory.*;
import com.ksptooi.gdc.v6.Manager.GeneralDataFactoryBuilder;

public class DataManager {
	
	
	private static DataSessionFactory dataSessionFactory = null;
	
	private static PlayerDataManager playerDataManager = null;
	
	private static AdvPasswordHash advPasswordHash = null;
	
	static {
		
		GeneralDataFactoryBuilder gdfb=new GeneralDataFactoryBuilder();
		
		dataSessionFactory = gdfb.buildDataFactory(32);
		
		playerDataManager = new PlayerDataManager();
		
		advPasswordHash = new AdvPasswordHash();
		
	}
	
	
	public static PlayerDataManager getPlayerDataManager() {
		return playerDataManager;
	}
	
	public static DataSessionFactory getDataSessionFactory() {
		return dataSessionFactory;
	}
	
	public static AdvPasswordHash getAdvPasswordHash() {
		return advPasswordHash;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
