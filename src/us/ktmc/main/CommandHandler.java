package us.ktmc.main;

import java.io.IOException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.ktmc.Exception.PasswdRulesException;
import us.ktmc.Exception.PlayerNotRegisterException;
import us.ktmc.player.PlayerLoginThread;
import us.ktmc.player.PlayerRegThread;
import us.ktmc.util.Var;
import us.ktmc.util.Language;
import us.ktmc.util_interface.FastUtil;
import us.ktmc.util_interface.GeneralUtil;

public class CommandHandler{

	FastUtil Util=new GeneralUtil();
	Player pl=null;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
			
		
		if(sender instanceof Player){
			
			pl=(Player)sender;
			
		}

		/**玩家登陆(!ConsoleExecute)**/
		if(cmd.getName().equalsIgnoreCase("login")|cmd.getName().equalsIgnoreCase("l")){
			
			
			if(Util.PlayerCommandExecuteAtConsole(sender))
				return false;
			
			
			new Thread(new PlayerLoginThread(pl,args)).start();
															
		}
		
		/**玩家注册 - 开始**/
		if(cmd.getName().equalsIgnoreCase("register")|cmd.getName().equalsIgnoreCase("reg")){
			
			if(Util.PlayerCommandExecuteAtConsole(sender))
				return false;			
			
			new Thread(new PlayerRegThread(pl,args)).start();
			
			
		}
		/**玩家注册 - 结束**/
		
		
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
						
				Var.conf.ConfigCheck();
				Var.conf.ReaderConfig(); 			
				Var.conf.LanguageCheck();
				Var.conf.IPtablesCheck();
				try{
					
					Var.conf.ReaderLanuage();
					
				}catch(NullPointerException ec){
					
					ec.printStackTrace();
					System.out.println("[FastLogin]·读取语言文件时出现错误,将使用预设语言");
					
				}
				
				sender.sendMessage("§c[FastLogin]·重新载入配置文件");										
				return true;
			}
			/**配置文件重载 - 结束**/
			
			
			/**设置默认登陆位置 - 开始**/
			if(args[0].equalsIgnoreCase("set")){
				
				if(Util.PlayerCommandExecuteAtConsole(sender)){
					return true;
				}
				
				Util.SetDefaultLoginLocation(pl.getLocation().getWorld().getName(),(int)pl.getLocation().getX(), (int)pl.getLocation().getY(), (int)pl.getLocation().getZ(),(int)pl.getLocation().getPitch(),(int)pl.getLocation().getYaw());
				
				Var.conf.ConfigCheck();
				Var.conf.ReaderConfig(); 	
				sender.sendMessage("§e[FastLogin]·登录位置已设置！");		
				return true;
				
			}
			/**设置默认登陆位置 - 结束**/
			
			
			/**T.P - Start**/
			if(args[0].equalsIgnoreCase("go")){
				
				if(Util.PlayerCommandExecuteAtConsole(sender))			
					return false;
				
				
				if(Var.Location_world.equalsIgnoreCase("empty")){
					sender.sendMessage("§c没有找到一个登录位置,如果你已经设置,请尝试重载插件.");
					return true;
				}			
				
				pl.teleport(Var.LoginLocation);
				return true;
			}
			/**T.P - End**/
			
			/**删除默认登陆位置 - 开始**/
			if(args[0].equalsIgnoreCase("del")){
				
				if(Util.PlayerCommandExecuteAtConsole(sender))			
					return false;
				
				if(Var.Location_world.equalsIgnoreCase("empty")){
					sender.sendMessage("§c没有找到一个登录位置,如果你已经设置,请尝试重载插件.");
					return false;
				}		
				
				if(Util.DeleteDefaultLoginLocation()){
					
					Var.conf.ConfigCheck();
					Var.conf.ReaderConfig(); 	
					sender.sendMessage("§c[FastLogin]·登录位置已删除！");
					return true;
				}
				
				sender.sendMessage(Util.getFileErrorText());
				return false;
					
			}
			/**删除默认登陆位置 - 结束**/
			
			
			/**重置玩家密码 - 开始**/
			if(args[0].equalsIgnoreCase("reset")){
				
				if(!(sender.isOp())){
				    sender.sendMessage("只有OP才能使用此命令");
				    return true;
				}
				
				if(args.length<3){
					sender.sendMessage("使用方法:/Fast Reset 玩家名 新密码");
					return true;
				}
				
				
				try{
				
					Util.ReSetPasswd(args[1], args[2]);
					
				}catch(PlayerNotRegisterException e){
				
					sender.sendMessage("§c[FastLogin]·此玩家未注册,修改密码失败！");
					return false;
					
				}catch(PasswdRulesException e){
					
					if(e.isTooLong()){
						sender.sendMessage(Language.PassMax);
					}
					
					if(e.isTooShort()){
						sender.sendMessage(Language.PassMin);
					}
					
					return false;
					
				}catch(IOException e){
					
					e.printStackTrace();
					sender.sendMessage(Util.getFileErrorText());
					return false;
					
				}

				
				sender.sendMessage("§6密码修改成功！");
				return true;
				
			}
			
			/**重置玩家密码 - 结束**/
		
			
			Util.SendOPHelp(sender);

			}catch(Exception e){
				
				Util.SendOPHelp(sender);
				
			}
			
			
		}
		
		//修改密码命令
		if(cmd.getName().equalsIgnoreCase("ModifyPasswd")){
			
			if(Util.PlayerCommandExecuteAtConsole(sender)){
				return false;
			}
			
			//输入错误命令时发送帮助文档
			if(args.length < 3){
				pl.sendMessage(Language.ModifyPwUsage);
				return true;
			}
			
			Util.ChangePasswd(pl, args[0], args[1], args[2]);
		
				
		}
		//修改密码 - 结束
		
		
		return false;		
	}
	
	
	
	
}
