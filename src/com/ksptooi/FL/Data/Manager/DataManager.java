package com.ksptooi.FL.Data.Manager;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Hash.PasswordHash;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Data.PlayerData.PlayerSqlDataManager;
import com.ksptooi.FL.Player.Check.PlayerNameRuleCheck;
import com.ksptooi.FL.Player.Check.PlayerPasswordRuleCheck;
import com.ksptooi.gdc.v6.Factory.*;
import com.ksptooi.gdc.v6.Manager.GeneralDataFactoryBuilder;

public class DataManager {
	
	
	private static DataSessionFactory dataSessionFactory = null;
	
	private static PlayerDataManager playerDataManager = null;
	
	private static PasswordHash advPasswordHash = null;
	
	private static PlayerPasswordRuleCheck passwordRuleCheck = null;
	
	private static PlayerNameRuleCheck playerNameRuleCheck = null;
	
	private static PlayerSqlDataManager sqlDataManager = null;
	
	private static GeneralDataFactoryBuilder generalDataFactoryBuilder = null;
	
	public static void PreInitDataManager() {
		
		generalDataFactoryBuilder =new GeneralDataFactoryBuilder();	
		dataSessionFactory = generalDataFactoryBuilder.buildDataFactory(32);
	
	}
	
	
	public static void initDataManager(){
				
		advPasswordHash = new PasswordHash();
		
		passwordRuleCheck = new PlayerPasswordRuleCheck();
		
		playerNameRuleCheck = new PlayerNameRuleCheck();
		
		
		//判断是否开启Mysql数据库
		if(ConfigManager.getConfig().getPlayerDataType().equalsIgnoreCase("mysql")) {
			
			sqlDataManager = new PlayerSqlDataManager();
			
		}
		
		playerDataManager = new PlayerDataManager();
		
	}
	
	
	public static GeneralDataFactoryBuilder getGeneralDataFactoryBuilder() {
		return generalDataFactoryBuilder;
	}
	
	
	public static PlayerSqlDataManager getPlayerSqlDataManager() {
		return sqlDataManager;
	}
	
	
	public static PlayerDataManager getPlayerDataManager() {
		return playerDataManager;
	}
	
	public static DataSessionFactory getDataSessionFactory() {
		return dataSessionFactory;
	}
	
	public static PasswordHash getAdvPasswordHash() {
		return advPasswordHash;
	}
	
	public static PlayerPasswordRuleCheck getPlayerPasswordRuleCheck() {
		return passwordRuleCheck;
	}
		
	public static PlayerNameRuleCheck getPlayerNameRuleCheck() {
		return playerNameRuleCheck;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
