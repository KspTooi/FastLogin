package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Config.Entity.DefaultLocationEntity;

public class FastCommand_SETSPAWN implements FastCommand{

	
	
	
	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
		
		
		/**设置默认登陆位置 - 开始**/
			
		if (!(sender instanceof Player)) {
			FastLogin.getLoggerr().logWarning("·控制台不能使用此类命令!");
			return;
		}

		Player pl = (Player) sender;

		DefaultLocationEntity dle = ConfigManager.getLocation();

		dle.setLoginLocation(pl.getLocation());

		ConfigManager.updateLocation(dle);

		sender.sendMessage(ConfigManager.getLanguage().getSetSpawnSuccess());

		
		
		
	}

}
