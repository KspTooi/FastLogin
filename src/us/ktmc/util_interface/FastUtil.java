package us.ktmc.util_interface;

import java.io.File;
import java.io.IOException;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.ktmc.Exception.PasswdRulesException;
import us.ktmc.Exception.PlayerNotRegisterException;

/**
 * @author KspTooi
 * 
 * FastLogin标准接口 V1
 */

public interface FastUtil {
	
	public static final String PlayerDataDir="plugins/ksptooi/fastlogin/database/";

	public static final File Mconf=new File("plugins/ksptooi/fastlogin/FastLogin.conf");
	
	public static final File LanguageFile=new File("plugins/ksptooi/fastlogin/language.gd");
	
	public static final String FastLoginVersion="0.3.6-G-26-RE";
	
	/**
	 * 用于判断命令被玩家执行或者是控制台执行
	 * 
	 * @param sender 命令执行人	
	 * @return true 控制台执行了玩家命令 false 玩家执行了命令
	 */
	public boolean PlayerCommandExecuteAtConsole(CommandSender sender);
	
	/**
	 * 获取玩家的数据文件
	 * 
	 * @param PlayerEntity 玩家实例
	 * @return 返回一个玩家数据文件
	 */
	public File getPlDataFile(Player PlayerEntity);
	
	/**
	 * 获取玩家的数据文件
	 * 
	 * @param PlayerEntity 玩家名称
	 * @return 返回一个玩家数据文件
	 */
	public File getPlDataFile(String PlayerName);
	
	/**
	 * 判断玩家数据文件是否存在
	 * 
	 * @param PlayerEntity 玩家实例 
	 * @return 如果为true则玩家文件存在
	 */	
	public boolean DataFileIsExists(Player PlayerEntity);
	
	/**
	 * 判断玩家数据文件是否存在
	 * 
	 * @param PlayerName 玩家名称
	 * @return 如果为true则玩家文件存在
	 */	
	public boolean DataFileIsExists(String PlayerName);
	
	
	/**
	 * 判断玩家是否注册
	 * 
	 * @param PlayerEntity 玩家实例 
	 * @return 如果为True则玩家已注册.
	 */	
	public boolean isReg(Player PlayerEntity);
	
	/**
	 * 判断玩家是否注册
	 * 
	 * @param PlayerName 玩家名称
	 * @return 如果为True则玩家已注册.
	 */	
	public boolean isReg(String PlayerName);
	
	/**
	 * 获取插件文件系统错误时的文本
	 * @return 错误文本
	 */
	public String getFileErrorText();
	
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
	public void SetDefaultLoginLocation(String WorldName,int X,int Y,int Z,int Pitch,int Yaw);
	
	
	/**
	 * 删除设置的默认登陆位置
	 * @return 如果成功删除 返回True 删除失败返回 Flase
	 */
	public boolean DeleteDefaultLoginLocation();
	
	/**
	 * OP重新设置玩家的密码！(此方法遵守通用密码设置规则)
	 * @param PlayerEntity 玩家实体
	 * @param Passwd 要设置的密码
	 * @throws IOException 文件系统出错时抛出此异常
	 * @throws PasswdRulesException 当玩家密码不符合要求时抛出此异常！(例如超过最大长度.)
	 * @throws PlayerNotRegisterException 当玩家未注册时抛出此异常
	 */
	public void ReSetPasswd(Player PlayerEntity,String Passwd)throws PlayerNotRegisterException,PasswdRulesException,IOException;
	
	/**
	 * OP重新设置玩家的密码！(此方法遵守通用密码设置规则)
	 * @param PlayerName 玩家名称
	 * @param Passwd 要设置的密码
	 * @throws IOException 文件系统出错时抛出此异常
	 * @throws PasswdRulesException 当玩家密码不符合要求时抛出此异常！(例如超过最大长度.)
	 * @throws PlayerNotRegisterException 当玩家未注册时抛出此异常
	 */
	public void ReSetPasswd(String PlayerName,String Passwd)throws PlayerNotRegisterException,PasswdRulesException,IOException;
	
	/**
	 * 检查玩家的密码是否符合规则
	 * 
	 * @param Passwd 要检查的密码
	 * @throws PasswdRulesException 当玩家密码不符合要求时抛出此异常！(例如超过最大长度.)
	 */
	public void PasswdIsAvailable(String Passwd)throws PasswdRulesException;
		
	/**
	 * 发送一个OP命令帮助至控制台
	 * @param 控制台实例
	 */
	public void SendOPHelp(CommandSender sender);
	
	/**
	 * 玩家更改玩家的密码
	 * 
	 * @param PlayerEntity 玩家实例
	 * @param OldPasswd 原密码
	 * @param NewPasswd 新密码
	 * @param ConfirmNewPasswd 确认新密码
	 */
	public void ChangePasswd(Player PlayerEntity, String OldPasswd, String NewPasswd,String ConfirmNewPasswd);
	
	/**
	 * 判断玩家输入的密码是否正确
	 * 
	 * @param PlayerEntity 玩家实例
	 * @param Passwd 要判断的密码
	 */
	public boolean isPassword(Player PlayerEntity,String Passwd);
	
	
	/**
	 * 判断玩家是否登录
	 * 
	 * @param PlayerEntity 玩家实例
	 * @return 玩家已登录返回True 当玩家未注册时返回False 玩家未登录时返回False
	 */
	public boolean isLogin(Player PlayerEntity);

	
	/**
	 * 将玩家传送到默认登陆位置
	 * 
	 * @param PlayerEntity 玩家实例
	 * @return 成功传送返回True 默认登陆位置不存在返回False
	 */
	public boolean TelePort_DefaultLoginLocation(Player PlayerEntity);
	
	/**
	 * 设置玩家登录状态
	 * 
	 * @param Player 玩家实例
	 * @param isLogin 设置是否登录 如果值为True则将玩家文件同步至已登录反则未登录
	 */
	public void setPlayerLogin(Player Player,Boolean isLogin);
	
	
	/**
	 * 创建一个玩家数据文件,当文件已存在时不执行任何操作。
	 * 
	 * @param Player 玩家实体
	 */
	public void CreatePlayerData(Player Player) throws Exception;
		
	/**
	 * 用于处理玩家名称 当名称不合法时直接踢出玩家
	 * 
	 * @param Player 玩家实体
	 * @return 如果名称合法返回True 不合法返回False并直接踢出玩家
	 */
	public boolean PlayerNameProcess(Player Player);
	
	
	/**
	 * 将玩家当前的位置保存到数据文件 作为最后下线位置使用
	 * 
	 * @param Player 玩家实体
	 */
	public void setPlayerQuitLocation(Player Player);
	
	/**
	 * 从数据文件获取玩家最后退出的位置
	 * 
	 * @param Player 玩家实体
	 * @return 玩家最后退出的位置 如果没有,则返回null
	 */
	public Location getPlayerQuitLocation(Player Player) throws Exception;
	
	/**
	 * 在玩家登录后显示消息
	 */
	public void ShowMessage(Player pl);
	
	/**
	 * 用于获取玩家加入信息
	 */
	public String GenJoinedMessage(Player pl);
	
	
	/**
	 * 异步转同步踢出玩家
	 */
	public void KickPlayerForBukkit(Player pl,String Message);
	
}
