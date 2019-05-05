package us.ktmc.util_interface;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.ktmc.Exception.PasswdRulesException;
import us.ktmc.Exception.PlayerNotRegisterException;
import us.ktmc.player.PlayerProcessThread;
import us.ktmc.util.Language;
import us.ktmc.util.Var;


public class GeneralUtil implements FastUtil{

	
	
	public String getFileErrorText(){
		return "[严重]·出现文件系统错误!";	
	}
	
	/**
	 * 发送一个OP命令帮助至控制台
	 * @param 控制台实例
	 */
	public void SendOPHelp(CommandSender sender){
		
		sender.sendMessage("§b[FastLogin]·命令用法:");
		sender.sendMessage("§e/Fast Reload - 重载插件配置文件");
		sender.sendMessage("§e/Fast set    - 设置一个登录传送点");
		sender.sendMessage("§e/Fast go     - 传送到你设置的登录传送点");
		sender.sendMessage("§e/Fast del    - 删除你的传送点");
		sender.sendMessage("§e/Fast reset  - 重新设定某玩家的密码");
		
	}
	
	
	
	/**
	 * 用于判断命令被玩家执行或者是控制台执行
	 * 
	 * @param sender 命令执行人
	 * @return true 控制台执行了玩家命令 false 玩家执行了命令
	 */
	
	public boolean PlayerCommandExecuteAtConsole(CommandSender sender) {
		
		if(!(sender instanceof Player)){
			System.out.println("[FastLogin]·控制台不能执行此类命令");
			return true;	
		}
		
		return false;
			
	}

	/**
	 * 获取玩家的数据文件
	 * 
	 * @param PlayerEntity 玩家实例
	 * @return 返回一个玩家数据文件
	 */
	public File getPlDataFile(Player PlayerEntity) {
		return getPlDataFile(PlayerEntity.getName());
	}

	public File getPlDataFile(String PlayerName) {
		return new File(FastUtil.PlayerDataDir+PlayerName.toLowerCase()+".gd");
		
	}

	/**
	 * 判断玩家数据文件是否存在
	 * 
	 * @param PlayerEntity 玩家实例 
	 * @param PlayerName 玩家名称
	 * @return 如果为true则玩家文件存在
	 */	
	public boolean DataFileIsExists(Player PlayerEntity) {
		return DataFileIsExists(PlayerEntity.getName());
	}

	public boolean DataFileIsExists(String PlayerName) {
		return new File(FastUtil.PlayerDataDir+PlayerName.toLowerCase()+".gd").exists();
	}

	/**
	 * 判断玩家是否注册
	 * 
	 * @param PlayerEntity 玩家实例 
	 * @param PlayerName 玩家名称
	 * @return 如果为True则玩家已注册.
	 */	
	public boolean isReg(Player PlayerEntity) {	
		return isReg(PlayerEntity.getName());
	}

	public boolean isReg(String PlayerName) {
		
		File PlayerConfig=getPlDataFile(PlayerName);
		
		if(!(PlayerConfig.exists())){
			return false;
		}
		
		try{
			
			if(Var.V2.getKeyValue(PlayerConfig, "register=").equalsIgnoreCase("1"))
				return true;
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(getFileErrorText());
		}

			
		return false;
	}


	/**
	 * 设置一个默认的上线位置。玩家上线后将会被传送到此处。
	 * @param WorldName 世界名
	 * @param X X坐标
	 * @param Y Y坐标
	 * @param Z Z坐标
	 * @param Pitch 身体朝向
	 * @param Yaw 头部仰角
	 * 	
	 */
	public void SetDefaultLoginLocation(String WorldName, int X, int Y, int Z, int Pitch, int Yaw) {
		
		try {
			
			
			Var.V2.WriteAtKey(Mconf,"location.world=", WorldName);
			Var.V2.WriteAtKey(Mconf,"location.x=", ""+X);
			Var.V2.WriteAtKey(Mconf,"location.y=", ""+Y);
			Var.V2.WriteAtKey(Mconf,"location.z=", ""+Z);
			Var.V2.WriteAtKey(Mconf,"location.pitch=", ""+Pitch);
			Var.V2.WriteAtKey(Mconf,"location.yaw=", ""+Yaw);
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println(getFileErrorText());
			
		}
				
	}


	/**
	 * 删除设置的默认登陆位置
	 * @return 如果成功删除 返回True 删除失败返回 Flase
	 */
	public boolean DeleteDefaultLoginLocation() {
			
		try{			
	
		Var.V2.WriteAtKey(Mconf,"location.world=","empty");
		Var.V2.WriteAtKey(Mconf,"location.x=", "empty");
		Var.V2.WriteAtKey(Mconf,"location.y=", "empty");
		Var.V2.WriteAtKey(Mconf,"location.z=", "empty");
		Var.V2.WriteAtKey(Mconf,"location.pitch=", "empty");
		Var.V2.WriteAtKey(Mconf,"location.yaw=", "empty");
		return true;
		
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.println(getFileErrorText());
			return false;
			
		}
					
	}


	/**
	 * 重新设置玩家的密码！(此方法遵守通用密码设置规则)
	 * @param PlayerEntity 玩家实体
	 * @param Passwd 要设置的密码
	 * @throws IOException 文件系统出错时抛出此异常
	 * @throws PasswdRulesException 当玩家密码不符合要求时抛出此异常！(例如超过最大长度.)
	 * @throws PlayerNotRegisterException 当玩家未注册时抛出此异常
	 */
	public void ReSetPasswd(Player PlayerEntity,String Passwd) throws PlayerNotRegisterException, PasswdRulesException, IOException {
		ReSetPasswd(PlayerEntity.getName(),Passwd);
	}



	public void ReSetPasswd(String PlayerName,String Passwd) throws PlayerNotRegisterException, PasswdRulesException, IOException {
		
		if(!(isReg(PlayerName))){
			throw new PlayerNotRegisterException();
		}
		
		PasswdIsAvailable(Passwd);
		
		Var.V2.WriteAtKey(getPlDataFile(PlayerName), "password=", Passwd);
		
	}


	
	/**
	 * 检查玩家的密码是否符合规则
	 * 
	 * @param Passwd 要检查的密码
	 * @throws PasswdRulesException 当玩家密码不符合要求时抛出此异常！(例如超过最大长度.)
	 */
	public void PasswdIsAvailable(String Passwd) throws PasswdRulesException{
		
		if(Passwd.length()>Var.PasswordMaxLength){
			throw new PasswdRulesException(true,false);
		}
		
		if(Passwd.length()<Var.PasswordMinLength){
			throw new PasswdRulesException(false,true);
		}
		
		
	}

	/**
	 * 判断玩家输入的密码是否正确
	 * 
	 * @param PlayerEntity 玩家实例
	 * @param Passwd 要判断的密码
	 */
	public boolean isPassword(Player PlayerEntity,String Passwd){
		
		File PlConf=getPlDataFile(PlayerEntity);
		
		try {
			
			if(Var.V2.getKeyValue(PlConf, "password=").equals(Passwd)){
				return true;
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			PlayerEntity.sendMessage(getFileErrorText());
			
		}
		
		
		return false;
		
	}
	
	
	/**
	 * 更改玩家的密码
	 * 
	 * @param PlayerEntity 玩家实例
	 * @param OldPasswd 原密码
	 * @param NewPasswd 新密码
	 * @param ConfirmNewPasswd 确认新密码
	 */
	public void ChangePasswd(Player PlayerEntity, String OldPasswd, String NewPasswd,String ConfirmNewPasswd) {
		
		//判断输入的旧密码是否正确
		if(!(isPassword(PlayerEntity,OldPasswd))){
			PlayerEntity.sendMessage(Language.ModifyOldpwErr);
			return;
		}
		
		//判断两次输入的密码是否一致
		if(!(NewPasswd.equals(ConfirmNewPasswd))){
			PlayerEntity.sendMessage(Language.ModifyConfirmError);
			return;
		}
		
		//判断新密码是否与旧密码一致
		if(NewPasswd.equalsIgnoreCase(OldPasswd)){
			PlayerEntity.sendMessage(Language.ReModifyPasswd);
			return;
		}
		
		/** 更改密码 - 开始 **/
		try {
			
			Var.V2.WriteAtKey(getPlDataFile(PlayerEntity),"password=", NewPasswd);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			PlayerEntity.sendMessage(getFileErrorText());
			
		}
		
		PlayerEntity.sendMessage(Language.ModifySuccessful);
		PlayerEntity.sendMessage(Language.LoginOut);
		
		setPlayerLogin(PlayerEntity, false);
		new Thread(new PlayerProcessThread(PlayerEntity)).start();
		
		/** 更改密码 - 结束 **/
	}
	
	
	/**
	 * 判断玩家是否登录
	 * 
	 * @param PlayerEntity 玩家实例
	 * @return 玩家已登录返回True 当玩家未注册时返回False 玩家未登录时返回False
	 */
	public boolean isLogin(Player PlayerEntity){
		
		if(!(isReg(PlayerEntity))){
			return false;
		}
		
		try {
			
			if(Var.V2.getKeyValue(getPlDataFile(PlayerEntity), "login=").equalsIgnoreCase("1"))
				return true;
			
		} catch (IOException e) {
			
			e.printStackTrace();
			PlayerEntity.sendMessage(getFileErrorText());
			
		}
				
		return false;
	
	}



	/**
	 * 将玩家传送到默认登陆位置
	 * 
	 * @param PlayerEntity 玩家实例
	 * @return 成功传送返回True 默认登陆位置不存在返回False
	 */
	public boolean TelePort_DefaultLoginLocation(Player PlayerEntity) {
		
		if(!(Var.Location_world.equalsIgnoreCase("empty"))){
			
			PlayerEntity.teleport(Var.LoginLocation);
			return true;
			
		}
				
		return false;
	}

	
	/**
	 * 设置玩家登录状态
	 * 
	 * @param Player 玩家实例
	 * @param isLogin 设置是否登录 如果值为True则将玩家文件同步至已登录反则未登录
	 */
	public void setPlayerLogin(Player Player, Boolean isLogin) {
		
		try {
			
			if (isLogin) {

				Var.V2.WriteAtKey(getPlDataFile(Player), "login=", "1");
				return;
				
			}
			
			Var.V2.WriteAtKey(getPlDataFile(Player), "login=", "0");

		} catch (Exception e) {
			e.printStackTrace();
			Player.sendMessage(getFileErrorText());

		}
		
	}

	/**
	 * 创建一个玩家数据文件,当文件已存在时不执行任何操作。
	 * 
	 * @param Player 玩家实体
	 * @throws Exception 数据文件出错时抛出本异常
	 */
	public void CreatePlayerData(Player Player) throws Exception {
		
		File Conf=getPlDataFile(Player);
		
		File Dir=new File("plugins/ksptooi/fastlogin/database/");
		
		Var.V3.setTargetFile(Conf);
		
		if(!Dir.exists()){
			Dir.mkdirs();
		}
		
		if(!(Conf.exists())){
			
			Conf.createNewFile();
					
			System.out.println("[FastLogin]·为玩家创建新的数据文件:"+Conf.getName());
			
			Var.V2.OutputToFile(Conf, "playername="+Player.getName(), "UTF-8");
			Var.V3.AddtoFile("\r\npassword=password");
			Var.V3.AddtoFile("\r\nregister=0");
			Var.V3.AddtoFile("\r\nlogin=0");
			Var.V3.AddtoFile("\r\nloc.world=empty");
			Var.V3.AddtoFile("\r\nloc.x=empty");
			Var.V3.AddtoFile("\r\nloc.y=empty");
			Var.V3.AddtoFile("\r\nloc.z=empty");
			Var.V3.AddtoFile("\r\nloc.pitch=empty");
			Var.V3.AddtoFile("\r\nloc.yaw=empty");
			
			
		}
		
		setPlayerLogin(Player, false);
		
	}

	/**
	 * 用于处理玩家名称 当名称不合法时直接踢出玩家
	 * 
	 * @param Player 玩家实体
	 * @return 如果名称合法返回True 不合法返回False并直接踢出玩家
	 */
	public boolean PlayerNameProcess(Player Player) {
		
		//判断玩家名称长度
		if (Player.getName().length() < Var.PlayerNameMinLength) {
			Player.kickPlayer("玩家名过短 最小需要" + Var.PlayerNameMinLength + "个字符");
			return false;
		}
		//End

		
		//以自定义正则表达式判断玩家名称 Start
		if(!Var.RegexMatchForPlayerName.equalsIgnoreCase("*")){
			
			if(!Player.getName().matches(Var.RegexMatchForPlayerName)){
				Player.kickPlayer("玩家名称不符合要求！");			
				return false; 
			}
			
			
		}	
		//End
		
		
		
		//以严格模式判断玩家名称 Start
		if (Var.Enable_UserNameStrictmode){
			
			String regex = "([A-Z]|[a-z]|[0-9]|-|_){1,}";
	
			if (!Player.getName().matches(regex)) {
				
				Player.kickPlayer("玩家名不符合要求,玩家名只能由A-Z 0-9与下划线组成,不得含有空格与特殊符号!");		
				return false; 
				
			}		
		
		}
		//End
	    	    
		//判断玩家名称是否含有被服务器禁用的字符
		for(String str:Var.BanName){
			
			if(Player.getName().toLowerCase().contains(str.toLowerCase())){
				
				Player.kickPlayer("玩家名含有本服务器禁用的关键字:"+str);
				return false;
				
			}
			
		}
		//End
		
		return true;
	}

	/**
	 * 将玩家当前的位置保存到数据文件 作为最后下线位置使用
	 * 
	 * @param Player 玩家实体
	 */
	public void setPlayerQuitLocation(Player Player) {
		
		Location loc=Player.getLocation();
		
		Var.V3.setTargetFile(getPlDataFile(Player));
		
		try {
			
			Var.V3.WriteAtKey("loc.world=", loc.getWorld().getName());
			Var.V3.WriteAtKey("loc.x=", ""+(int)loc.getX());
			Var.V3.WriteAtKey("loc.y=", ""+(int)loc.getY());
			Var.V3.WriteAtKey("loc.z=", ""+(int)loc.getZ());
			Var.V3.WriteAtKey("loc.pitch=", ""+(int)loc.getPitch());
			Var.V3.WriteAtKey("loc.yaw=", ""+(int)loc.getYaw());
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println(getFileErrorText());
			
		}
		
	}

	/**
	 * 从数据文件获取玩家最后退出的位置
	 * 
	 * @param Player 玩家实体
	 * @return 玩家最后退出的位置 如果没有,则返回null
	 * @throws Exception 文件系统错误时抛出本异常
	 */
	public Location getPlayerQuitLocation(Player Player) throws Exception {
		
		Var.V3.setTargetFile(getPlDataFile(Player));
		
		if(Var.V3.getKeyValue("loc.world=").equalsIgnoreCase("empty")){
			return null;	
		}
		
		Location loc=new Location(null, 0, 0, 0);
		
		loc.setWorld(Bukkit.getWorld(Var.V3.getKeyValue("loc.world=")));
		loc.setX(new Integer(Var.V3.getKeyValue("loc.x=")));
		loc.setY(new Integer(Var.V3.getKeyValue("loc.y=")));
		loc.setZ(new Integer(Var.V3.getKeyValue("loc.z=")));
		loc.setPitch(new Integer(Var.V3.getKeyValue("loc.pitch=")));
		loc.setYaw(new Integer(Var.V3.getKeyValue("loc.yaw=")));
		
		return loc;
	}

	
	
	/**
	 * 用于玩家登录后的消息控制
	 */
	public void ShowMessage(Player pl) {
		
		
		String ShowMessage="";
		
		//判断是否启用了登录消息
		if(Var.PlayerLoginedMessage.equalsIgnoreCase("false")){
			return;
		}
		
		//生成登录消息
		ShowMessage=Var.PlayerLoginedMessage;
		
		ShowMessage=ShowMessage.replace("%Player%", pl.getName());
		
		ShowMessage=ShowMessage.replace("&", "§");
		
		ShowMessage=ShowMessage.replace("#", "\n");
		
		
		//发送登录消息
		pl.sendMessage(ShowMessage);
		
		
	}

	/**
	 * 用于获取玩家加入信息
	 */
	@Override
	public String GenJoinedMessage(Player pl) {

		String str=Var.PlayerJoinedMessage;
		
		//生成消息
		str=str.replace("%Player%", pl.getName());
		
		str=str.replace("&", "§");
		
		str=str.replace("#", "\n");
		
		
		return str;
	}


	/**
	 * 用于获取玩家退出信息
	 */
	@Override
	public String GenQuitMessage(Player pl) {

		String str=Var.PlayerQuitMessage;
		
		//生成消息
		str=str.replace("%Player%", pl.getName());
		
		str=str.replace("&", "§");
		
		str=str.replace("#", "\n");
		
		
		return str;
	}
	
	
	

}
