package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface FastCommand {

	
	String commandName="ExampleCommand";
	
	
	
	public void executeCommand(CommandSender sender,Command cmd,String[] args);
	
	
}
