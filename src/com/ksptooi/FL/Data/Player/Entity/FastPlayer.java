package com.ksptooi.FL.Data.Player.Entity;

import java.net.InetSocketAddress;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Hash.PasswordHash;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Event.FastEvent.PlayerTelemetryEvent;
import com.ksptooi.FL.PAsync.Task.AsyncTask;
import com.ksptooi.FL.Player.Check.PlayerPasswordRuleCheck;
import com.ksptooi.FL.Player.Effect.PlayerEffectManager;
import com.ksptooi.FL.Util.Logger;

public class FastPlayer{

	
	private Player bukkitPlayer = null;
	
	private PlayerData playerData = null;
	
	private PlayerDataManager playerDataManager = null;
	
	private AsyncTask asyncTask = null;
	
	private PlayerEffectManager effectManager = null;
	
	private PasswordHash passwordHash = null;
	
	
	public FastPlayer(Player pl) {
			
		this.bukkitPlayer = pl;
		
		this.playerDataManager = DataManager.getPlayerDataManager();		
		
		this.asyncTask = FastLogin.getAsyncTask();
		
		this.effectManager = FastLogin.getPlayerEffectManager();
		
		this.passwordHash = DataManager.getAdvPasswordHash();
		
		
		//获取数据	
		playerData = playerDataManager.getPlayerData(pl);
		
	}
	
	
	
	//创建玩家数据文件(如果存在则没有操作)
	public void createData() {		
		
		playerDataManager.createPlayerData(bukkitPlayer.getName());
		
	}
	
	//从文件/缓存 刷新玩家的数据文件
	public void reload() {
		playerData = playerDataManager.getPlayerData(bukkitPlayer);
	}
	
	
	//保存玩家数据文件至文件系统
	public void save() {		
		playerDataManager.updatePlayerData(playerData);		
	}
	
	
	//保存玩家的最后退出位置
	public void saveQuitLocation() {
				
		playerData.setLastQuitLocation(bukkitPlayer.getLocation());		
		
	}
	
	//设置登陆状态
	public void setLogin(boolean bool) {
		this.playerData.setLogin(bool);
	}
		
	//设置密码
	public void setPassword(String str) {
		this.playerData.setPassword(str);
	}
	
	//设置注册状态
	public void setRegister(boolean bool) {
		this.playerData.setRegister(bool);
	}
	
	//添加登陆粒子效果过
	public void addLoginedEffect() {
		this.effectManager.addLoginedEffect(bukkitPlayer);
	}
	
	//获取密码
	public String getPassword() {
		this.reload();
		return this.playerData.getPassword();
	}
	
	
	//添加登陆失明效果
	public void addPreLoginEffect() {
		this.effectManager.addPreLoginEffect(bukkitPlayer);
	}
	
	//移除登陆失明效果
	public void removePreLoginEffect() {
		this.effectManager.removePreLoginEffect(bukkitPlayer);
	}
	
	
	
	public boolean isLogin() {
		this.reload();
		return playerData.isLogin();
	}
	
	public boolean isRegister() {
		this.reload();
		return playerData.isRegister();
	}
	
	
	
	//传送玩家到服务器设定的默认登陆位置
	public boolean tpFastSpawn(){
		
		
		if(! ConfigManager.getLocation().getLocation_world().equalsIgnoreCase("empty")){
			
			asyncTask.taskTp(bukkitPlayer, ConfigManager.getLocation().getLoginLocation());
					
			return true;
			
		}	
		
		return false;
		
	}
	
	//传送玩家到最后下线的地方
	public void tpLastQuitLocation() {
		
		this.asyncTask.taskTp(bukkitPlayer, playerData.getLastQuitLocation());
		
	}
	
	
	//判断是否为影分身
	public boolean isGhostPlayer(){
		
		int i=0;

		
		for(Player p:Bukkit.getServer().getOnlinePlayers()){
			
			if(p.getName().equalsIgnoreCase(bukkitPlayer.getName())){
				i++;
			}
			
		}
		
		
		if(i>1){
			return true;
		}
		
		return false;
		
	}
	
	
	//校验玩家的密码
	public boolean isRightPassword(String password){
			
		String Hash = ConfigManager.getConfig().getEnable_passwordHash();		
		
		Boolean isSupportOldpwd = ConfigManager.getConfig().isEnable_SupportOldPassword();
		
		String SaltPassword = password;
		
		Logger logger = FastLogin.getLoggerr();
		
			
		//使用MD5
		if(Hash.equalsIgnoreCase("MD5")){
			
			logger.DM("使用MD5加密");
						
			
			if(passwordHash.autoCompression(SaltPassword).equals(playerData.getPassword())){
				logger.DM("密码正确");
				return true;
			}
			
			
			//判断是否支持旧密码
			if(! isSupportOldpwd){
				logger.DM("密码错误");
				return false;
			}
			
			logger.DM("支持使用旧密码");
			
			
			//启用旧密码支持
			if(password.equals(playerData.getPassword())){
				logger.DM("旧密码正确");			
				this.updatePlayerPassword(playerData,SaltPassword);
				return true;
				
			}
			
			logger.DM("旧密码错误");		
			return false;
		}

		
		
		//没有加密算法 - false & Other	
		if(SaltPassword.equals(playerData.getPassword())){
			return true;
		}
		
		return false;	
			
		
	}
	
	
	/**用于升级玩家的旧密码**/
	public void updatePlayerPassword(PlayerData PDE,String Password){
				
		PDE.setPassword(passwordHash.autoCompression(Password));
		this.save();

	}
	
	
	/**
	 * 更改玩家的密码
	 * 
	 * @param PlayerData 玩家实例
	 * @param OldPasswd 原密码
	 * @param NewPasswd 新密码
	 * @param ConfirmNewPasswd 确认新密码
	 */
	public void ChangePasswd(String OldPasswd, String NewPasswd,String ConfirmNewPasswd) {
		
		PlayerPasswordRuleCheck passwordRuleCheck = DataManager.getPlayerPasswordRuleCheck();
		
		//判断有无加密
		if(ConfigManager.getConfig().getEnable_passwordHash().equalsIgnoreCase("md5")){
			this.ChangePasswdMD5(OldPasswd, NewPasswd, ConfirmNewPasswd);
			return;
		}	
		
		this.reload();
			
		//检查密码规则
		if(!passwordRuleCheck.isValid(this, OldPasswd, NewPasswd, ConfirmNewPasswd)) {
			return;
		}
		

			
		/** 更改密码 - 开始 **/
		this.setPassword(NewPasswd);
		this.setLogin(false);
		
		this.sendMessage(ConfigManager.getLanguage().getChangePw_Success());
		this.sendMessage(ConfigManager.getLanguage().getLoginOut());
		
		//同步至数据库
		this.save();
		
		
		PlayerTelemetryEvent event=new PlayerTelemetryEvent(this);
		FastLogin.getEventManager().runFastEvent(event);
		
	}

	
	/**
	 * 更改玩家的密码 - 加密适用
	 */
	
	public void ChangePasswdMD5(String OldPasswd, String NewPasswd,String ConfirmNewPasswd) {
		
		PlayerPasswordRuleCheck passwordRuleCheck = DataManager.getPlayerPasswordRuleCheck();
		
		this.reload();
		
		//检查密码规则
		if(!passwordRuleCheck.isValidMD5(this, OldPasswd, NewPasswd, ConfirmNewPasswd)) {
			return;
		}
		
		/** 更改密码 - 开始 **/
		
		this.setPassword(passwordHash.autoCompression(NewPasswd));
		this.setLogin(false);
		
		this.sendMessage(ConfigManager.getLanguage().getChangePw_Success());
		this.sendMessage(ConfigManager.getLanguage().getLoginOut());
		
		//同步至数据库
		this.save();
			
		PlayerTelemetryEvent event=new PlayerTelemetryEvent(this);
		FastLogin.getEventManager().runFastEvent(event);
		
	}
	
	
	//判断玩家是否为真的玩家 而不是其他实体
	public boolean isRealPlayer(){
		
		String Address=null;
		
		try{
			
			Address=this.getAddress().getHostName();
			
			if(Address == null) {
				return false;
			}
			
			return true;
			
			
		}catch(Exception exc){
			return false;
		}
			
	}
	
	
	
	/**
	 * 
	 * BUKKIT的方法封装
	 * 
	 */
	
	
	//获取Location
	public Location getLocation() {
		return bukkitPlayer.getLocation();
	}
	
	
	//是否是OP
	public boolean isOp() {
		return bukkitPlayer.isOp();
	}
	
	
	//是否在线
	public boolean isOnline() {
		return bukkitPlayer.isOnline();
	}
	
	//获取名字
	public String getName() {
		return bukkitPlayer.getName();
	}
	
	//获取地址
	public InetSocketAddress getAddress() {
		return bukkitPlayer.getAddress();
	}
	
	
	//向该玩家发送一条消息(可用于异步)
	public void sendMessage(String str) {
		this.asyncTask.taskMessage(bukkitPlayer, str);
	}
	
	//踢出该玩家(可用于异步)
	public void kickPlayer(String message) {
		this.asyncTask.taskKick(bukkitPlayer, message);
	}
	
	//设置OP(可用于异步)
	public void setOp(boolean bool) {
		this.asyncTask.taskSetOP(bukkitPlayer, bool);
	}
	
	//获取游戏模式
	public GameMode getGameMode() {
		return bukkitPlayer.getGameMode();
	}
	
	//设置游戏模式
	public void setGameMode(int i) {
		this.asyncTask.taskSetGameMode(bukkitPlayer,i);
	}
	
}
