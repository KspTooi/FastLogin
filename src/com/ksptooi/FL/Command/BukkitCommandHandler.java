package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Hash.PasswordHash;
import com.ksptooi.FL.Util.Logger;


public class BukkitCommandHandler{

	
	Player pl=null;
	Logger lm=null;

	PasswordHash APH=null;
	
	CommandManager commandManager = null;
	
	
	public BukkitCommandHandler(){
		
		lm=new Logger();
		APH = new PasswordHash();	
		commandManager = FastLogin.getCommandManager();
		
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
			
		
		commandManager.execute(cmd.getName(), sender, cmd, args);
		
		
		if(cmd.getName().equalsIgnoreCase("fast")){

			
			//权限判断
			if(!(sender.isOp())){
			    sender.sendMessage("只有OP才能使用此命令");
			    return false;
			}
				
			try {

				boolean flag = true;

				flag = commandManager.execute(cmd.getName() + " " + args[0], sender, cmd, args);

				
				if (!flag) {

					lm.SendOPHelp(sender);
				}
				

			} catch (Exception e) {
				lm.SendOPHelp(sender);
			}

			
			
		}
		
		
		return true;		
	}
	

	
	
}
