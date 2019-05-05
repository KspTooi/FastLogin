package com.ksptooi.FL.Command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.ksptooi.FL.Entity.DefaultLocationEntity;
import com.ksptooi.FL.Entity.FastLoginConfigEntity;
import com.ksptooi.FL.Entity.FastLoginLanguageEntity;
import com.ksptooi.FL.Entity.PlayerDataEntity;
import com.ksptooi.FL.PlayerProcess.PlayerPasswordProcess;
import com.ksptooi.FL.PluginConf.ConfigReader;
import com.ksptooi.FL.PluginConf.ConfigWriter;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.LogManager;
import com.ksptooi.playerData_BLL.PlayerDataBLL_Interface;
import com.ksptooi.playerData_BLL.PlayerDataBLLimpl;
import com.ksptooi.security.AdvPasswordHash;


public class CommandHandler{

	
	Player pl=null;
	LogManager lm=null;
	PlayerDataBLL_Interface playerDataBLL=null;
	ConfigReader FLCR=null;
	ConfigWriter FLCW=null;
	PlayerPasswordProcess pwdProcess=null;
	AdvPasswordHash APH=null;
	
	public CommandHandler(){
		lm=new LogManager();
		playerDataBLL=new PlayerDataBLLimpl();
		FLCR=new ConfigReader();
		FLCW=new ConfigWriter();
		pwdProcess=new PlayerPasswordProcess();
		APH = new AdvPasswordHash();
	}
	
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		
		if(sender instanceof Player){			
			pl=(Player)sender;		
		}
		

//		//登录命令
//		if(cmd.getName().equalsIgnoreCase("login")|cmd.getName().equalsIgnoreCase("l")){
//			
//			
//			if(!(sender instanceof Player)){
//				lm.writerWarning("·控制台不能使用此类命令!");
//				return false;
//			}
//			
//			new Thread(new PlayerLoginThread(pl,args)).start();
//															
//		}
//		
//		//注册命令
//		if(cmd.getName().equalsIgnoreCase("register")|cmd.getName().equalsIgnoreCase("reg")){
//			
//			if(!(sender instanceof Player)){
//				lm.writerWarning("·控制台不能使用此类命令!");
//				return false;
//			}		
//			
//			new Thread(new PlayerRegThread(pl,args)).start();
//			
//		}
		
		
		//OP命令(Reload=重载文件,set=设置默认登陆位置,go=传送到登陆位置,del=删除登陆位置,reset=更改玩家密码)
		if(cmd.getName().equalsIgnoreCase("fast")){
			
			
			if(sender instanceof Player){	
				
				if(!(pl.isOp())){
					
					pl.sendMessage("§c[FastLogin]·需要OP才可以使用此命令");
					return true;
					
				}			
			}
			
				
		try{
				
			/**配置文件重载 - 开始**/
			if(args[0].equalsIgnoreCase("reload")){
						
				FastLoginConfigEntity FLCE = FLCR.readerConfig();
				FastLoginLanguageEntity FLLE = FLCR.readerLanguageConfig();
				DefaultLocationEntity DLE = FLCR.readerLocationConfig();
				
				FUtil.config=FLCE;
				FUtil.language=FLLE;
				FUtil.defaultLocationEntity=DLE;
				
				
				sender.sendMessage("§c[FastLogin]·重新载入配置文件");										
				return true;
			}
			
			
			/**设置默认登陆位置 - 开始**/
			if(args[0].equalsIgnoreCase("setSpawn")){
				
				if(!(sender instanceof Player)){
					lm.writerWarning("·控制台不能使用此类命令!");
					return false;
				}
				
				DefaultLocationEntity DLE = FUtil.defaultLocationEntity;
				
				DLE.setLoginLocation(pl.getLocation());
				
				FLCW.updateLocationConfig(DLE);
				
				FUtil.defaultLocationEntity=DLE;
				
				sender.sendMessage(FUtil.language.getSetSpawnSuccess());		
				return true;
				
			}
			
			
			//传送到登录位置
			if(args[0].equalsIgnoreCase("Spawn")){
				
				if(!(sender instanceof Player)){
					lm.writerWarning("·控制台不能使用此类命令!");
					return false;
				}
				
				
				if(FUtil.defaultLocationEntity.getLocation_world().equalsIgnoreCase("empty")){
					sender.sendMessage("§c没有找到一个登录位置,如果你已经设置,请尝试重载插件.");
					return true;
				}			
				
				sender.sendMessage(FUtil.language.getTPSpawnSuccess());
				pl.teleport(FUtil.defaultLocationEntity.getLoginLocation());
				return true;
			}
			
			
			/**删除默认登陆位置 - 开始**/
			if(args[0].equalsIgnoreCase("delSpawn")){
				
				if(!(sender instanceof Player)){
					lm.writerWarning("·控制台不能使用此类命令!");
					return false;
				}
				
				
				if(FUtil.defaultLocationEntity.getLocation_world().equalsIgnoreCase("empty")){
					sender.sendMessage("§c没有找到一个登录位置,如果你已经设置,请尝试重载插件.");
					return true;
				}	
				
				FUtil.defaultLocationEntity.removeLoc();
				
				FLCW.updateLocationConfig(FUtil.defaultLocationEntity);
				
				
				
				sender.sendMessage(FUtil.language.getDeleteSpawnSuccess());
				return true;
					
			}

			
			
			/**重置玩家密码 - 开始**/
			if(args[0].equalsIgnoreCase("setPassword")){
				
				if(!(sender.isOp())){
				    sender.sendMessage("只有OP才能使用此命令");
				    return true;
				}
				
				if(args.length<3){
					sender.sendMessage(FUtil.language.getAdminSetPasswordUsage());
					return true;
				}
				
				PlayerDataEntity PDE = playerDataBLL.getPlayerData(args[1]);
				
				if(!PDE.isRegister()){
					sender.sendMessage(FUtil.language.getAdminSetPasswordError1());
					return false;
				}
				
				//OP修改的玩家密码 不受插件的长度约束
				PDE.setPassword(APH.autoCompression(args[2]));
				
				playerDataBLL.updatePlayerData(PDE);
				
				sender.sendMessage(FUtil.language.getAdminSetPasswordSuccess());
				
				
				try{
					Bukkit.getPlayer(PDE.getPlayername()).kickPlayer(FUtil.language.getAdminSetPasswordKick());
				}catch(Exception e){	
				}

				
				return true;
				
			}
			
			/**重置玩家密码 - 结束**/
		
			lm.SendOPHelp(sender);

			}catch(Exception e){
				
				lm.SendOPHelp(sender);
				
			}
			
			
		}
		
		//修改密码命令
		if(cmd.getName().equalsIgnoreCase("changepassword")||cmd.getName().equalsIgnoreCase("editpwd")){
			
			if(!(sender instanceof Player)){
				lm.writerWarning("·控制台不能使用此类命令!");
				return false;
			}
			
			
			//输入错误命令时发送帮助文档
			if(args.length < 3){
				pl.sendMessage(FUtil.language.getChangePwUsage());
				return true;
			}
			
			
			pwdProcess.ChangePasswd(pl, args[0], args[1], args[2]);
				
		}
		
		
		return false;		
	}
	

	
	
}
