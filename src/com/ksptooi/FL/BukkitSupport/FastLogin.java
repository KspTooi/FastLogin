package com.ksptooi.FL.BukkitSupport;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import com.ksptooi.FL.Command.CommandHandler;
import com.ksptooi.FL.LogFitter.PlayerPasswordLogFitter;
import com.ksptooi.FL.PluginConf.ConfigReader;
import com.ksptooi.FL.PluginConf.ConfigUpdate;
import com.ksptooi.FL.PluginConf.ConfigWriter;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.playerEvent.PlayerEventHandler;
import com.ksptooi.gdc.FileAPI.IOController_V5;
import com.ksptooi.playerData_BLL.PlayerSqlDataBLL;

public class FastLogin extends JavaPlugin {

	ConfigReader PCCR=null;
	ConfigWriter PCCW=null;
	ConfigUpdate PCCU=null;
	CommandHandler CH=null;
	IOController_V5 v5=null;
	PlayerPasswordLogFitter PPLF = null;
	
	public FastLogin(){
		
		
		PCCR=new ConfigReader();
		PCCW=new ConfigWriter();
		PCCU=new ConfigUpdate();
		CH=new CommandHandler();	
		PPLF = new PlayerPasswordLogFitter();
	}
	
	public void onEnable(){
		
		System.out.println("[FastLogin]版本:"+FUtil.Version);
		

		
		/**配置检查**/
		PCCW.createConfig();
		PCCW.createLocationConfig();
		PCCW.createLanguageConfig();
		PCCW.createIPCountConfig();
		
		/**配置读取**/
		FUtil.config=PCCR.readerConfig();
		FUtil.defaultLocationEntity=PCCR.readerLocationConfig();
		FUtil.language=PCCR.readerLanguageConfig();
		
		/**配置版本检查**/
		PCCU.checkAndUpdateOfConfig();
		
		FUtil.MainClass=this;
		
		Bukkit.getPluginManager().registerEvents(new PlayerEventHandler(), this);
		
		
		//判断是否使用Mysql
		if(FUtil.config.getPlayerDataType().equalsIgnoreCase("mysql")){
			
			FUtil.playerSqlDataBLL=new PlayerSqlDataBLL();
			
		}
		
		
		
	}
	
	//进行命令传递
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){							
		return CH.onCommand(sender, cmd, label, args);
	}
	

	
	
}
