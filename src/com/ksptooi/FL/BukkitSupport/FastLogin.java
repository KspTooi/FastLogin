package com.ksptooi.FL.BukkitSupport;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.ksptooi.FL.Command.BukkitCommandHandler;
import com.ksptooi.FL.Command.CommandManager;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Data.PlayerData.PlayerSqlDataManager;
import com.ksptooi.FL.Event.FastEvent.EventManager;
import com.ksptooi.FL.Event.PlayerEvent.PlayerEventHandler;
import com.ksptooi.FL.General.Init.FastLogin_init;
import com.ksptooi.FL.Player.Async.PlayerAsyncProcess;
import com.ksptooi.FL.Player.Effect.PlayerEffectManager;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.Logger;

public class FastLogin extends JavaPlugin {

	BukkitCommandHandler bukkitCommandHandler=null;
	
	private static CommandManager commandManager=null;
	
	private static Logger log=new Logger();
	
	private static PlayerDataManager playerDataManager =null;
	
	private static HashMap<String,Player> listOnlinePlayer = null;
	
	private static PlayerAsyncProcess playerAsyncProcess = null;
		
	private static EventManager eventManager = null;
	
	private static PlayerEffectManager playerEffectManager = null;
	
	public FastLogin(){
				
		commandManager=new CommandManager();
		bukkitCommandHandler=new BukkitCommandHandler();
		playerDataManager = new PlayerDataManager();
		listOnlinePlayer = new HashMap<String,Player>();
		playerAsyncProcess = new PlayerAsyncProcess();
		eventManager = new EventManager();
		playerEffectManager = new PlayerEffectManager();
	}
	
	public void onEnable(){
		
		System.out.println("[FastLogin]版本:"+FUtil.Version);
		
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
	
	public static PlayerDataManager getPlayerDataManager(){
		return playerDataManager;
	}
	
	
	//在线玩家列表操作
	public static void addOnlinePlayer(String pname,Player pl) {
		listOnlinePlayer.put(pname, pl);
	}
	
	public static void removeOnlinePlayer(String pname) {
		listOnlinePlayer.remove(pname);
	}
	
	
	public static Player getPlayer(String playerName) {
		return listOnlinePlayer.get(playerName);
	}
	
	
	public static PlayerAsyncProcess getAsyncProcess() {
		return playerAsyncProcess;
	}
	
	
	public static EventManager getEventManager() {
		return eventManager;
	}
	
	public static PlayerEffectManager getPlayerEffectManager() {
		return playerEffectManager;
	}
	
	//进行命令传递
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){							
		return bukkitCommandHandler.onCommand(sender, cmd, label, args);
	}
	
}
