package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.ksptooi.FL.Data.Config.ConfigManager;

public class FastCommand_RELOAD implements FastCommand{


	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
		
		
		/**配置文件重载 - 开始**/	
		ConfigManager.readConfig();
		ConfigManager.readLanguage();
		ConfigManager.readLocation();

		sender.sendMessage("§c[FastLogin]·重新载入配置文件");								
		
		
	}
	
	
	

}
