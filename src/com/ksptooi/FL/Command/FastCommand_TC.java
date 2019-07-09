package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.ksptooi.FL.Performance.PerformanceMonitorManager;

public class FastCommand_TC implements FastCommand{

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
		
		
		sender.sendMessage("----FastLogin 性能监测----");
		sender.sendMessage("累计IO:"+PerformanceMonitorManager.getPFPC());
		sender.sendMessage("已缓存:"+PerformanceMonitorManager.getPDECCount());
		sender.sendMessage("活动线程:"+PerformanceMonitorManager.getPATC());
		sender.sendMessage("-------------------------");
		
		
	}

}
