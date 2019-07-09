package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;

public class FastCommand_SPAWN implements FastCommand{

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {

		//传送到登录位置
			
		if (!(sender instanceof Player)) {
			FastLogin.getLoggerr().logWarning("·控制台不能使用此类命令!");
			return;
		}

		Player pl=(Player)sender;
		
		if (ConfigManager.getLocation().getLocation_world().equalsIgnoreCase("empty")) {
			sender.sendMessage("§c没有找到一个登录位置,如果你已经设置,请尝试重载插件.");
			return;
		}

		sender.sendMessage(ConfigManager.getLanguage().getTPSpawnSuccess());
		
		pl.teleport(ConfigManager.getLocation().getLoginLocation());
		
		
	}

}
