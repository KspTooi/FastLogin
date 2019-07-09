package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Config.Entity.DefaultLocationEntity;

public class FastCommand_DELSPAWN implements FastCommand{

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
		
			
		if (!(sender instanceof Player)) {
			FastLogin.getLoggerr().logWarning("·控制台不能使用此类命令!");
			return;
		}
		
		
		if (ConfigManager.getLocation().getLocation_world().equalsIgnoreCase("empty")) {
			sender.sendMessage("§c没有找到一个登录位置,如果你已经设置,请尝试重载插件.");
			return;
		}
		
		
		DefaultLocationEntity location = ConfigManager.getLocation();
		
		location.removeLoc();
		
		ConfigManager.updateLocation(location);


		sender.sendMessage(ConfigManager.getLanguage().getDeleteSpawnSuccess());
		
	}

}
