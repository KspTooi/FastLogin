package us.ktmc.util;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import org.bukkit.Location;

import us.ktmc.SQL.IO4;
import us.ktmc.file.PlayerConf;
import us.ktmc.file.PluginConf;
import us.ktmc.main.FastLogin;
import us.ktmc.main.IO2;
import us.ktmc.main.IO3;
import us.ktmc.main.IODispatch;
import us.ktmc.player.PlayerIPCheck;
import us.ktmc.util_interface.FastSecurity;
import us.ktmc.util_interface.FastUtil;
import us.ktmc.util_interface.GeneralUtil;
import us.ktmc.process.*;

/**
 *
 * 此Class作为全局变量使用！
 *  
 * @author KspTooi
 *
 */
public class Var {
		
	//全局容器 初始化
	public static ArrayList<String> NoDamagePlayer=new ArrayList<String>();
		
	public static ArrayList<String> opTables=new ArrayList<String>();
	
	//全局工具 初始化
	
	public static FastLogin MainClass=null;
	
	public static PlayerConf PlConf=new PlayerConf();
	
	public static final FastUtil Util=new GeneralUtil();
	
	public static IODispatch IO=new IODispatch();
	
	public static PluginConf conf=new PluginConf();
	
	public static IO2 V2=new IO2();
	
	public static IO3 V3=new IO3();
	
	public static IO4 V4=new IO4();
	
	public static ActionHandler AH=new ActionHandler();
	
	public static FastSecurity FS=new FastSecurity();
	
	public static PlayerIPCheck PIP=new PlayerIPCheck();
	
	public static Fast_SQLHandler SQL=new Fast_SQLHandler();
	
	//全局变量 初始化

	public static final File FLconf=new File("plugins/ksptooi/fastlogin/FastLogin.conf");
	
	public static final File OLDConf=new File("plugins/ksptooi/fastlogin/OldFastLogin.conf");
	
	public static final File IPtables=new File("plugins/ksptooi/fastlogin/iptables.gd");
	
	public static Connection SQLConn=null;
	
	public static boolean isMysql=false;
	
	//全局配置 初始化
	public static String PlayerDataType="GeneralDataCore";
	
	public static int LoginTimeOut=0;
	
	public static int PasswordMaxLength=0;
	
	public static int PasswordMinLength=0;
	
	public static int PlayerNameMinLength=3;
	
	
	public static Location LoginLocation=new Location(null, 0, 0, 0);
	public static String Location_world="empty";
	public static double Location_x=0;
	public static double Location_y=0;
	public static double Location_z=0;
	public static float Location_pitch=0F;
	public static float Location_yaw=0F;
	
	public static int MessageInterval=5;
	
	public static int NoDamageTime=0;
	
	public static boolean Enable_LoginSecurity = false;
	
	public static boolean Enable_UserNameStrictmode=false;
	
	public static String[] BanName={"*"};
	
	public static int MaxRegisterIP=3;
	
	public static String RegexMatchForPlayerName="*";
	
	public static String PlayerLoginedMessage="";
	
	public static String PlayerJoinedMessage="";
	
	public static String PlayerQuitMessage="";
	
	public static boolean Enable_OPSecurity=true;
	
	public static boolean Enable_SecurityWarning=true;
	
	//#FastLogin - Mysql数据库配置 #玩家数据表
	public static String PlayerDataTable="";
	public static String PlayerNameField="";
	public static String PlayerPwdField="";
	public static String PlayerRegStatusField="";
	public static String PlayerLoginStatusField="";
	
	//#FastLogin - Mysql数据库配置 #玩家位置数据表
	public static String PlayerLocTable="";
	public static String PlayerLocworld="";
	public static String PlayerLocx="";
	public static String PlayerLocy="";
	public static String PlayerLocz="";
	public static String PlayerLocpitch="";
	public static String PlayerLocyaw="";
	
	
}