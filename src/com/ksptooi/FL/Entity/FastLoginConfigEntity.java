package com.ksptooi.FL.Entity;

public class FastLoginConfigEntity {

	private String Version=null;
	
	private String PlayerDataType="GeneralDataCore";
	
	private int LoginTimeOut=0;
	
	private int PasswordMaxLength=0;
	
	private int PasswordMinLength=0;
	
	private int PlayerNameMinLength=0;
	
	private int MessageInterval=0;
	
	private int LoginSecurityTime=0;
	
	private boolean Enable_LoginSecurity=false;
	
	private boolean Enable_UserNameStrictmode=false;
	
	private String[] BanName=null;
	
	private int MaxRegisterIP=0;
	
	private String RegexMatchForPlayerName=null;
	
	private String PlayerLoginedMessage=null;
	
	private String PlayerJoinedMessage=null;
	
	private String PlayerQuitMessage=null;
	
	private boolean Enable_OPSecurity=false;
	
	private boolean Enable_SecurityWarning=false;
	
	private boolean Enable_HellGateSecurity=false;
	
	private boolean Enable_PlayerLoginedEffect=true;
	
	private boolean Enable_PlayerPreLoginEffect=true;
	
	private boolean Enable_CreativeModeSecurity=true;
	
	private String Enable_passwordHash="MD5"; 
	
	private boolean Enable_SupportOldPassword=false;
	
	private boolean Enable_DebugPrint=false;
	
	//FastLogin - Mysql数据库配置
	private String MysqlAddress=null;
	private String DataBaseName=null;
	private String MysqlUser=null;
	private String MysqlPwd=null;
	private String Param=null;
	
	
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

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public String getPlayerDataType() {
		return PlayerDataType;
	}

	public void setPlayerDataType(String playerDataType) {
		PlayerDataType = playerDataType;
	}

	public int getLoginTimeOut() {
		return LoginTimeOut;
	}

	public void setLoginTimeOut(int loginTimeOut) {
		LoginTimeOut = loginTimeOut;
	}

	public int getPasswordMaxLength() {
		return PasswordMaxLength;
	}

	public void setPasswordMaxLength(int passwordMaxLength) {
		PasswordMaxLength = passwordMaxLength;
	}

	public int getPasswordMinLength() {
		return PasswordMinLength;
	}

	public void setPasswordMinLength(int passwordMinLength) {
		PasswordMinLength = passwordMinLength;
	}

	public int getMessageInterval() {
		return MessageInterval;
	}

	public void setMessageInterval(int messageInterval) {
		MessageInterval = messageInterval;
	}

	public int getLoginSecurityTime() {
		return LoginSecurityTime;
	}

	public void setLoginSecurityTime(int loginSecurityTime) {
		LoginSecurityTime = loginSecurityTime;
	}

	public boolean isEnable_LoginSecurity() {
		return Enable_LoginSecurity;
	}

	public void setEnable_LoginSecurity(boolean enable_LoginSecurity) {
		Enable_LoginSecurity = enable_LoginSecurity;
	}

	public boolean isEnable_UserNameStrictmode() {
		return Enable_UserNameStrictmode;
	}

	public void setEnable_UserNameStrictmode(boolean enable_UserNameStrictmode) {
		Enable_UserNameStrictmode = enable_UserNameStrictmode;
	}

	public String[] getBanName() {
		return BanName;
	}

	public void setBanName(String[] banName) {
		BanName = banName;
	}

	public int getMaxRegisterIP() {
		return MaxRegisterIP;
	}

	public void setMaxRegisterIP(int maxRegisterIP) {
		MaxRegisterIP = maxRegisterIP;
	}

	public String getRegexMatchForPlayerName() {
		return RegexMatchForPlayerName;
	}

	public void setRegexMatchForPlayerName(String regexMatchForPlayerName) {
		RegexMatchForPlayerName = regexMatchForPlayerName;
	}

	public String getPlayerLoginedMessage() {
		return PlayerLoginedMessage;
	}

	public void setPlayerLoginedMessage(String playerLoginedMessage) {
		PlayerLoginedMessage = playerLoginedMessage;
	}

	public String getPlayerJoinedMessage() {
		return PlayerJoinedMessage;
	}

	public void setPlayerJoinedMessage(String playerJoinedMessage) {
		PlayerJoinedMessage = playerJoinedMessage;
	}

	public String getPlayerQuitMessage() {
		return PlayerQuitMessage;
	}

	public void setPlayerQuitMessage(String playerQuitMessage) {
		PlayerQuitMessage = playerQuitMessage;
	}

	public boolean isEnable_OPSecurity() {
		return Enable_OPSecurity;
	}

	public void setEnable_OPSecurity(boolean enable_OPSecurity) {
		Enable_OPSecurity = enable_OPSecurity;
	}

	public boolean isEnable_SecurityWarning() {
		return Enable_SecurityWarning;
	}

	public void setEnable_SecurityWarning(boolean enable_SecurityWarning) {
		Enable_SecurityWarning = enable_SecurityWarning;
	}

	public boolean isEnable_HellGateSecurity() {
		return Enable_HellGateSecurity;
	}

	public void setEnable_HellGateSecurity(boolean enable_HellGateSecurity) {
		Enable_HellGateSecurity = enable_HellGateSecurity;
	}

	public String getMysqlAddress() {
		return MysqlAddress;
	}

	public void setMysqlAddress(String mysqlAddress) {
		MysqlAddress = mysqlAddress;
	}

	public String getDataBaseName() {
		return DataBaseName;
	}

	public void setDataBaseName(String dataBaseName) {
		DataBaseName = dataBaseName;
	}

	public String getMysqlUser() {
		return MysqlUser;
	}

	public void setMysqlUser(String mysqlUser) {
		MysqlUser = mysqlUser;
	}

	public String getMysqlPwd() {
		return MysqlPwd;
	}

	public void setMysqlPwd(String mysqlPwd) {
		MysqlPwd = mysqlPwd;
	}

	public String getParam() {
		return Param;
	}

	public void setParam(String param) {
		Param = param;
	}

	public String getPlayerDataTable() {
		return PlayerDataTable;
	}

	public void setPlayerDataTable(String playerDataTable) {
		PlayerDataTable = playerDataTable;
	}

	public String getPlayerNameField() {
		return PlayerNameField;
	}

	public void setPlayerNameField(String playerNameField) {
		PlayerNameField = playerNameField;
	}

	public String getPlayerPwdField() {
		return PlayerPwdField;
	}

	public void setPlayerPwdField(String playerPwdField) {
		PlayerPwdField = playerPwdField;
	}

	public String getPlayerRegStatusField() {
		return PlayerRegStatusField;
	}

	public void setPlayerRegStatusField(String playerRegStatusField) {
		PlayerRegStatusField = playerRegStatusField;
	}

	public String getPlayerLoginStatusField() {
		return PlayerLoginStatusField;
	}

	public void setPlayerLoginStatusField(String playerLoginStatusField) {
		PlayerLoginStatusField = playerLoginStatusField;
	}

	public String getPlayerLocTable() {
		return PlayerLocTable;
	}

	public void setPlayerLocTable(String playerLocTable) {
		PlayerLocTable = playerLocTable;
	}

	public String getPlayerLocworld() {
		return PlayerLocworld;
	}

	public void setPlayerLocworld(String playerLocworld) {
		PlayerLocworld = playerLocworld;
	}

	public String getPlayerLocx() {
		return PlayerLocx;
	}

	public void setPlayerLocx(String playerLocx) {
		PlayerLocx = playerLocx;
	}

	public String getPlayerLocy() {
		return PlayerLocy;
	}

	public void setPlayerLocy(String playerLocy) {
		PlayerLocy = playerLocy;
	}

	public String getPlayerLocz() {
		return PlayerLocz;
	}

	public void setPlayerLocz(String playerLocz) {
		PlayerLocz = playerLocz;
	}

	public String getPlayerLocpitch() {
		return PlayerLocpitch;
	}

	public void setPlayerLocpitch(String playerLocpitch) {
		PlayerLocpitch = playerLocpitch;
	}

	public String getPlayerLocyaw() {
		return PlayerLocyaw;
	}

	public void setPlayerLocyaw(String playerLocyaw) {
		PlayerLocyaw = playerLocyaw;
	}

	public int getPlayerNameMinLength() {
		return PlayerNameMinLength;
	}

	public void setPlayerNameMinLength(int playerNameMinLength) {
		PlayerNameMinLength = playerNameMinLength;
	}

	public boolean isEnable_PlayerLoginedEffect() {
		return Enable_PlayerLoginedEffect;
	}

	public void setEnable_PlayerLoginedEffect(boolean enable_PlayerLoginedEffect) {
		Enable_PlayerLoginedEffect = enable_PlayerLoginedEffect;
	}

	public boolean isEnable_PlayerPreLoginEffect() {
		return Enable_PlayerPreLoginEffect;
	}

	public void setEnable_PlayerPreLoginEffect(boolean enable_PlayerPreLoginEffect) {
		Enable_PlayerPreLoginEffect = enable_PlayerPreLoginEffect;
	}

	public boolean isEnable_CreativeModeSecurity() {
		return Enable_CreativeModeSecurity;
	}

	public void setEnable_CreativeModeSecurity(boolean enable_CreativeModeSecurity) {
		Enable_CreativeModeSecurity = enable_CreativeModeSecurity;
	}

	public String getEnable_passwordHash() {
		return Enable_passwordHash;
	}

	public void setEnable_passwordHash(String enable_passwordHash) {
		Enable_passwordHash = enable_passwordHash;
	}


	public boolean isEnable_SupportOldPassword() {
		return Enable_SupportOldPassword;
	}

	public void setEnable_SupportOldPassword(boolean enable_SupportOldPassword) {
		Enable_SupportOldPassword = enable_SupportOldPassword;
	}

	public boolean isEnable_DebugPrint() {
		return Enable_DebugPrint;
	}

	public void setEnable_DebugPrint(boolean enable_DebugPrint) {
		Enable_DebugPrint = enable_DebugPrint;
	}
	
	
}
