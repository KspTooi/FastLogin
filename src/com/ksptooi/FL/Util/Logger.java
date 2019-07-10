package com.ksptooi.FL.Util;

import org.bukkit.command.CommandSender;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;


public class Logger{
	
	public void logInfo(String info){
		System.out.println("[FastLogin]:"+info);
	}
	
	public void logWarning(String warning){
		System.out.println("[FastLogin]警告:"+warning);
	}
	
	public void logError(String error){
		System.out.println("[FastLogin]严重:"+error);
	}
	
	
	/**
	 * 用于获取玩家加入信息
	 */
	public String GenJoinedMessage(FastPlayer pl) {

		String str=ConfigManager.getConfig().getPlayerJoinedMessage();
		
		//生成消息
		str=str.replace("%Player%", pl.getName());
		
		str=str.replace("&", "§");
		
		str=str.replace("#", "\n");
		
		
		return str;
	}


	/**
	 * 用于获取玩家退出信息
	 */
	public String GenQuitMessage(FastPlayer pl) {

		String str=ConfigManager.getConfig().getPlayerQuitMessage();
		
		//生成消息
		str=str.replace("%Player%", pl.getName());
		
		str=str.replace("&", "§");
		
		str=str.replace("#", "\n");
		
		return str;
	}
	
	
	/**
	 * 发送一个OP命令帮助至控制台
	 * @param 控制台实例
	 */
	public void SendOPHelp(CommandSender sender){
		
		sender.sendMessage(ConfigManager.getLanguage().getAdminCommandHelp1());
		sender.sendMessage(ConfigManager.getLanguage().getAdminCommandHelp2());
		sender.sendMessage(ConfigManager.getLanguage().getAdminCommandHelp3());
		sender.sendMessage(ConfigManager.getLanguage().getAdminCommandHelp4());
		sender.sendMessage(ConfigManager.getLanguage().getAdminCommandHelp5());
		sender.sendMessage(ConfigManager.getLanguage().getAdminCommandHelp6());
		
	}
	
	/**
	 * 用于玩家登录后的消息控制
	 */
	public void ShowMessage(FastPlayer pl) {
		
		
		String ShowMessage="";
		
		//判断是否启用了登录消息
		if(ConfigManager.getConfig().getPlayerLoginedMessage().equalsIgnoreCase("false")){
			return;
		}
		
		//生成登录消息
		ShowMessage=ConfigManager.getConfig().getPlayerLoginedMessage();
		
		ShowMessage=ShowMessage.replace("%Player%", pl.getName());
		
		ShowMessage=ShowMessage.replace("&", "§");
		
		ShowMessage=ShowMessage.replace("#", "\n");
		
		
		//发送登录消息
		pl.sendMessage(ShowMessage);
		
	}
	
	/**
	 * 用于输出调试信息
	 */
	public void DM(String Message){
		debugMessage(Message);
	}
	
	public void debugMessage(String Message){
		
		if(! ConfigManager.getConfig().isEnable_DebugPrint()){
			return;
		}
		
		System.out.println("[FastLogin]·调试:"+Message);
			
	}
	
}
