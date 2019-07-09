package com.ksptooi.FL.Data.Config.Entity;

public class Language {

	
	private String Notlogin="§e[FastLogin]§b你需要登录后才能操作,使用/login 密码 来登录";
	
	private String NotRegister="§e[FastLogin]§b你需要注册后才能操作,使用/register 密码 确认密码 来注册";
	
	private String LoginTimeOutKick="登录超时";
	
	private String RepeatLogin="§e[FastLogin]§c你已经登录了!";
	
	private String RepeatRegister="§e[FastLogin]§c你已经注册了!";
	
	private String NotRegister2="§e[FastLogin]§c你还没有注册！";
	
	private String LoginSuccess="§e[FastLogin]§a登录成功！";
	
	private String PasswordError="§e[FastLogin]§c密码错误！";
	
	private String RegisterSuccess="§e[FastLogin]§a注册完成！";
	
	private String NullPassword="§e[FastLogin]§c请输入密码！";
	
	private String PasswdTooLong="§e[FastLogin]§c密码长度超过最大限制！";
	
	private String PasswdTooShost="§e[FastLogin]§c密码太短！";
	
	private String NoConfirmPasswd="§e[FastLogin]§c请输入确认密码 例:/register 12345 12345";
	
	private String ConfirmPasswdError="§e[FastLogin]§c两次输入的密码不一致";
	
	private String JoinGameError1="登录失败:相同用户名的玩家已经在游戏中!"; 
	
	private String ChangePwUsage="§e[FastLogin]§b修改密码 - 用法:/ChangePassword 旧密码 新密码 确认新密码";
	
	private String ChangePw_OldpwErr="§e[FastLogin]§c修改密码 - 失败:旧密码错误.";
	
	private String ChangePw_ConfirmPwError="§e[FastLogin]§c修改密码 - 失败:两次输入的确认密码不一致.";
	
	private String ReModifyPasswd="§e[FastLogin]§c修改密码 - 失败:新密码不能和旧密码一样.";
	
	private String ChangePw_PasswdTooLong="§e[FastLogin]§c修改密码 - 失败:新密码超过长度限制.";
	
	private String ChangePw_PasswdTooShost="§e[FastLogin]§c修改密码 - 失败:新密码太短.";
	
	private String ChangePw_Success="§e[FastLogin]§a修改密码 - 完成!";
	
	private String LoginOut="§e[FastLogin]§c·登录已经被注销!";
	
	//new V1
	
	
	private String AdminSetPasswordError1="§e[FastLogin]§c此玩家未注册,修改密码失败!";
	
	private String AdminSetPasswordUsage="§e[FastLogin]§b使用方法:/Fast SetPassword 玩家名 新密码";
	
	private String AdminSetPasswordSuccess="§e[FastLogin]§a成功修改此玩家密码!";
	
	private String AdminSetPasswordKick="密码被管理员修改!";
	
	private String AdminCommandHelp1="§b[FastLogin]·OP命令用法:";
	
	private String AdminCommandHelp2="§6/Fast Reload§f: 重载插件的配置文件";
	
	private String AdminCommandHelp3="§6/Fast setSpawn§f: 设置初始登录地点";
	
	private String AdminCommandHelp4="§6/Fast goSpawn§f: 传送到初始登录地点";
	
	private String AdminCommandHelp5="§6/Fast delSpawn§f: 删除初始登录地点";
	
	private String AdminCommandHelp6="§6/Fast setPassword§f: 设置玩家的密码";
	
	private String OPHasbeenCanceld="§e[FastLoginSecurity]§c·由于你未登录,你的OP权限已被取消!";
	
	private String CreativeModeHasbeenCanceld="§e[FastLoginSecurity]§c·由于你未登录,你的创造模式已被取消!";
	
	private String OPRestore="§e[FastLoginSecurity]§a·你的OP权限已经恢复!";
	
	private String CreativeModeRestore="§e[FastLoginSecurity]§a·你的创造模式已经恢复!";
	
	private String setSpawnSuccess="§e[FastLogin]§a·初始登录点已设置!";
	
	private String DeleteSpawnSuccess="§e[FastLogin]§a·初始登录点已删除!";
	
	private String TPSpawnSuccess="§e[FastLogin]§6·正在传送...";
	
	private String OPHasBeenCleared="§e[FastLoginSecurity]§c·由于你没有注册,你的OP已被清除..";
	
	private String CreativeHasBeenCleared="§e[FastLoginSecurity]§c·由于你没有注册,你的OP已被清除..";


	
	public String getNotlogin() {
		return Notlogin;
	}

	public void setNotlogin(String notlogin) {
		Notlogin = notlogin;
	}

	public String getNotRegister() {
		return NotRegister;
	}

	public void setNotRegister(String notRegister) {
		NotRegister = notRegister;
	}

	public String getLoginTimeOutKick() {
		return LoginTimeOutKick;
	}

	public void setLoginTimeOutKick(String loginTimeOutKick) {
		LoginTimeOutKick = loginTimeOutKick;
	}

	public String getRepeatLogin() {
		return RepeatLogin;
	}

	public void setRepeatLogin(String repeatLogin) {
		RepeatLogin = repeatLogin;
	}

	public String getRepeatRegister() {
		return RepeatRegister;
	}

	public void setRepeatRegister(String repeatRegister) {
		RepeatRegister = repeatRegister;
	}

	public String getNotRegister2() {
		return NotRegister2;
	}

	public void setNotRegister2(String notRegister2) {
		NotRegister2 = notRegister2;
	}

	public String getLoginSuccess() {
		return LoginSuccess;
	}

	public void setLoginSuccess(String loginSuccessful) {
		LoginSuccess = loginSuccessful;
	}

	public String getPasswordError() {
		return PasswordError;
	}

	public void setPasswordError(String passwordError) {
		PasswordError = passwordError;
	}

	public String getRegisterSuccess() {
		return RegisterSuccess;
	}

	public void setRegisterSuccess(String registerSuccessful) {
		RegisterSuccess = registerSuccessful;
	}

	public String getNullPassword() {
		return NullPassword;
	}

	public void setNullPassword(String nullPassword) {
		NullPassword = nullPassword;
	}

	public String getPasswdTooLong() {
		return PasswdTooLong;
	}

	public void setPasswdTooLong(String PasswdMax) {
		this.PasswdTooLong = PasswdMax;
	}

	public String getPasswdTooShost() {
		return PasswdTooShost;
	}

	public void setPasswdTooShost(String PasswdMin) {
		this.PasswdTooShost = PasswdMin;
	}

	public String getNoConfirmPasswd() {
		return NoConfirmPasswd;
	}

	public void setNoConfirmPasswd(String noConfirmPasswd) {
		NoConfirmPasswd = noConfirmPasswd;
	}

	public String getConfirmPasswdError() {
		return ConfirmPasswdError;
	}

	public void setConfirmPasswdError(String confirmPasswdError) {
		ConfirmPasswdError = confirmPasswdError;
	}

	public String getJoinGameError1() {
		return JoinGameError1;
	}

	public void setJoinGameError1(String joinGameError1) {
		JoinGameError1 = joinGameError1;
	}

	public String getChangePwUsage() {
		return ChangePwUsage;
	}

	public void setChangePwUsage(String modifyPwUsage) {
		ChangePwUsage = modifyPwUsage;
	}

	public String getChangePw_OldpwErr() {
		return ChangePw_OldpwErr;
	}

	public void setChangePw_OldpwErr(String modifyOldpwErr) {
		ChangePw_OldpwErr = modifyOldpwErr;
	}

	public String getChangePw_ConfirmPwError() {
		return ChangePw_ConfirmPwError;
	}

	public void setChangePw_ConfirmPwError(String modifyConfirmError) {
		ChangePw_ConfirmPwError = modifyConfirmError;
	}

	public String getReModifyPasswd() {
		return ReModifyPasswd;
	}

	public void setReModifyPasswd(String reModifyPasswd) {
		ReModifyPasswd = reModifyPasswd;
	}

	public String getChangePw_PasswdTooLong() {
		return ChangePw_PasswdTooLong;
	}

	public void setChangePw_PasswdTooLong(String modifyPasswdLengthMax) {
		ChangePw_PasswdTooLong = modifyPasswdLengthMax;
	}

	public String getChangePw_PasswdTooShost() {
		return ChangePw_PasswdTooShost;
	}

	public void setChangePw_PasswdTooShost(String modifyPasswdLengthMin) {
		ChangePw_PasswdTooShost = modifyPasswdLengthMin;
	}

	public String getChangePw_Success() {
		return ChangePw_Success;
	}

	public void setChangePw_Success(String modifySuccessful) {
		ChangePw_Success = modifySuccessful;
	}

	public String getLoginOut() {
		return LoginOut;
	}

	public void setLoginOut(String loginOut) {
		LoginOut = loginOut;
	}

	public String getAdminSetPasswordKick() {
		return AdminSetPasswordKick;
	}

	public void setAdminSetPasswordKick(String adminSetPasswordKick) {
		AdminSetPasswordKick = adminSetPasswordKick;
	}

	public String getAdminCommandHelp1() {
		return AdminCommandHelp1;
	}

	public void setAdminCommandHelp1(String adminCommandHelp1) {
		AdminCommandHelp1 = adminCommandHelp1;
	}

	public String getAdminCommandHelp2() {
		return AdminCommandHelp2;
	}

	public void setAdminCommandHelp2(String adminCommandHelp2) {
		AdminCommandHelp2 = adminCommandHelp2;
	}

	public String getAdminCommandHelp3() {
		return AdminCommandHelp3;
	}

	public void setAdminCommandHelp3(String adminCommandHelp3) {
		AdminCommandHelp3 = adminCommandHelp3;
	}

	public String getAdminCommandHelp4() {
		return AdminCommandHelp4;
	}

	public void setAdminCommandHelp4(String adminCommandHelp4) {
		AdminCommandHelp4 = adminCommandHelp4;
	}

	public String getAdminCommandHelp5() {
		return AdminCommandHelp5;
	}

	public void setAdminCommandHelp5(String adminCommandHelp5) {
		AdminCommandHelp5 = adminCommandHelp5;
	}

	public String getAdminCommandHelp6() {
		return AdminCommandHelp6;
	}

	public void setAdminCommandHelp6(String adminCommandHelp6) {
		AdminCommandHelp6 = adminCommandHelp6;
	}

	public String getOPHasbeenCanceld() {
		return OPHasbeenCanceld;
	}

	public void setOPHasbeenCanceld(String oPHasbeenCanceld) {
		OPHasbeenCanceld = oPHasbeenCanceld;
	}

	public String getCreativeModeHasbeenCanceld() {
		return CreativeModeHasbeenCanceld;
	}

	public void setCreativeModeHasbeenCanceld(String creativeModeHasbeenCanceld) {
		CreativeModeHasbeenCanceld = creativeModeHasbeenCanceld;
	}

	public String getOPRestore() {
		return OPRestore;
	}

	public void setOPRestore(String oPRestore) {
		OPRestore = oPRestore;
	}

	public String getCreativeModeRestore() {
		return CreativeModeRestore;
	}

	public void setCreativeModeRestore(String creativeModeRestore) {
		CreativeModeRestore = creativeModeRestore;
	}

	public String getSetSpawnSuccess() {
		return setSpawnSuccess;
	}

	public void setSetSpawnSuccess(String setSpawnSuccess) {
		this.setSpawnSuccess = setSpawnSuccess;
	}

	public String getDeleteSpawnSuccess() {
		return DeleteSpawnSuccess;
	}

	public void setDeleteSpawnSuccess(String deleteSpawnSuccess) {
		DeleteSpawnSuccess = deleteSpawnSuccess;
	}

	public String getTPSpawnSuccess() {
		return TPSpawnSuccess;
	}

	public void setTPSpawnSuccess(String tPSpawnSuccess) {
		TPSpawnSuccess = tPSpawnSuccess;
	}

	public String getAdminSetPasswordError1() {
		return AdminSetPasswordError1;
	}

	public void setAdminSetPasswordError1(String adminSetPasswordError1) {
		AdminSetPasswordError1 = adminSetPasswordError1;
	}

	public String getAdminSetPasswordUsage() {
		return AdminSetPasswordUsage;
	}

	public void setAdminSetPasswordUsage(String adminSetPasswordUsage) {
		AdminSetPasswordUsage = adminSetPasswordUsage;
	}

	public String getAdminSetPasswordSuccess() {
		return AdminSetPasswordSuccess;
	}

	public void setAdminSetPasswordSuccess(String adminSetPasswordSuccess) {
		AdminSetPasswordSuccess = adminSetPasswordSuccess;
	}

	public String getOPHasBeenCleared() {
		return OPHasBeenCleared;
	}

	public void setOPHasBeenCleared(String oPHasBeenCleared) {
		OPHasBeenCleared = oPHasBeenCleared;
	}

	public String getCreativeHasBeenCleared() {
		return CreativeHasBeenCleared;
	}

	public void setCreativeHasBeenCleared(String creativeHasBeenCleared) {
		CreativeHasBeenCleared = creativeHasBeenCleared;
	}
	
	
	
}
