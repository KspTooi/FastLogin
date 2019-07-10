package com.ksptooi.FL.Data.Config;

import java.io.File;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.Entity.DefaultLocationEntity;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Util.Logger;
import com.ksptooi.gdc.v6.Factory.DataSessionFactory;
import com.ksptooi.gdc.v6.Session.dataSession;


public class ConfigWriter {

	Logger logManager=null;
	
	DataSessionFactory dataSessionFactory = DataManager.getDataSessionFactory();
	
	public ConfigWriter(){
		this.logManager=new Logger();
	}
	
	
	/**
	 * 检查并创建配置文件
	 */
	public boolean createConfig(){
			
		File config = ConfigManager.fastLoginConfigFile;
		
		if(dataSessionFactory.createdata(config) == true) {
			
			logManager.logInfo("写入默认配置项");
			
			dataSession ds = dataSessionFactory.openSession(config);
			
			ds.addline(" ");			
			ds.addline("#基本配置\r\n" + 
					"ConfigVersion="+FastLogin.getVersion()+"\r\n" + 
					"PlayerDataType=GeneralDataCore\r\n" + 
					"LoginTimeOut=60\r\n" + 
					"PasswordMaxLength=15\r\n" + 
					"PasswordMinLength=5\r\n" + 
					"PlayerNameMinLength=3\r\n" + 
					"MessageInterval=5\r\n" + 
					"LoginSecurityTime=3\r\n" + 
					"Enable_LoginSecurity=false\r\n" + 
					"Enable_UserNameStrictmode=true\r\n" + 
					"BanName=*;\r\n" + 
					"MaxRegisterIP=0\r\n" + 
					"RegexMatchForPlayerName=*\r\n" + 
					"\r\n" + 
					"#特效及自定义配置\r\n" + 
					"PlayerLoginedMessage=false\r\n" + 
					"PlayerJoinedMessage=§e%Player% Joined the game.\r\n" + 
					"PlayerQuitMessage=§e%Player% Left the game.\r\n" + 
					"Enable_PlayerLoginedEffect=true\r\n" + 
					"Enable_PlayerPreLoginEffect=true\r\n" + 
					"\r\n" + 
					"#安全配置\r\n" + 
					"Enable_OPSecurity=true\r\n" + 
					"Enable_HellGateSecurity=true\r\n" + 
					"Enable_CreativeModeSecurity=true\r\n" + 
					"Enable_passwordHash=MD5\r\n" + 
					"Enable_SupportOldPassword=false\r\n" + 
					"Enable_DebugPrint=false\r\n" + 
					"\r\n" + 
					"GeneralDataCore - Mysql数据库配置\r\n" + 
					"address=127.0.0.1:3306\r\n" + 
					"database=asmc\r\n" + 
					"username=root\r\n" + 
					"password=h14R4g5Rf6H5h7f\r\n" + 
					"poolinitSize=32\r\n" + 
					"param=?useSSL=false&characterEncoding=utf8&serverTimezone=UTC&autoReconnect=true\r\n" + 
					"\r\n" + 
					"FastLogin - Mysql数据库配置 #玩家数据表\r\n" + 
					"PlayerDataTable=playertable\r\n" + 
					"PlayerNameField=playername\r\n" + 
					"PlayerPwdField=playerpwd\r\n" + 
					"PlayerRegStatusField=register\r\n" + 
					"PlayerLoginStatusField=login\r\n" + 
					"\r\n" + 
					"FastLogin - Mysql数据库配置 #玩家位置数据表(!不建议修改)\r\n" + 
					"PlayerLocTable=playerloc\r\n" + 
					"PlayerNameField=Foreign Key\r\n" + 
					"PlayerLocworld=locworld\r\n" + 
					"PlayerLocx=locx\r\n" + 
					"PlayerLocy=locy\r\n" + 
					"PlayerLocz=locz\r\n" + 
					"PlayerLocpitch=locpitch\r\n" + 
					"PlayerLocyaw=locyaw");
			
			
			ds.release();
			
			return true;
			
		}
		
		
		return false;
		
		
		
	}
	
	
	/**
	 * 检查并创建位置文件
	 */
	public boolean createLocationConfig(){
		
		File location = ConfigManager.fastLoginLocationFile;

		
		if(dataSessionFactory.createdata(location)==true) {
			
			logManager.logInfo("写入默认Location配置项");
			
			dataSession ds = dataSessionFactory.openSession(location);
			
			ds.addline("location.world=empty");
			ds.addline("location.x=empty");
			ds.addline("location.y=empty");
			ds.addline("location.z=empty");
			ds.addline("location.pitch=empty");
			ds.addline("location.yaw=empty");
			
			ds.release();
			
			return true;
			
		}
		
		
		return false;
		
		
		
	
		
	}
	
	/**
	 * 检查并创建IPCount文件
	 */
	public void createIPCountConfig(){
		
		File ipcount = ConfigManager.fastLoginIPCountFile;
		
		if(dataSessionFactory.createdata(ipcount)==true) {
			
			logManager.logInfo("写入默认IPCount配置项");
			
		}
		
		
		
	}
	
	/**
	 * 检查并创建语言文件
	 */
	public void createLanguageConfig(){
		
		File lang = ConfigManager.fastLoginLanguageFile;
		
		if(dataSessionFactory.createdata(lang) == true){
			
			dataSession ds = dataSessionFactory.openSession(lang);
			
			logManager.logInfo("写入默认语言配置项");
			
			
			ds.addline("Notlogin=§e[FastLogin]§b你需要登录后才能操作,使用/login 密码 来登录.");
			ds.addline("NotRegister=§e[FastLogin]§b你需要注册后才能操作,使用/register 密码 确认密码 来注册.");
			ds.addline("LoginTimeOutKick=登录超时");
			ds.addline("RepeatLogin=§e[FastLogin]§c你已经登录了!");
			ds.addline("RepeatRegister=§e[FastLogin]§c你已经注册了!");
			ds.addline("NotRegister2=§e[FastLogin]§c你还没有注册！");
			ds.addline("LoginSuccess=§e[FastLogin]§a登录成功！");
			ds.addline("PasswordError=§e[FastLogin]§c密码错误！");
			ds.addline("RegisterSuccess=§e[FastLogin]§a注册完成！");
			ds.addline("NullPassword=§e[FastLogin]§c请输入密码！");
			ds.addline("PasswdTooLong=§e[FastLogin]§c你输入的密码长度超过最大限制！");
			ds.addline("PasswdTooShost=§e[FastLogin]§c你输入的密码太短！");
			ds.addline("NoConfirmPasswd=§e[FastLogin]§c请输入确认密码 例:/register 12345 12345");
			ds.addline("ConfirmPasswdError=§e[FastLogin]§c两次输入的密码不一致");
			ds.addline("JoinGameError1=登录失败:相同用户名的玩家已经在游戏中!");		
			ds.addline("ChangePwUsage=§e[FastLogin]§b修改密码 - 用法:/ChangePassword 旧密码 新密码 确认新密码");			
			ds.addline("ChangePw_OldpwErr=§e[FastLogin]§c旧密码错误.");		
			ds.addline("ChangePw_ConfirmPwError=§e[FastLogin]§c两次输入的确认密码不一致.");				
			ds.addline("ReModifyPasswd=§e[FastLogin]§c新密码不能和旧密码一样.");				
			ds.addline("ChangePw_PasswdTooLong=§e[FastLogin]§c新密码超过长度限制.");		
			ds.addline("ChangePw_PasswdTooShost=§e[FastLogin]§c新密码太短.");				
			ds.addline("ChangePw_Success=§e[FastLogin]§a修改密码 - 完成!");		
			ds.addline("LoginOut=§e[FastLogin]§c·登录已经被注销!");	
				
			
			//new V1
			
			ds.addline("AdminSetPasswordError1=§e[FastLogin]§c此玩家未注册,修改密码失败!");
			
			ds.addline("AdminSetPasswordUsage=§e[FastLogin]§b使用方法:/Fast SetPassword 玩家名 新密码");
			
			ds.addline("AdminSetPasswordSuccess=§e[FastLogin]§a成功修改此玩家密码!");
			
			
			ds.addline("AdminSetPasswordKick=密码被管理员修改!");
			
			ds.addline("AdminCommandHelp1=§b[FastLogin]·OP命令用法:");
			ds.addline("AdminCommandHelp2=§6/Fast Reload§f: 重载插件的配置文件");
			ds.addline("AdminCommandHelp3=§6/Fast setSpawn§f: 设置初始登录地点");
			ds.addline("AdminCommandHelp4=§6/Fast Spawn§f: 传送到初始登录地点");
			ds.addline("AdminCommandHelp5=§6/Fast delSpawn§f: 删除初始登录地点");
			ds.addline("AdminCommandHelp6=§6/Fast setPassword§f: 设置玩家的密码");
			
			ds.addline("OPHasbeenCanceld=§e[FastLoginSecurity]§c·由于你未登录,你的OP权限已被取消!");
			ds.addline("CreativeModeHasbeenCanceld=§e[FastLoginSecurity]§c·由于你未登录,你的创造模式已被取消!");
			
			ds.addline("OPRestore=§e[FastLoginSecurity]§a·你的OP权限已经恢复!");
			
			ds.addline("CreativeModeRestore=§e[FastLoginSecurity]§a·你的创造模式已经恢复!");
			
			ds.addline("setSpawnSuccess=§e[FastLogin]§a·初始登录点已设置!");
			
			ds.addline("DeleteSpawnSuccess=§e[FastLogin]§a·初始登录点已删除!");
			
			ds.addline("TPSpawnSuccess=§e[FastLogin]§6·正在传送...");
			
			ds.addline("OPHasBeenCleared=§e[FastLoginSecurity]§c·由于你没有注册,你的OP已被清除..");
			
			ds.addline("CreativeHasBeenCleared=§e[FastLoginSecurity]§c·由于你没有注册,你的OP已被清除..");
			
			ds.release();
			
		}
		
		
		
	}
	
	/**
	 * 更新位置文件
	 */
	public void updateLocationConfig(DefaultLocationEntity dle){
		
		
		File location = ConfigManager.fastLoginLocationFile;
		
		
		dataSession ds = dataSessionFactory.openSession(location);
		
		ds.set("location.world", dle.getLocation_world());
		ds.set("location.x", dle.getLocation_x());
		ds.set("location.y", dle.getLocation_y());
		ds.set("location.z", dle.getLocation_z());
		ds.set("location.pitch", dle.getLocation_pitch());
		ds.set("location.yaw", dle.getLocation_yaw());
		
		ds.release();

	}
	
	
	

	

	
}
