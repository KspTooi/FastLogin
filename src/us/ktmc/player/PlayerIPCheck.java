package us.ktmc.player;

import org.bukkit.entity.Player;
import us.ktmc.util.Var;

public class PlayerIPCheck {

	
	public boolean isMaxRegIP(Player pl){
		
		String PlayerIP=pl.getAddress().getHostName();
			
		try {
			
			//如果MaxRegisterIP是0则关闭这个功能
			if(Var.MaxRegisterIP==0){
				return false;
			}
			
			
			//判断该玩家IP是否超出限制
			if(Var.V2.FileRepeatFind(Var.IPtables, PlayerIP) >= Var.MaxRegisterIP){
				return true;
			}
			

		
		//如果没有超出限制则在IPTABLES中添加一条记录
		Var.V2.AddtoFile(Var.IPtables, "\r\n"+PlayerIP);
		return false;
		
		
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		
		}

		
			
	}
	

	
	
	
	
	
	
	
	
}
