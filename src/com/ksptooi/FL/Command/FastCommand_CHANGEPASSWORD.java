package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.PlayerProcess.PlayerPasswordProcess;

public class FastCommand_CHANGEPASSWORD implements FastCommand{

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
		
		
			PlayerPasswordProcess pwdProcess=new PlayerPasswordProcess();
			
			
			if(!(sender instanceof Player)){
				FastLogin.getLoggerr().logWarning("·控制台不能使用此类命令!");
				return;
			}
			
			Player pl=(Player) sender;
			
			
			//输入错误命令时发送帮助文档
			if(args.length < 3){
				pl.sendMessage(ConfigManager.getLanguage().getChangePwUsage());
				return;
			}
						
			pwdProcess.ChangePasswd(pl, args[0], args[1], args[2]);			
		
	}

}
