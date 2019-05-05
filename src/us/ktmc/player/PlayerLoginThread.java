package us.ktmc.player;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import us.ktmc.util.Language;
import us.ktmc.util.Var;
import us.ktmc.util_interface.FastUtil;

public class PlayerLoginThread implements Runnable{

	Player pl=null;
	String[] args=null;
	File PlConf=null;
	FastUtil Util=Var.Util;
	
	public PlayerLoginThread(Player pl,String[] args){
		
		this.args=args;
		this.pl=pl;
		PlConf=Util.getPlDataFile(pl);
	}
	
	public void run() {
		
		//已登录则关闭线程
		if (Util.isLogin(pl)) {		
			pl.sendMessage(Language.RepeatLogin);
			return;
			
		}

		// 未注册则关闭线程
		if (!Util.isReg(pl)) {		
			pl.sendMessage(Language.NotRegister2);
			return;
			
		}
		
		// 有2名同名玩家在线则全部踢出 并 关闭线程
		
		int PlayerCount=0;
		
		for(Player pl:Bukkit.getServer().getOnlinePlayers()){   		
			
            if(pl.getName().toLowerCase().equalsIgnoreCase(this.pl.getName().toLowerCase())){
            	PlayerCount++;
            }                                   
		}
		
		if(PlayerCount>1){
			
			for(Player pl:Bukkit.getServer().getOnlinePlayers()){   		
				
	            if(pl.getName().toLowerCase().equalsIgnoreCase(this.pl.getName().toLowerCase())){
	            	Var.Util.KickPlayerForBukkit(pl, "[FastLogin]登录异常!服务器内有同名玩家.");
	            	
	            }                                   
			}
			
			return;
			
		}
		
		//结束
		
		
		try {

			//密码正确
			if (Util.isPassword(pl, args[0])) {

				Util.setPlayerLogin(pl, true);
				
				pl.sendMessage(Language.LoginSuccessful);

				//如果玩家在登录前是OP 将OP给回玩家
				for (int i=0;i<Var.opTables.size();i++) {

					if (pl.getName().equals(Var.opTables.get(i))){		
						pl.setOp(true);
						Var.opTables.remove(i);
					}

				}
				
				//调用登录处理
				Var.AH.LoginSuccess(pl);

				//登陆保护开启时将玩家传送回最后下线地点
				if (Var.LocationProtection) {
					
					if (Var.Location_world.equalsIgnoreCase("empty")){
						return;
					}
					
					if (Util.getPlayerQuitLocation(pl) != null) {
						pl.teleport(Util.getPlayerQuitLocation(pl));
					}
					
					return;
				}

				//登录保护不开启则将玩家传送至预设的默认登陆点
				Util.TelePort_DefaultLoginLocation(pl);
				
				
				
				return;

			}

			
			pl.sendMessage(Language.PasswordError);
			return;

		} catch (Exception e) {
			pl.sendMessage(Language.NullPassword);
			return;
		}
		
		
	}

}
