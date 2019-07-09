package com.ksptooi.FL.Data.Config;

import java.io.File;

import org.bukkit.Bukkit;
import com.ksptooi.FL.Data.Config.Entity.Config;
import com.ksptooi.FL.Data.Config.Entity.DefaultLocationEntity;
import com.ksptooi.FL.Data.Config.Entity.Language;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Util.Logger;
import com.ksptooi.gdc.v6.Factory.DataSessionFactory;
import com.ksptooi.gdc.v6.Session.dataSession;

public class ConfigReader {

	Logger logManager=null;
	
	DataSessionFactory dataSessionFactory = DataManager.getDataSessionFactory();
	
	public ConfigReader(){
		this.logManager=new Logger();
	}
	
	
	/**
	 * 读取插件配置文件
	 * @return 返回一个配置文件实例
	 */
	public Config readerConfig(){

		File configFile = ConfigManager.fastLoginConfigFile;
		
		dataSession ds = dataSessionFactory.openSession(configFile);
		
		Config config=new Config();
		
		logManager.logInfo("读取配置项");	
		
		config.setConfigVersion(ds.get("ConfigVersion"));
		
		config.setPlayerDataType(ds.get("PlayerDataType"));
		
		config.setLoginTimeOut(ds.getInt("LoginTimeOut"));
		
		config.setPasswordMaxLength(ds.getInt("PasswordMaxLength"));
		
		config.setPasswordMinLength(ds.getInt("PasswordMinLength"));
				
		config.setPlayerNameMinLength(ds.getInt("PlayerNameMinLength"));
				
		config.setMessageInterval(ds.getInt("MessageInterval"));
		
		config.setLoginSecurityTime(ds.getInt("LoginSecurityTime"));
		
		config.setEnable_LoginSecurity(ds.getBoolean("Enable_LoginSecurity"));
				
		config.setEnable_UserNameStrictmode(ds.getBoolean("Enable_UserNameStrictmode"));
		
		config.setBanName(ds.getList("BanName"));
		
		config.setMaxRegisterIP(ds.getInt("MaxRegisterIP"));
			
		config.setRegexMatchForPlayerName(ds.get("RegexMatchForPlayerName"));
		
		config.setPlayerLoginedMessage(ds.get("PlayerLoginedMessage"));
		
		config.setPlayerJoinedMessage(ds.get("PlayerJoinedMessage"));
			
		config.setPlayerQuitMessage(ds.get("PlayerQuitMessage"));
		
		config.setEnable_PlayerLoginedEffect(ds.getBoolean("Enable_PlayerLoginedEffect"));
		
		config.setEnable_PlayerPreLoginEffect(ds.getBoolean("Enable_PlayerPreLoginEffect"));
			
		config.setEnable_OPSecurity(ds.getBoolean("Enable_OPSecurity"));		
		
		config.setEnable_SecurityWarning(ds.getBoolean("Enable_SecurityWarning"));
		
		config.setEnable_HellGateSecurity(ds.getBoolean("Enable_HellGateSecurity"));
		
		config.setEnable_CreativeModeSecurity(ds.getBoolean("Enable_CreativeModeSecurity"));
		
		config.setEnable_passwordHash(ds.get("Enable_passwordHash"));
		
		config.setEnable_SupportOldPassword(ds.getBoolean("Enable_SupportOldPassword"));
		
		config.setEnable_DebugPrint(ds.getBoolean("Enable_DebugPrint"));
			
		config.setAddress(ds.get("address"));
		
		config.setDatabase(ds.get("database"));
		
		config.setUsername(ds.get("username"));
		
		config.setPassword(ds.get("password"));
		
		config.setPoolinitSize(ds.getInt("poolinitSize"));

		config.setParam(ds.get("param"));
		
		config.setPlayerDataTable(ds.get("PlayerDataTable"));
		
		config.setPlayerNameField(ds.get("PlayerNameField"));
		
		config.setPlayerPwdField(ds.get("PlayerPwdField"));
		
		config.setPlayerRegStatusField(ds.get("PlayerRegStatusField"));
		
		config.setPlayerLoginStatusField(ds.get("PlayerLoginStatusField"));
		
		config.setPlayerLocTable(ds.get("PlayerLocTable"));
		
		config.setPlayerNameField(ds.get("PlayerNameField"));
		
		config.setPlayerLocworld(ds.get("PlayerLocworld"));
		
		config.setPlayerLocx(ds.get("PlayerLocx"));
			
		config.setPlayerLocy(ds.get("PlayerLocy"));
		
		config.setPlayerLocz(ds.get("PlayerLocz"));
		
		config.setPlayerLocpitch(ds.get("PlayerLocpitch"));
		
		config.setPlayerLocyaw(ds.get("PlayerLocyaw"));
		
		ds.release();
		
		return config;
	}
	
	
	
	/**
	 * 读取位置文件
	 * @return 返回一个位置文件实例
	 */
	public DefaultLocationEntity readerLocationConfig(){
		
		logManager.logInfo("读取Location");
		
		DefaultLocationEntity DLE=new DefaultLocationEntity();
		
		dataSession ds = dataSessionFactory.openSession(ConfigManager.fastLoginLocationFile);
		
		DLE.setLocation_world(ds.get("location.world"));
		
		//初始化位置
		if(! DLE.getLocation_world().equalsIgnoreCase("empty")){
			
			DLE.setLocation_x(ds.getDouble("location.x"));
			DLE.setLocation_y(ds.getDouble("location.y"));
			DLE.setLocation_z(ds.getDouble("location.z"));
			DLE.setLocation_pitch(ds.getFloat("location.pitch"));
			DLE.setLocation_yaw(ds.getFloat("location.yaw"));
			
			DLE.getLoginLocation().setWorld(Bukkit.getWorld(DLE.getLocation_world()));
			DLE.getLoginLocation().setX(DLE.getLocation_x());
			DLE.getLoginLocation().setY(DLE.getLocation_y());
			DLE.getLoginLocation().setZ(DLE.getLocation_z());
			DLE.getLoginLocation().setPitch(DLE.getLocation_pitch());
			DLE.getLoginLocation().setYaw(DLE.getLocation_yaw());
		}
				
		ds.release();
				
		return DLE;
	}
	
	
	/**
	 * 读取语言文件
	 * @return 返回一个语言文件实例
	 */
	
	public Language readerLanguageConfig(){
		
		logManager.logInfo("读取语言配置项");
		
		Language lang=new Language();
		
		dataSession ds = dataSessionFactory.openSession(ConfigManager.fastLoginLanguageFile);
		

		
		lang.setNotlogin(ds.get("Notlogin"));
		lang.setNotRegister(ds.get("NotRegister"));
		lang.setLoginTimeOutKick(ds.get("LoginTimeOutKick"));	
		lang.setRepeatLogin(ds.get("RepeatLogin"));
		lang.setRepeatRegister(ds.get("RepeatRegister"));
		lang.setNotRegister2(ds.get("NotRegister2"));
		lang.setLoginSuccess(ds.get("LoginSuccess"));
		lang.setPasswordError(ds.get("PasswordError"));
		lang.setRegisterSuccess(ds.get("RegisterSuccess"));
		lang.setNullPassword(ds.get("NullPassword"));
		lang.setPasswdTooLong(ds.get("PasswdTooLong"));
		lang.setPasswdTooShost(ds.get("PasswdTooShost"));
		lang.setNoConfirmPasswd(ds.get("NoConfirmPasswd"));
		lang.setConfirmPasswdError(ds.get("ConfirmPasswdError"));
		lang.setJoinGameError1(ds.get("JoinGameError1"));		
		lang.setChangePwUsage(ds.get("ChangePwUsage"));
		lang.setChangePw_OldpwErr(ds.get("ChangePw_OldpwErr"));
		lang.setChangePw_ConfirmPwError(ds.get("ChangePw_ConfirmPwError"));
		lang.setReModifyPasswd(ds.get("ReModifyPasswd"));
		lang.setChangePw_PasswdTooLong(ds.get("ChangePw_PasswdTooLong"));
		lang.setChangePw_PasswdTooShost(ds.get("ChangePw_PasswdTooShost"));
		lang.setChangePw_Success(ds.get("ChangePw_Success"));
		lang.setLoginOut(ds.get("LoginOut"));	
		lang.setAdminSetPasswordError1(ds.get("AdminSetPasswordError1"));		
		lang.setAdminSetPasswordUsage(ds.get("AdminSetPasswordUsage"));		
		lang.setAdminSetPasswordSuccess(ds.get("AdminSetPasswordSuccess"));		
		lang.setAdminSetPasswordKick(ds.get("AdminSetPasswordKick"));	
		lang.setAdminCommandHelp1(ds.get("AdminCommandHelp1"));
		lang.setAdminCommandHelp2(ds.get("AdminCommandHelp2"));
		lang.setAdminCommandHelp3(ds.get("AdminCommandHelp3"));
		lang.setAdminCommandHelp4(ds.get("AdminCommandHelp4"));
		lang.setAdminCommandHelp5(ds.get("AdminCommandHelp5"));
		lang.setAdminCommandHelp6(ds.get("AdminCommandHelp6"));		
		lang.setOPHasbeenCanceld(ds.get("OPHasbeenCanceld"));
		lang.setCreativeModeHasbeenCanceld(ds.get("CreativeModeHasbeenCanceld"));		
		lang.setOPRestore(ds.get("OPRestore"));		
		lang.setCreativeModeRestore(ds.get("CreativeModeRestore"));		
		lang.setSetSpawnSuccess(ds.get("setSpawnSuccess"));		
		lang.setDeleteSpawnSuccess(ds.get("DeleteSpawnSuccess"));		
		lang.setTPSpawnSuccess(ds.get("TPSpawnSuccess"));		
		lang.setOPHasBeenCleared(ds.get("OPHasBeenCleared"));		
		lang.setCreativeHasBeenCleared(ds.get("CreativeHasBeenCleared"));
		
		ds.release();
		
		return lang;
	}
	
	
	
}
