package com.ksptooi.FL.BukkitSupport;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import com.ksptooi.FL.Command.BukkitCommandHandler;
import com.ksptooi.FL.Command.CommandManager;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;
import com.ksptooi.FL.Data.PlayerData.PlayerSqlDataManager;
import com.ksptooi.FL.Event.FastEvent.EventManager;
import com.ksptooi.FL.Event.PlayerEvent.PlayerEventHandler;
import com.ksptooi.FL.General.Init.FastLogin_init;
import com.ksptooi.FL.PAsync.Task.AsyncTask;
import com.ksptooi.FL.Player.Effect.PlayerEffectManager;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.Logger;

public class FastLogin extends JavaPlugin {

	BukkitCommandHandler bukkitCommandHandler=null;
	
	private static CommandManager commandManager=null;
	
	private static Logger log=new Logger();
	
	private static HashMap<String,FastPlayer> listOnlinePlayer = null;
	
	private static AsyncTask asyncTask = null;
		
	private static EventManager eventManager = null;
	
	private static PlayerEffectManager playerEffectManager = null;
	
	private static final String Version="0.45-B-R1";
	
	public FastLogin(){
				
		commandManager=new CommandManager();
		bukkitCommandHandler=new BukkitCommandHandler();
		listOnlinePlayer = new HashMap<String,FastPlayer>();
		asyncTask = new AsyncTask();
		eventManager = new EventManager();
		playerEffectManager = new PlayerEffectManager();
	}
	
	public void onEnable(){
		
		System.out.println("[FastLogin]版本:"+FastLogin.getVersion());
		
		System.out.println("[FastLogin]·:测试版");
		
		FastLogin_init.init();
		
		FUtil.MainClass=this;
		
		Bukkit.getPluginManager().registerEvents(new PlayerEventHandler(), this);
		
		
		//判断是否使用Mysql
		if(ConfigManager.getConfig().getPlayerDataType().equalsIgnoreCase("mysql")){
			
			FUtil.playerSqlDataBLL=new PlayerSqlDataManager();
			
		}	
		
	}
	
	
	public static CommandManager getCommandManager() {
		return commandManager;
	}
	
	public static Logger getLoggerr(){
		return log;
	}
	
	
	//在线玩家列表操作
	public static void addOnlinePlayer(String pname,FastPlayer pl) {
		listOnlinePlayer.put(pname.toLowerCase(), pl);
	}
	
	public static void removeOnlinePlayer(String pname) {
		listOnlinePlayer.remove(pname.toLowerCase());
	}
	
	
	public static FastPlayer getPlayer(String playerName) {
		return listOnlinePlayer.get(playerName.toLowerCase());
	}
	
	
	public static AsyncTask getAsyncTask() {
		return asyncTask;
	}
	
	
	public static EventManager getEventManager() {
		return eventManager;
	}
	
	public static PlayerEffectManager getPlayerEffectManager() {
		return playerEffectManager;
	}
	
	public static String getVersion() {
		return Version;
	}
	
	
	//进行命令传递
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){							
		return bukkitCommandHandler.onCommand(sender, cmd, label, args);
	}
	
}
