package com.ksptooi.security;

import org.bukkit.entity.Player;

public class PlayerFilter {

	
	@SuppressWarnings("unused")
	public boolean isRealPlayer(Player pl){
		
		String Address=null;
	
		
		try{
			
			Address=pl.getAddress().getHostName();
			return true;
			
			
		}catch(Exception exc){
			return false;
		}
		
		
		
	}
	
	
	
	
	
}
