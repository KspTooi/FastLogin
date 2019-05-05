package com.ksptooi.FL.PluginConf;

import com.ksptooi.FL.Entity.DefaultLocationEntity;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.LogManager;
import com.ksptooi.gdc.FileAPI.IOController_V5;


public class ConfigWriter {

	LogManager logManager=null;
	IOController_V5 v5=new IOController_V5();
	
	public ConfigWriter(){
		this.logManager=new LogManager();
	}
	
	
	/**
	 * 检查并创建配置文件
	 */
	public void createConfig(){
		
		logManager.writerInfo("检查配置项");
		
		if(v5.createNewGdcFile(FUtil.fastLoginConfigFile) == true){
			
			logManager.writerInfo("写入默认配置项");
			
			v5.setTarget(FUtil.fastLoginConfigFile);
			
			//写入配置文件内容
			v5.addLine("#基本配置");
			v5.addLine("ConfigVersion="+FUtil.Version);
			v5.addLine("PlayerDataType=GeneralDataCore");
			v5.addLine("LoginTimeOut=60");
			v5.addLine("PasswordMaxLength=15");
			v5.addLine("PasswordMinLength=5");
			v5.addLine("PlayerNameMinLength=3");
			v5.addLine("MessageInterval=5");
			v5.addLine("LoginSecurityTime=3");
			v5.addLine("Enable_LoginSecurity=false");
			v5.addLine("Enable_UserNameStrictmode=true");
			v5.addLine("BanName=*;");
			v5.addLine("MaxRegisterIP=0");
			v5.addLine("RegexMatchForPlayerName=*");
			
			v5.addLine("#");
			v5.addLine("#特效及自定义配置");
			v5.addLine("PlayerLoginedMessage=false");
			v5.addLine("PlayerJoinedMessage=§e%Player% Joined the game.");
			v5.addLine("PlayerQuitMessage=§e%Player% Left the game.");
			v5.addLine("Enable_PlayerLoginedEffect=true");
			v5.addLine("Enable_PlayerPreLoginEffect=true");
			
			v5.addLine("#");
			v5.addLine("#安全配置");			
			v5.addLine("Enable_OPSecurity=true");
			v5.addLine("Enable_SecurityWarning=true");
			v5.addLine("Enable_HellGateSecurity=true");
			v5.addLine("Enable_CreativeModeSecurity=true");
			v5.addLine("Enable_passwordHash=MD5");
			v5.addLine("Enable_SupportOldPassword=false");
			v5.addLine("Enable_DebugPrint=false");
	
			
			v5.addLine("#");
			v5.addLine("GeneralDataCore - Mysql数据库配置");
			v5.addLine("MysqlAddress=127.0.0.1:3306");
			v5.addLine("DataBaseName=fastlogin");
			v5.addLine("MysqlUser=root");
			v5.addLine("MysqlPwd=root");
			v5.addLine("Param=?useSSL=false§characterEncoding=utf8§serverTimezone=UTC§autoReconnect=true");
			v5.addLine("#");
			v5.addLine("FastLogin - Mysql数据库配置 #玩家数据表");
			v5.addLine("PlayerDataTable=playertable");
			v5.addLine("PlayerNameField=playername");
			v5.addLine("PlayerPwdField=playerpwd");
			v5.addLine("PlayerRegStatusField=register");
			v5.addLine("PlayerLoginStatusField=login");
			v5.addLine("#");
			v5.addLine("FastLogin - Mysql数据库配置 #玩家位置数据表(!不建议修改)");
			v5.addLine("PlayerLocTable=playerloc");
			v5.addLine("PlayerNameField=Foreign Key");
			v5.addLine("PlayerLocworld=locworld");
			v5.addLine("PlayerLocx=locx");
			v5.addLine("PlayerLocy=locy");
			v5.addLine("PlayerLocz=locz");
			v5.addLine("PlayerLocpitch=locpitch");
			v5.addLine("PlayerLocyaw=locyaw");
			
		}
		
		
	}
	
	
	/**
	 * 检查并创建位置文件
	 */
	public void createLocationConfig(){
		
		logManager.writerInfo("检查Location配置项");
		
		if(v5.createNewGdcFile(FUtil.fastLoginLocationFile) == true){
			
			logManager.writerInfo("写入默认Location配置项");
			
			
			v5.setTarget(FUtil.fastLoginLocationFile);
			
			
			//写入Location文件内容
			v5.addLine("location.world=empty");
			v5.addLine("location.x=empty");
			v5.addLine("location.y=empty");
			v5.addLine("location.z=empty");
			v5.addLine("location.pitch=empty");
			v5.addLine("location.yaw=empty");

			
		}
		
	
		
	}
	
	/**
	 * 检查并创建IPCount文件
	 */
	public void createIPCountConfig(){
		
		logManager.writerInfo("检查IPCount配置项");
		
		if(v5.createNewGdcFile(FUtil.fastLoginIPCountFile) == true){
			
			logManager.writerInfo("写入默认IPCount配置项");
			
		}
		
		
		
	}
	
	/**
	 * 检查并创建语言文件
	 */
	public void createLanguageConfig(){
		
		logManager.writerInfo("检查语言配置项");
		
		if(v5.createNewGdcFile(FUtil.fastLoginLanguageFile) == true){
			
			logManager.writerInfo("写入默认语言配置项");
			
			v5.setTarget(FUtil.fastLoginLanguageFile);
			
			v5.addLine("Notlogin=§e[FastLogin]§b你需要登录后才能操作,使用/login 密码 来登录.");
			v5.addLine("NotRegister=§e[FastLogin]§b你需要注册后才能操作,使用/register 密码 确认密码 来注册.");
			v5.addLine("LoginTimeOutKick=登录超时");
			v5.addLine("RepeatLogin=§e[FastLogin]§c你已经登录了!");
			v5.addLine("RepeatRegister=§e[FastLogin]§c你已经注册了!");
			v5.addLine("NotRegister2=§e[FastLogin]§c你还没有注册！");
			v5.addLine("LoginSuccess=§e[FastLogin]§a登录成功！");
			v5.addLine("PasswordError=§e[FastLogin]§c密码错误！");
			v5.addLine("RegisterSuccess=§e[FastLogin]§a注册完成！");
			v5.addLine("NullPassword=§e[FastLogin]§c请输入密码！");
			v5.addLine("PasswdTooLong=§e[FastLogin]§c你输入的密码长度超过最大限制！");
			v5.addLine("PasswdTooShost=§e[FastLogin]§c你输入的密码太短！");
			v5.addLine("NoConfirmPasswd=§e[FastLogin]§c请输入确认密码 例:/register 12345 12345");
			v5.addLine("ConfirmPasswdError=§e[FastLogin]§c两次输入的密码不一致");
			v5.addLine("JoinGameError1=登录失败:相同用户名的玩家已经在游戏中!");		
			v5.addLine("ChangePwUsage=§e[FastLogin]§b修改密码 - 用法:/ChangePassword 旧密码 新密码 确认新密码");			
			v5.addLine("ChangePw_OldpwErr=§e[FastLogin]§c旧密码错误.");		
			v5.addLine("ChangePw_ConfirmPwError=§e[FastLogin]§c两次输入的确认密码不一致.");				
			v5.addLine("ReModifyPasswd=§e[FastLogin]§c新密码不能和旧密码一样.");				
			v5.addLine("ChangePw_PasswdTooLong=§e[FastLogin]§c新密码超过长度限制.");		
			v5.addLine("ChangePw_PasswdTooShost=§e[FastLogin]§c新密码太短.");				
			v5.addLine("ChangePw_Success=§e[FastLogin]§a修改密码 - 完成!");		
			v5.addLine("LoginOut=§e[FastLogin]§c·登录已经被注销!");	
				
			
			//new V1
			
			v5.addLine("AdminSetPasswordError1=§e[FastLogin]§c此玩家未注册,修改密码失败!");
			
			v5.addLine("AdminSetPasswordUsage=§e[FastLogin]§b使用方法:/Fast SetPassword 玩家名 新密码");
			
			v5.addLine("AdminSetPasswordSuccess=§e[FastLogin]§a成功修改此玩家密码!");
			
			
			v5.addLine("AdminSetPasswordKick=密码被管理员修改!");
			
			v5.addLine("AdminCommandHelp1=§b[FastLogin]·OP命令用法:");
			v5.addLine("AdminCommandHelp2=§6/Fast Reload§f: 重载插件的配置文件");
			v5.addLine("AdminCommandHelp3=§6/Fast setSpawn§f: 设置初始登录地点");
			v5.addLine("AdminCommandHelp4=§6/Fast Spawn§f: 传送到初始登录地点");
			v5.addLine("AdminCommandHelp5=§6/Fast delSpawn§f: 删除初始登录地点");
			v5.addLine("AdminCommandHelp6=§6/Fast setPassword§f: 设置玩家的密码");
			
			v5.addLine("OPHasbeenCanceld=§e[FastLoginSecurity]§c·由于你未登录,你的OP权限已被取消!");
			v5.addLine("CreativeModeHasbeenCanceld=§e[FastLoginSecurity]§c·由于你未登录,你的创造模式已被取消!");
			
			v5.addLine("OPRestore=§e[FastLoginSecurity]§a·你的OP权限已经恢复!");
			
			v5.addLine("CreativeModeRestore=§e[FastLoginSecurity]§a·你的创造模式已经恢复!");
			
			v5.addLine("setSpawnSuccess=§e[FastLogin]§a·初始登录点已设置!");
			
			v5.addLine("DeleteSpawnSuccess=§e[FastLogin]§a·初始登录点已删除!");
			
			v5.addLine("TPSpawnSuccess=§e[FastLogin]§6·正在传送...");
			
			v5.addLine("OPHasBeenCleared=§e[FastLoginSecurity]§c·由于你没有注册,你的OP已被清除..");
			
			v5.addLine("CreativeHasBeenCleared=§e[FastLoginSecurity]§c·由于你没有注册,你的OP已被清除..");
			
		}
		
		
		
	}
	
	/**
	 * 更新位置文件
	 */
	public void updateLocationConfig(DefaultLocationEntity DLE){
		
		v5.setTarget(FUtil.fastLoginLocationFile);

		
		v5.setKeyValue("location.world", DLE.getLocation_world());
		v5.setKeyValue("location.x", String.valueOf(DLE.getLocation_x()));
		v5.setKeyValue("location.y", String.valueOf(DLE.getLocation_y()));
		v5.setKeyValue("location.z", String.valueOf(DLE.getLocation_z()));
		v5.setKeyValue("location.pitch", String.valueOf(DLE.getLocation_pitch()));
		v5.setKeyValue("location.yaw", String.valueOf(DLE.getLocation_yaw()));

	}
	
	
	

	

	
}
