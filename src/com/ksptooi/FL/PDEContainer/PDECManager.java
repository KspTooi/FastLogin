package com.ksptooi.FL.PDEContainer;

import java.util.ArrayList;

import com.ksptooi.FL.Entity.PlayerDataEntity;

public class PDECManager {

	private static ArrayList<PlayerDataEntity> PDEC = new ArrayList<PlayerDataEntity>();
	
	
	
	public static ArrayList<PlayerDataEntity> getPDEC(){
		return PDEC;	
	}
	
	
	public static boolean isExistsOfPDE(String PlayerName){
		
		for(PlayerDataEntity PDE:PDEC){
			
			if(PDE.getPlayername().equalsIgnoreCase(PlayerName)){
				return true;
			}
			
			
		}
		
		return false;
		
	}

	
	public static PlayerDataEntity getPDE(String PlayerName){
		
		for(PlayerDataEntity PDE:PDEC){
			
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
	
	
	public static boolean updatePDE(PlayerDataEntity PDE){
		
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
