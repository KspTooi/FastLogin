package us.ktmc.util_interface;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import us.ktmc.util.Var;

public class FastSecurity {
	
	
	/**
	 * 用于判断服务器内是否有2名同名玩家在线 (影分身)
	 * @return 如果有2名同名玩家在线返回True 否则 False
	 */
	public boolean isShadowGhost(Player CheckPlayer){
		
		int PlayerCount=0;
		
		for(Player pl:Bukkit.getServer().getOnlinePlayers()){   		
			
            if(pl.getName().equalsIgnoreCase(CheckPlayer.getName())){
            	PlayerCount++;
            }                                   
		}
		
		if(PlayerCount>1){
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * 用于踢出使用了影分身BUG的相关玩家(全部)
	 * @param CheckPlayer 使用了影分身的玩家
	 */
	public void KickShadowGhost(Player CheckPlayer){
		
		if(Var.Enable_SecurityWarning){
			
			for(Player pl:Bukkit.getServer().getOnlinePlayers()){   		
				pl.sendMessage("§e[FastLogin]§c!§b玩家§c"+CheckPlayer.getName()+"§b尝试使用BUG时被踢");                             
			}
			System.out.println("[FastLogin]·玩家"+CheckPlayer.getName()+"尝试使用BUG时被踢");
			
		}
		
		
		//轮询踢出该玩家
		for(Player pl:Bukkit.getServer().getOnlinePlayers()){   		
			
            if(pl.getName().equalsIgnoreCase(CheckPlayer.getName())){
            	
            	Var.FS.KickPlayerForBukkit(pl, "[FastLogin]·登录异常!服务器内有2名同名玩家.");
            	
            }                                   
		}	
		
	}
	
	/**
	 * 异步转同步踢出玩家
	 * @param pl
	 */
	public void KickPlayerForBukkit(Player pl,String KickText){
		
		Bukkit.getScheduler().runTask(Var.MainClass, new Runnable(){
			
			public void run(){
				pl.kickPlayer(KickText);
			}	
			
		});	
		
	}
	
	
	

}
