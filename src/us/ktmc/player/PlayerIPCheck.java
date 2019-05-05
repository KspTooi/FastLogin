package us.ktmc.player;

import org.bukkit.entity.Player;

import us.ktmc.util.Var;

public class PlayerIPCheck {

	
	public boolean MaxIPReg(Player pl){
		
		try {
			
			if(!(Var.IO.FileRepeatFind(Var.IPtables, pl.getAddress().getHostName()) < Var.ipmaxreg)){
				return false;
				
			}

		} catch (Exception e) {e.printStackTrace();}
		

		Var.IO.AddtoFile(Var.IPtables, "\r\n"+pl.getAddress().getHostName(), "UTF-8");
		return true;
		
			
	}
	
	
	
	
	
	
	
	
	
	
}
