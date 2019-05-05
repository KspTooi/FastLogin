package com.ksptooi.FL.Util;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class LogManager{
	
	public void writerInfo(String info){
		System.out.println("[FastLogin]:"+info);
	}
	
	public void writerWarning(String warning){
		System.out.println("[FastLogin]警告:"+warning);
	}
	
	public void writerError(String error){
		System.out.println("[FastLogin]严重:"+error);
	}
	
	
	/**
	 * 用于获取玩家加入信息
	 */
	public String GenJoinedMessage(Player pl) {

		String str=FUtil.config.getPlayerJoinedMessage();
		
		//生成消息
		str=str.replace("%Player%", pl.getName());
		
		str=str.replace("&", "§");
		
		str=str.replace("#", "\n");
		
		
		return str;
	}


	/**
	 * 用于获取玩家退出信息
	 */
	public String GenQuitMessage(Player pl) {

		String str=FUtil.config.getPlayerQuitMessage();
		
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
		
		sender.sendMessage(FUtil.language.getAdminCommandHelp1());
		sender.sendMessage(FUtil.language.getAdminCommandHelp2());
		sender.sendMessage(FUtil.language.getAdminCommandHelp3());
		sender.sendMessage(FUtil.language.getAdminCommandHelp4());
		sender.sendMessage(FUtil.language.getAdminCommandHelp5());
		sender.sendMessage(FUtil.language.getAdminCommandHelp6());
		
	}
	
	/**
	 * 用于玩家登录后的消息控制
	 */
	public void ShowMessage(Player pl) {
		
		
		String ShowMessage="";
		
		//判断是否启用了登录消息
		if(FUtil.config.getPlayerLoginedMessage().equalsIgnoreCase("false")){
			return;
		}
		
		//生成登录消息
		ShowMessage=FUtil.config.getPlayerLoginedMessage();
		
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
		
		if(! FUtil.config.isEnable_DebugPrint()){
			return;
		}
		
		System.out.println("[FastLogin]·调试:"+Message);
			
	}
	
}
