package com.ksptooi.FL.Data.Player.Cache;

import java.util.ArrayList;

import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;

public class PlayerDataCache {

	private static ArrayList<PlayerEntity> PDEC = new ArrayList<PlayerEntity>();
	
	
	
	public static ArrayList<PlayerEntity> getPDEC(){
		return PDEC;	
	}
	
	
	public static boolean isExistsOfPDE(String PlayerName){
		
		for(PlayerEntity PDE:PDEC){
			
			if(PDE.getPlayername().equalsIgnoreCase(PlayerName)){
				return true;
			}
			
			
		}
		
		return false;
		
	}

	
	public static PlayerEntity getPDE(String PlayerName){
		
		for(PlayerEntity PDE:PDEC){
			
			if(PDE.getPlayername().equalsIgnoreCase(PlayerName)){
				return PDE;
			}
			
		}
		
		return null;
	}
	
	public static void removePDE(String PlayerName){
		
		for(int i=0;i<PDEC.size();i++){
			
			if(PDEC.get(i).getPlayername().equalsIgnoreCase(PlayerName)){
				
				PDEC.remove(i);
			}		
			
		}	
	}
	
	
	public static boolean updatePDE(PlayerEntity PDE){
		
		for(int i=0;i<PDEC.size();i++){
			
			if(PDEC.get(i).getPlayername().equalsIgnoreCase(PDE.getPlayername())){
				
				PDEC.set(i, PDE);			
				return true;
			}					
			
		}
		
		PDEC.add(PDE);
		return false;
				
	}
	

	 
	
	
	
}
