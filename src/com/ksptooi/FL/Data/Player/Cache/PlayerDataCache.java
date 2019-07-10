package com.ksptooi.FL.Data.Player.Cache;

import java.util.ArrayList;

import com.ksptooi.FL.Data.Player.Entity.PlayerData;

public class PlayerDataCache {

	private static ArrayList<PlayerData> listPlayerDataEntity = new ArrayList<PlayerData>();
	
	
	
	public static ArrayList<PlayerData> getPlayerDataList(){
		return listPlayerDataEntity;	
	}
	
	
	public static boolean isExistsOfPlayerData(String PlayerName){
		
		for(PlayerData PDE:listPlayerDataEntity){
			
			if(PDE.getPlayername().equalsIgnoreCase(PlayerName)){
				return true;
			}
			
			
		}
		
		return false;
		
	}

	
	public static PlayerData getPlayerData(String PlayerName){
		
		for(PlayerData PDE:listPlayerDataEntity){
			
			if(PDE.getPlayername().equalsIgnoreCase(PlayerName)){
				return PDE;
			}
			
		}
		
		return null;
	}
	
	public static void removePlayerData(String PlayerName){
		
		for(int i=0;i<listPlayerDataEntity.size();i++){
			
			if(listPlayerDataEntity.get(i).getPlayername().equalsIgnoreCase(PlayerName)){
				
				listPlayerDataEntity.remove(i);
			}		
			
		}	
	}
	
	
	public static boolean updatePlayerData(PlayerData PDE){
		
		for(int i=0;i<listPlayerDataEntity.size();i++){
			
			if(listPlayerDataEntity.get(i).getPlayername().equalsIgnoreCase(PDE.getPlayername())){
				
				listPlayerDataEntity.set(i, PDE);			
				return true;
			}					
			
		}
		
		listPlayerDataEntity.add(PDE);
		return false;
				
	}
	

	 
	
	
	
}
