package com.ksptooi.FL.Command;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandManager {

	
	private HashMap<String,FastCommand> listCommand =new HashMap<String,FastCommand>();
	
	
	
	public void regCommand(String key,FastCommand command) {
		this.listCommand.put(key, command);
	}
	
	
	
	
	public boolean execute(String commandName,CommandSender sender,Command cmd,String[] args) {
		
		
		FastCommand fc=this.listCommand.get(commandName.toLowerCase());
		
		//如果没有这个命令则返回false
		if(fc == null) {
			return false;
		}
		
		fc.executeCommand(sender, cmd, args);
		return true;
		
	}
	
	
	
	
	
	
}
