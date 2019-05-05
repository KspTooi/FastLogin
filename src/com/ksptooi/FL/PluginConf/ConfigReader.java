package com.ksptooi.FL.PluginConf;

import org.bukkit.Bukkit;
import com.ksptooi.FL.Entity.DefaultLocationEntity;
import com.ksptooi.FL.Entity.FastLoginConfigEntity;
import com.ksptooi.FL.Entity.FastLoginLanguageEntity;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.LogManager;
import com.ksptooi.gdc.FileAPI.IOController_V5;

public class ConfigReader {

	LogManager logManager=null;
	IOController_V5 v5=new IOController_V5();
	
	public ConfigReader(){
		this.logManager=new LogManager();
	}
	
	/**
	 * 读取插件配置文件
	 * @return 返回一个配置文件实例
	 */
	public FastLoginConfigEntity readerConfig(){

		logManager.writerInfo("读取配置项");
		
		FastLoginConfigEntity FLCE=new FastLoginConfigEntity();
		
		v5.setTarget(FUtil.fastLoginConfigFile);
		
		FLCE.setVersion(v5.getKeyValue("ConfigVersion"));
		FLCE.setPlayerDataType(v5.getKeyValue("PlayerDataType"));
		FLCE.setLoginTimeOut(new Integer(v5.getKeyValue("LoginTimeOut")));
		FLCE.setPasswordMaxLength(new Integer(v5.getKeyValue("PasswordMaxLength")));
		FLCE.setPasswordMinLength(new Integer(v5.getKeyValue("PasswordMinLength")));
		FLCE.setPlayerNameMinLength(new Integer(v5.getKeyValue("PlayerNameMinLength")));
		FLCE.setMessageInterval(new Integer(v5.getKeyValue("MessageInterval")));
		FLCE.setLoginSecurityTime(new Integer(v5.getKeyValue("LoginSecurityTime")));
		FLCE.setEnable_LoginSecurity(new Boolean(v5.getKeyValue("Enable_LoginSecurity")));
		FLCE.setEnable_UserNameStrictmode(new Boolean(v5.getKeyValue("Enable_UserNameStrictmode")));
		FLCE.setBanName(v5.getKeyValue("BanName").split(";"));
		FLCE.setMaxRegisterIP(new Integer(v5.getKeyValue("MaxRegisterIP")));
		FLCE.setRegexMatchForPlayerName(v5.getKeyValue("RegexMatchForPlayerName"));
		FLCE.setPlayerLoginedMessage(v5.getKeyValue("PlayerLoginedMessage"));
		FLCE.setPlayerJoinedMessage(v5.getKeyValue("PlayerJoinedMessage"));
		FLCE.setPlayerQuitMessage(v5.getKeyValue("PlayerQuitMessage"));
		FLCE.setEnable_OPSecurity(new Boolean(v5.getKeyValue("Enable_OPSecurity")));
		FLCE.setEnable_SecurityWarning(new Boolean(v5.getKeyValue("Enable_SecurityWarning")));
		FLCE.setEnable_HellGateSecurity(new Boolean(v5.getKeyValue("Enable_HellGateSecurity")));
		FLCE.setEnable_PlayerLoginedEffect(new Boolean(v5.getKeyValue("Enable_PlayerLoginedEffect")));
		FLCE.setEnable_PlayerPreLoginEffect(new Boolean(v5.getKeyValue("Enable_PlayerPreLoginEffect")));
		FLCE.setEnable_CreativeModeSecurity(new Boolean(v5.getKeyValue("Enable_CreativeModeSecurity")));
		FLCE.setEnable_passwordHash(v5.getKeyValue("Enable_passwordHash"));
		FLCE.setEnable_SupportOldPassword(new Boolean(v5.getKeyValue("Enable_SupportOldPassword")));	
		FLCE.setEnable_DebugPrint(new Boolean(v5.getKeyValue("Enable_DebugPrint")));
		
		FLCE.setMysqlAddress(v5.getKeyValue("MysqlAddress"));
		FLCE.setDataBaseName(v5.getKeyValue("DataBaseName"));
		FLCE.setMysqlUser(v5.getKeyValue("MysqlUser"));
		FLCE.setMysqlPwd(v5.getKeyValue("MysqlPwd"));
		FLCE.setParam(v5.getKeyValue("Param"));
		FLCE.setPlayerDataTable(v5.getKeyValue("PlayerDataTable"));
		FLCE.setPlayerNameField(v5.getKeyValue("PlayerNameField"));
		FLCE.setPlayerPwdField(v5.getKeyValue("PlayerPwdField"));
		FLCE.setPlayerRegStatusField(v5.getKeyValue("PlayerRegStatusField"));
		FLCE.setPlayerLoginStatusField(v5.getKeyValue("PlayerLoginStatusField"));
		FLCE.setPlayerLocTable(v5.getKeyValue("PlayerLocTable"));
		FLCE.setPlayerLocworld(v5.getKeyValue("PlayerLocworld"));
		FLCE.setPlayerLocx(v5.getKeyValue("PlayerLocx"));
		FLCE.setPlayerLocy(v5.getKeyValue("PlayerLocy"));
		FLCE.setPlayerLocz(v5.getKeyValue("PlayerLocz"));
		FLCE.setPlayerLocpitch(v5.getKeyValue("PlayerLocpitch"));
		FLCE.setPlayerLocyaw(v5.getKeyValue("PlayerLocyaw"));
		
		return FLCE;
	}
	
	/**
	 * 读取位置文件
	 * @return 返回一个位置文件实例
	 */
	public DefaultLocationEntity readerLocationConfig(){
		
		logManager.writerInfo("读取Location");
		
		DefaultLocationEntity DLE=new DefaultLocationEntity();
		
		v5.setTarget(FUtil.fastLoginLocationFile);
		
		DLE.setLocation_world(v5.getKeyValue("location.world"));
		
		//初始化位置
		if(! DLE.getLocation_world().equalsIgnoreCase("empty")){
			
			DLE.setLocation_x(new Double(v5.getKeyValue("location.x")));
			DLE.setLocation_y(new Double(v5.getKeyValue("location.y")));
			DLE.setLocation_z(new Double(v5.getKeyValue("location.z")));
			DLE.setLocation_pitch(new Float(v5.getKeyValue("location.pitch")));
			DLE.setLocation_yaw(new Float(v5.getKeyValue("location.yaw")));
			DLE.getLoginLocation().setWorld(Bukkit.getWorld(DLE.getLocation_world()));
			DLE.getLoginLocation().setX(DLE.getLocation_x());
			DLE.getLoginLocation().setY(DLE.getLocation_y());
			DLE.getLoginLocation().setZ(DLE.getLocation_z());
			DLE.getLoginLocation().setPitch(DLE.getLocation_pitch());
			DLE.getLoginLocation().setYaw(DLE.getLocation_yaw());
		}
		
		return DLE;
	}
	
	/**
	 * 读取语言文件
	 * @return 返回一个语言文件实例
	 */
	
	public FastLoginLanguageEntity readerLanguageConfig(){
		
		logManager.writerInfo("读取语言配置项");
		
		FastLoginLanguageEntity FLLE=new FastLoginLanguageEntity();
		
		v5.setTarget(FUtil.fastLoginLanguageFile);
		
		FLLE.setNotlogin(v5.getKeyValue("Notlogin"));
		FLLE.setNotRegister(v5.getKeyValue("NotRegister"));
		FLLE.setLoginTimeOutKick(v5.getKeyValue("LoginTimeOutKick"));	
		FLLE.setRepeatLogin(v5.getKeyValue("RepeatLogin"));
		FLLE.setRepeatRegister(v5.getKeyValue("RepeatRegister"));
		FLLE.setNotRegister2(v5.getKeyValue("NotRegister2"));
		FLLE.setLoginSuccess(v5.getKeyValue("LoginSuccess"));
		FLLE.setPasswordError(v5.getKeyValue("PasswordError"));
		FLLE.setRegisterSuccess(v5.getKeyValue("RegisterSuccess"));
		FLLE.setNullPassword(v5.getKeyValue("NullPassword"));
		FLLE.setPasswdTooLong(v5.getKeyValue("PasswdTooLong"));
		FLLE.setPasswdTooShost(v5.getKeyValue("PasswdTooShost"));
		FLLE.setNoConfirmPasswd(v5.getKeyValue("NoConfirmPasswd"));
		FLLE.setConfirmPasswdError(v5.getKeyValue("ConfirmPasswdError"));
		FLLE.setJoinGameError1(v5.getKeyValue("JoinGameError1"));		
		FLLE.setChangePwUsage(v5.getKeyValue("ChangePwUsage"));
		FLLE.setChangePw_OldpwErr(v5.getKeyValue("ChangePw_OldpwErr"));
		FLLE.setChangePw_ConfirmPwError(v5.getKeyValue("ChangePw_ConfirmPwError"));
		FLLE.setReModifyPasswd(v5.getKeyValue("ReModifyPasswd"));
		FLLE.setChangePw_PasswdTooLong(v5.getKeyValue("ChangePw_PasswdTooLong"));
		FLLE.setChangePw_PasswdTooShost(v5.getKeyValue("ChangePw_PasswdTooShost"));
		FLLE.setChangePw_Success(v5.getKeyValue("ChangePw_Success"));
		FLLE.setLoginOut(v5.getKeyValue("LoginOut"));
		
		FLLE.setAdminSetPasswordError1(v5.getKeyValue("AdminSetPasswordError1"));
		
		FLLE.setAdminSetPasswordUsage(v5.getKeyValue("AdminSetPasswordUsage"));
		
		FLLE.setAdminSetPasswordSuccess(v5.getKeyValue("AdminSetPasswordSuccess"));
		
		FLLE.setAdminSetPasswordKick(v5.getKeyValue("AdminSetPasswordKick"));
		
		FLLE.setAdminCommandHelp1(v5.getKeyValue("AdminCommandHelp1"));
		FLLE.setAdminCommandHelp2(v5.getKeyValue("AdminCommandHelp2"));
		FLLE.setAdminCommandHelp3(v5.getKeyValue("AdminCommandHelp3"));
		FLLE.setAdminCommandHelp4(v5.getKeyValue("AdminCommandHelp4"));
		FLLE.setAdminCommandHelp5(v5.getKeyValue("AdminCommandHelp5"));
		FLLE.setAdminCommandHelp6(v5.getKeyValue("AdminCommandHelp6"));
		
		FLLE.setOPHasbeenCanceld(v5.getKeyValue("OPHasbeenCanceld"));
		FLLE.setCreativeModeHasbeenCanceld(v5.getKeyValue("CreativeModeHasbeenCanceld"));
		
		FLLE.setOPRestore(v5.getKeyValue("OPRestore"));
		
		FLLE.setCreativeModeRestore(v5.getKeyValue("CreativeModeRestore"));
		
		FLLE.setSetSpawnSuccess(v5.getKeyValue("setSpawnSuccess"));
		
		FLLE.setDeleteSpawnSuccess(v5.getKeyValue("DeleteSpawnSuccess"));
		
		FLLE.setTPSpawnSuccess(v5.getKeyValue("TPSpawnSuccess"));
		
		FLLE.setOPHasBeenCleared(v5.getKeyValue("OPHasBeenCleared"));
		
		FLLE.setCreativeHasBeenCleared(v5.getKeyValue("CreativeHasBeenCleared"));
			
		
		return FLLE;
	}
	
	
	
}
