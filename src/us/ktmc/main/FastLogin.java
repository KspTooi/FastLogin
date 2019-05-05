package us.ktmc.main;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;
import us.ktmc.file.OldConfUpdata;
import us.ktmc.player.PlayerEventHandler;
import us.ktmc.util.Var;
import us.ktmc.util_interface.FastUtil;

public class FastLogin extends JavaPlugin {
	
	CommandHandler Command=new CommandHandler();
	
	public void onEnable(){
		
		System.out.println("[FastLogin]版本:"+FastUtil.FastLoginVersion);
		System.out.println("[FastLogin]·检查配置项");	
						
		
		/**更新旧配置项**/
		new OldConfUpdata().AutoUpdataOldConf();

		/**配置初始化**/
		Var.conf.ConfigCheck();
		Var.conf.ReaderConfig(); 	
		Var.conf.IPtablesCheck();
	
		/**语言文件初始化**/
		Var.conf.LanguageCheck();
			
		try{
			
			Var.conf.ReaderLanuage();
			
		}catch(NullPointerException ec){
			System.out.println("[严重]·读取语言文件时出现错误,将使用预设语言");
		}
		
		/**初始化完成!**/
				
		Var.MainClass=this;
		
		//检查数据储存介质
		if(!Var.PlayerDataType.equalsIgnoreCase("GeneralDataCore")&!Var.PlayerDataType.equalsIgnoreCase("Mysql")){
			System.out.println("[FastLogin][严重]·数据储存介质不存在:"+Var.PlayerDataType);
			System.out.println("[FastLogin][严重]·可选项有:GeneralDataCore与Mysql");
		}
		
		//如果数据储存介质是Mysql则连接Mysql
		if(Var.PlayerDataType.equalsIgnoreCase("Mysql")){
			
			System.out.println("[FastLogin]·初始化数据库中.");
			
			Var.V4.ReadSQLConfigFromFile(FastUtil.Mconf);
			Var.SQLConn=Var.V4.getSQLConnect();
					
			//判断连接是否成功
			if(Var.SQLConn==null){
				System.out.println("[FastLogin][严重]·数据库连接错误!");
				Bukkit.getServer().shutdown();
			}
		
			System.out.println("[FastLogin]·数据库连接成功！");
			
			//检查表
			Var.SQL.CreateDataTable();
			Var.SQL.CreateLocTable();
			
			Var.isMysql=true;
			
		}
		
			
		
		Bukkit.getPluginManager().registerEvents(new PlayerEventHandler(),this);
		
		
	}
	
	//进行命令传递
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){							
		return Command.onCommand(sender, cmd, label, args);
	}
	
	
}
