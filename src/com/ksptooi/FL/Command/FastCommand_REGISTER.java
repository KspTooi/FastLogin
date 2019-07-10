package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Event.FastEvent.PlayerRegisterEvent;

public class FastCommand_REGISTER implements FastCommand{

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("register")|cmd.getName().equalsIgnoreCase("reg")){
			
			if(!(sender instanceof Player)){
				FastLogin.getLoggerr().logWarning("·控制台不能使用此类命令!");
				return;
			}		
			
			Player pl =(Player)sender;
			
			//提交注册事件
			PlayerRegisterEvent ple=new PlayerRegisterEvent(pl, args);
			
			FastLogin.getEventManager().runFastEvent(ple);	
			
//			new Thread(new PlayerRegThread(pl,args)).start();
			
		}
		
	}

}
