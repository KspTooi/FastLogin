package us.ktmc.file;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import us.ktmc.util.Var;
import us.ktmc.util_interface.FastUtil;
import us.ktmc.util_interface.GeneralUtil;
import us.ktmc.util.Language;



public class PluginConf {

	FastUtil Util=new GeneralUtil();
	
	public void IPtablesCheck(){
		
		if(!(Var.IO.CheckFile("plugins/ksptooi/fastlogin/", "iptables.gd"))){
			
			System.out.println("[FastLogin]·创建IPtables");
			try {
				
				Var.IO.OutputToFile(Var.IPtables, "Iptables:", "UTF-8");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	
	public void ConfigCheck(){
		
		if(!FastUtil.Mconf.exists()){
			
			System.out.println("[FastLogin]·写入默认配置项");
					
			Var.V3.setTargetFile(FastUtil.Mconf);
			
			
			try {
				
				new File("plugins/ksptooi/fastlogin/").mkdirs();
				
				FastUtil.Mconf.createNewFile();
				
				Var.V3.OutputToFile(FastUtil.Mconf, "version=" + FastUtil.FastLoginVersion, "UTF-8");
				Var.V3.AddtoFile("\r\nplayerdatatype=generaldata");
				Var.V3.AddtoFile("\r\nlogintimeout=60");
				Var.V3.AddtoFile("\r\npasswordlenmaxlength=15");
				Var.V3.AddtoFile("\r\npasswordlenminlength=5");
				Var.V3.AddtoFile("\r\nupdatatomysql=false");
				Var.V3.AddtoFile("\r\nminnamelength=3");
				Var.V3.AddtoFile("\r\nlocation.world=empty");
				Var.V3.AddtoFile("\r\nlocation.x=empty");
				Var.V3.AddtoFile("\r\nlocation.y=empty");
				Var.V3.AddtoFile("\r\nlocation.z=empty");
				Var.V3.AddtoFile("\r\nlocation.yaw=empty");
				Var.V3.AddtoFile("\r\nlocation.pitch=empty");
				Var.V3.AddtoFile("\r\nmessageinterval=5");
				Var.V3.AddtoFile("\r\nnodamagetime=0");
				Var.V3.AddtoFile("\r\nlocationprotection=false");
				Var.V3.AddtoFile("\r\nnamestrictmode=true");
				Var.V3.AddtoFile("\r\nbanname=*;");
				Var.V3.AddtoFile("\r\nipmaxreg=3");
				Var.V3.AddtoFile("\r\nregex=*");
				Var.V3.AddtoFile("\r\nplayerloginedmessage=false");
				Var.V3.AddtoFile("\r\nplayerjoinedmessage=&e%Player% Joined the game.");
				

			} catch (IOException e) {
				
				e.printStackTrace();
				System.out.println();
				
			}
			
			
		}
	
	}
	

	public void LanguageCheck(){
		
		if(!FastUtil.LanguageFile.exists()){
			
			
			System.out.println("[FastLogin}·创建语言文件");

			Var.V3.setTargetFile(FastUtil.LanguageFile);
					    		    	
			try{
				
				FastUtil.LanguageFile.createNewFile();
				
				Var.V3.OutputToFile(FastUtil.LanguageFile, "notlogin=§e[fastlogin]§b你需要登录后才能操作,使用/login 密码 来登录", "UTF-8");
		    	
		    	Var.V3.AddtoFile( "\r\nnotregister=§e[fastlogin]§b你需要注册后才能操作,使用/register 密码 确认密码 来注册");
		    	
		    	Var.V3.AddtoFile( "\r\nlogintimeoutkick=登录超时");
		    	
		    	Var.V3.AddtoFile( "\r\nrepeatlogin=§e[fastlogin]§c你已经登录了!");
		    	
		    	Var.V3.AddtoFile( "\r\nrepeatregister=§e[fastlogin]§c你已经注册了!");
		    	
		    	Var.V3.AddtoFile( "\r\nnotregister2=§e[fastlogin]§c你还没有注册！");
		    	
		    	Var.V3.AddtoFile( "\r\nloginsuccessful=§e[fastlogin]§a登录成功！");
		    	
		    	Var.V3.AddtoFile( "\r\npassworderror=§e[fastlogin]§c密码错误！");
		    	
		    	Var.V3.AddtoFile( "\r\nregistersuccessful=§e[fastlogin]§a注册完成！");
		    	
		    	Var.V3.AddtoFile( "\r\nnullpassword=§e[fastlogin]§c请输入密码！");
		    	
		    	Var.V3.AddtoFile( "\r\npasswdmax=§e[fastlogin]§c密码长度超过最大限制！");
		    	
		    	Var.V3.AddtoFile( "\r\npasswdmin=§e[fastlogin]§c密码太短！");
		    	
		    	Var.V3.AddtoFile( "\r\nconfirmpasswd=§e[fastlogin]§c请输入确认密码 例:/register 12345 12345");
		    	
		    	Var.V3.AddtoFile( "\r\nconfirmpasswderror=§e[fastlogin]§c两次输入的密码不一致");   
				
				Var.V3.AddtoFile( "\r\njoingameerror1=登录失败:相同用户名的玩家已经在游戏中!");   
			
				Var.V3.AddtoFile( "\r\nmodifypwusage=§e[fastlogin]§c修改密码 - 用法:/ModifyPasswd 旧密码 新密码 确认新密码");
				
				Var.V3.AddtoFile( "\r\nmodifyoldpwerr=§e[fastlogin]§c修改密码 - 失败:旧密码错误.");
				
				Var.V3.AddtoFile( "\r\nmodifyconfirmerror=§e[fastlogin]§c修改密码 - 失败:两次输入的确认密码不一致.");
				
				Var.V3.AddtoFile( "\r\nremodifypasswd=§e[fastlogin]§c修改密码 - 失败:新密码不能和旧密码一样.");
				
				Var.V3.AddtoFile( "\r\nmodifypasswdlengthmax=§e[fastlogin]§c修改密码 - 失败:新密码超过长度限制.");
				
				Var.V3.AddtoFile( "\r\nmodifypasswdlengthmin=§e[fastlogin]§c修改密码 - 失败:新密码太短.");
				
				Var.V3.AddtoFile( "\r\nmodifysuccessful=§e[fastlogin]§c修改密码 - §3成功:密码已更改！.");
				
				Var.V3.AddtoFile( "\r\nloginout=§e[fastlogin]§c·登录已经被注销");
			
			
			}catch(IOException e){
				e.printStackTrace();
				System.out.println("[严重]·文件系统错误,请删除FastLogin语言文件后重启服务器！");
			}	
		    			    	 						
		}		
		
	}
	
	
	
	public void ReaderLanuage(){
													
		Var.V3.setTargetFile(FastUtil.LanguageFile);
															
		try{
			

			Language.Notlogin=Var.V3.getKeyValue("notlogin=");
			
			Language.NotRegister=Var.V3.getKeyValue("notregister=");
					
			Language.LoginTimeOutKick=Var.V3.getKeyValue("logintimeoutkick=");
			
			Language.RepeatLogin=Var.V3.getKeyValue("repeatlogin=");
			
			Language.RepeatRegister=Var.V3.getKeyValue("repeatregister=");
			
			Language.NotRegister2=Var.V3.getKeyValue("notregister2=");
			
			Language.LoginSuccessful=Var.V3.getKeyValue("loginsuccessful=");
			
			Language.PasswordError=Var.V3.getKeyValue("passworderror=");
			
			Language.RegisterSuccessful=Var.V3.getKeyValue("registersuccessful=");
			
			Language.NullPassword=Var.V3.getKeyValue("nullpassword=");
			
			Language.PassMax=Var.V3.getKeyValue("passwdmax=");
			
			Language.PassMin=Var.V3.getKeyValue("passwdmin=");
			
			Language.NoConfirmPasswd=Var.V3.getKeyValue("confirmpasswd=");
			
			Language.ConfirmPasswdError=Var.V3.getKeyValue("confirmpasswderror=");
					
			Language.JoinGameError1=Var.V3.getKeyValue("joingameerror1=");
		
			Language.ModifyPwUsage=Var.V3.getKeyValue("modifypwusage=");
			
			Language.ModifyOldpwErr=Var.V3.getKeyValue("modifyoldpwerr=");
			
			Language.ModifyConfirmError=Var.V3.getKeyValue("modifyconfirmerror=");
			
			Language.ReModifyPasswd=Var.V3.getKeyValue("remodifypasswd=");
			
			Language.ModifyPasswdLengthMax=Var.V3.getKeyValue("modifypasswdlengthmax=");
			
			Language.ModifyPasswdLengthMin=Var.V3.getKeyValue("modifypasswdlengthmin=");
			
			Language.ModifySuccessful=Var.V3.getKeyValue("modifysuccessful=");
			
			Language.LoginOut=Var.V3.getKeyValue("loginout=");
			
			
			

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("[严重]·文件系统错误,请删除FastLogin语言文件后重启服务器！");
		}						
		
	}
	
	
	public void ReaderConfig(){
		
		Var.V3.setTargetFile(FastUtil.Mconf);
			
		try{
			
			Var.PasswordMaxlength=new Integer(Var.V3.getKeyValue("passwordlenmaxlength="));
			
			Var.PasswordMinlength=new Integer(Var.V3.getKeyValue("passwordlenminlength="));		
			
			Var.LoginTimeOut=new Integer(Var.V3.getKeyValue("logintimeout="));
			
			Var.MinNameLength=new Integer(Var.V3.getKeyValue("minnamelength="));			
			
			Var.MessageInterval=new Integer(Var.V3.getKeyValue("messageinterval="));
			
			Var.LocationProtection=new Boolean(Var.V3.getKeyValue("locationprotection="));		
			
			Var.Location_world=Var.V3.getKeyValue("location.world=");
			
			Var.isStrictmode=new Boolean(Var.V3.getKeyValue("namestrictmode="));	
			
			Var.BanName=Var.V3.getKeyValue("banname=").split(";");
			
			Var.ipmaxreg=new Integer(Var.V3.getKeyValue("ipmaxreg="));
			
			Var.NoDamageTime=new Integer(Var.V3.getKeyValue("nodamagetime="));
			
			Var.Regex=Var.V3.getKeyValue("regex=");
			
			Var.PlayerLoginedMessage=Var.V3.getKeyValue("playerloginedmessage=");
			
			Var.PlayerJoinedMessage=Var.V3.getKeyValue("playerjoinedmessage=");
			
			if( ! (Var.Location_world.equalsIgnoreCase("empty"))){		
				
				Var.Location_x=new Integer(Var.V3.getKeyValue("location.x="));
				Var.Location_y=new Integer(Var.V3.getKeyValue("location.y="));
				Var.Location_z=new Integer(Var.V3.getKeyValue("location.z="));
				Var.Location_pitch=new Integer(Var.V3.getKeyValue("location.pitch="));
				Var.Location_yaw=new Integer(Var.V3.getKeyValue("location.yaw="));
				Var.LoginLocation.setWorld(Bukkit.getWorld(Var.Location_world));
				Var.LoginLocation.setX(Var.Location_x);
				Var.LoginLocation.setY(Var.Location_y);
				Var.LoginLocation.setZ(Var.Location_z);
				Var.LoginLocation.setPitch(Var.Location_pitch);
				Var.LoginLocation.setYaw(Var.Location_yaw);
			}
			
			
		
		}catch (IOException e){
			e.printStackTrace();
			System.out.println("[严重]·文件系统错误,请删除FastLogin配置文件后重启服务器！");
		}
		
	
		
	}
	
	
	

	
	
	
}
