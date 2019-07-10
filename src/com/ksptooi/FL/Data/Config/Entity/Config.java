package com.ksptooi.FL.Data.Config.Entity;

import java.util.ArrayList;

public class Config {



	private String ConfigVersion=null;
	
	private String PlayerDataType="GeneralDataCore";
	
	private int LoginTimeOut=0;
	
	private int PasswordMaxLength=0;
	
	private int PasswordMinLength=0;
	
	private int PlayerNameMinLength=0;
	
	private int MessageInterval=0;
	
	private int LoginSecurityTime=0;
	
	private boolean Enable_LoginSecurity=false;
	
	private boolean Enable_UserNameStrictmode=false;
	
	private ArrayList<String> BanName=null;
	
	private int MaxRegisterIP=0;
	
	private String RegexMatchForPlayerName=null;
	
	private String PlayerLoginedMessage=null;
	
	private String PlayerJoinedMessage=null;
	
	private String PlayerQuitMessage=null;
	
	private boolean Enable_OPSecurity=false;
	
	private boolean Enable_HellGateSecurity=false;
	
	private boolean Enable_PlayerLoginedEffect=true;
	
	private boolean Enable_PlayerPreLoginEffect=true;
	
	private boolean Enable_CreativeModeSecurity=true;
	
	private String Enable_passwordHash="MD5"; 
	
	private boolean Enable_SupportOldPassword=false;
	
	private boolean Enable_DebugPrint=false;
	
	//FastLogin - Mysql数据库配置
	private String address=null;
	private String database=null;
	private String username=null;
	private String password=null;
	private int poolinitSize=16;
	private String param=null;

	
	
	//FastLogin - Mysql数据库配置 - 玩家数据表
	private String PlayerDataTable=null;
	
	private String PlayerNameField=null;
	
	private String PlayerPwdField=null;
	
	private String PlayerRegStatusField=null;
	
	private String PlayerLoginStatusField=null;
	
	
	//FastLogin - Mysql数据库配置 - 玩家位置表
	private String PlayerLocTable=null;
	
	private String PlayerLocworld=null;
	
	private String PlayerLocx=null;
	
	private String PlayerLocy=null;
	
	private String PlayerLocz=null;
	
	private String PlayerLocpitch=null;
	
	private String PlayerLocyaw=null;

	public String getConfigVersion() {
		return ConfigVersion;
	}

	public String getPlayerDataType() {
		return PlayerDataType;
	}

	public int getLoginTimeOut() {
		return LoginTimeOut;
	}

	public int getPasswordMaxLength() {
		return PasswordMaxLength;
	}

	public int getPasswordMinLength() {
		return PasswordMinLength;
	}

	public int getPlayerNameMinLength() {
		return PlayerNameMinLength;
	}

	public int getMessageInterval() {
		return MessageInterval;
	}

	public int getLoginSecurityTime() {
		return LoginSecurityTime;
	}

	public boolean isEnable_LoginSecurity() {
		return Enable_LoginSecurity;
	}

	public boolean isEnable_UserNameStrictmode() {
		return Enable_UserNameStrictmode;
	}

	public ArrayList<String> getBanName() {
		return BanName;
	}

	public int getMaxRegisterIP() {
		return MaxRegisterIP;
	}

	public String getRegexMatchForPlayerName() {
		return RegexMatchForPlayerName;
	}

	public String getPlayerLoginedMessage() {
		return PlayerLoginedMessage;
	}

	public String getPlayerJoinedMessage() {
		return PlayerJoinedMessage;
	}

	public String getPlayerQuitMessage() {
		return PlayerQuitMessage;
	}

	public boolean isEnable_OPSecurity() {
		return Enable_OPSecurity;
	}


	public boolean isEnable_HellGateSecurity() {
		return Enable_HellGateSecurity;
	}

	public boolean isEnable_PlayerLoginedEffect() {
		return Enable_PlayerLoginedEffect;
	}

	public boolean isEnable_PlayerPreLoginEffect() {
		return Enable_PlayerPreLoginEffect;
	}

	public boolean isEnable_CreativeModeSecurity() {
		return Enable_CreativeModeSecurity;
	}

	public String getEnable_passwordHash() {
		return Enable_passwordHash;
	}

	public boolean isEnable_SupportOldPassword() {
		return Enable_SupportOldPassword;
	}

	public boolean isEnable_DebugPrint() {
		return Enable_DebugPrint;
	}

	public String getAddress() {
		return address;
	}

	public String getDatabase() {
		return database;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getPoolinitSize() {
		return poolinitSize;
	}

	public String getParam() {
		return param;
	}

	public String getPlayerDataTable() {
		return PlayerDataTable;
	}

	public String getPlayerNameField() {
		return PlayerNameField;
	}

	public String getPlayerPwdField() {
		return PlayerPwdField;
	}

	public String getPlayerRegStatusField() {
		return PlayerRegStatusField;
	}

	public String getPlayerLoginStatusField() {
		return PlayerLoginStatusField;
	}

	public String getPlayerLocTable() {
		return PlayerLocTable;
	}

	public String getPlayerLocworld() {
		return PlayerLocworld;
	}

	public String getPlayerLocx() {
		return PlayerLocx;
	}

	public String getPlayerLocy() {
		return PlayerLocy;
	}

	public String getPlayerLocz() {
		return PlayerLocz;
	}

	public String getPlayerLocpitch() {
		return PlayerLocpitch;
	}

	public String getPlayerLocyaw() {
		return PlayerLocyaw;
	}

	public void setConfigVersion(String configVersion) {
		ConfigVersion = configVersion;
	}

	public void setPlayerDataType(String playerDataType) {
		PlayerDataType = playerDataType;
	}

	public void setLoginTimeOut(int loginTimeOut) {
		LoginTimeOut = loginTimeOut;
	}

	public void setPasswordMaxLength(int passwordMaxLength) {
		PasswordMaxLength = passwordMaxLength;
	}

	public void setPasswordMinLength(int passwordMinLength) {
		PasswordMinLength = passwordMinLength;
	}

	public void setPlayerNameMinLength(int playerNameMinLength) {
		PlayerNameMinLength = playerNameMinLength;
	}

	public void setMessageInterval(int messageInterval) {
		MessageInterval = messageInterval;
	}

	public void setLoginSecurityTime(int loginSecurityTime) {
		LoginSecurityTime = loginSecurityTime;
	}

	public void setEnable_LoginSecurity(boolean enable_LoginSecurity) {
		Enable_LoginSecurity = enable_LoginSecurity;
	}

	public void setEnable_UserNameStrictmode(boolean enable_UserNameStrictmode) {
		Enable_UserNameStrictmode = enable_UserNameStrictmode;
	}

	public void setBanName(ArrayList<String> banName) {
		BanName = banName;
	}

	public void setMaxRegisterIP(int maxRegisterIP) {
		MaxRegisterIP = maxRegisterIP;
	}

	public void setRegexMatchForPlayerName(String regexMatchForPlayerName) {
		RegexMatchForPlayerName = regexMatchForPlayerName;
	}

	public void setPlayerLoginedMessage(String playerLoginedMessage) {
		PlayerLoginedMessage = playerLoginedMessage;
	}

	public void setPlayerJoinedMessage(String playerJoinedMessage) {
		PlayerJoinedMessage = playerJoinedMessage;
	}

	public void setPlayerQuitMessage(String playerQuitMessage) {
		PlayerQuitMessage = playerQuitMessage;
	}

	public void setEnable_OPSecurity(boolean enable_OPSecurity) {
		Enable_OPSecurity = enable_OPSecurity;
	}

	public void setEnable_HellGateSecurity(boolean enable_HellGateSecurity) {
		Enable_HellGateSecurity = enable_HellGateSecurity;
	}

	public void setEnable_PlayerLoginedEffect(boolean enable_PlayerLoginedEffect) {
		Enable_PlayerLoginedEffect = enable_PlayerLoginedEffect;
	}

	public void setEnable_PlayerPreLoginEffect(boolean enable_PlayerPreLoginEffect) {
		Enable_PlayerPreLoginEffect = enable_PlayerPreLoginEffect;
	}

	public void setEnable_CreativeModeSecurity(boolean enable_CreativeModeSecurity) {
		Enable_CreativeModeSecurity = enable_CreativeModeSecurity;
	}

	public void setEnable_passwordHash(String enable_passwordHash) {
		Enable_passwordHash = enable_passwordHash;
	}

	public void setEnable_SupportOldPassword(boolean enable_SupportOldPassword) {
		Enable_SupportOldPassword = enable_SupportOldPassword;
	}

	public void setEnable_DebugPrint(boolean enable_DebugPrint) {
		Enable_DebugPrint = enable_DebugPrint;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPoolinitSize(int poolinitSize) {
		this.poolinitSize = poolinitSize;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public void setPlayerDataTable(String playerDataTable) {
		PlayerDataTable = playerDataTable;
	}

	public void setPlayerNameField(String playerNameField) {
		PlayerNameField = playerNameField;
	}

	public void setPlayerPwdField(String playerPwdField) {
		PlayerPwdField = playerPwdField;
	}

	public void setPlayerRegStatusField(String playerRegStatusField) {
		PlayerRegStatusField = playerRegStatusField;
	}

	public void setPlayerLoginStatusField(String playerLoginStatusField) {
		PlayerLoginStatusField = playerLoginStatusField;
	}

	public void setPlayerLocTable(String playerLocTable) {
		PlayerLocTable = playerLocTable;
	}

	public void setPlayerLocworld(String playerLocworld) {
		PlayerLocworld = playerLocworld;
	}

	public void setPlayerLocx(String playerLocx) {
		PlayerLocx = playerLocx;
	}

	public void setPlayerLocy(String playerLocy) {
		PlayerLocy = playerLocy;
	}

	public void setPlayerLocz(String playerLocz) {
		PlayerLocz = playerLocz;
	}

	public void setPlayerLocpitch(String playerLocpitch) {
		PlayerLocpitch = playerLocpitch;
	}

	public void setPlayerLocyaw(String playerLocyaw) {
		PlayerLocyaw = playerLocyaw;
	}



	
	
	
}
