package us.ktmc.file;

import java.io.File;
import java.io.IOException;
import us.ktmc.util.Var;
import us.ktmc.util_interface.FastUtil;

public class OldConfUpdata {

	
	public final void AutoUpdataOldConf(){
	
		try {
			
			if(!(Var.FLconf.exists())){
				return;
			}

			if(!Var.V2.getKeyValue(FastUtil.Mconf, "Version=").equalsIgnoreCase(FastUtil.FastLoginVersion)){
				System.out.println("·[FastLogin]更新配置项....");	
				System.out.println("[FastLogin]·发现旧版配置文件");
				System.out.println("[FastLogin]·已将旧文件备份至目录:Plugins/ksptooi/fastlogin/"+Var.OLDConf.getName());
				Var.OLDConf.delete();
				Var.FLconf.renameTo(Var.OLDConf);
			}
			
				
		
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("[严重]·文件系统错误,请删除FastLogin配置文件后重启服务器！");
			System.exit(0);
			
		}
				
	}
	
	
	
	
	public final void AutoUpdataPlayerdata(){
		
		File[] dir=new File("plugins/ksptooi/fastlogin/database/").listFiles();
		System.out.println("[FastLogin]·更新玩家配置文件....");
		
		for(int i=0;i<dir.length;i++){		
			if(dir[i].isFile()){
				
				Var.IO.AddtoFile(dir[i], "\r\nloc.world=empty", "UTF-8");
				Var.IO.AddtoFile(dir[i], "\r\nloc.x=empty", "UTF-8");
				Var.IO.AddtoFile(dir[i], "\r\nloc.y=empty", "UTF-8");
				Var.IO.AddtoFile(dir[i], "\r\nloc.z=empty", "UTF-8");
				Var.IO.AddtoFile(dir[i], "\r\nloc.pitch=empty", "UTF-8");
				Var.IO.AddtoFile(dir[i], "\r\nloc.yaw=empty", "UTF-8");				
				 
			}
				
		}
			
		
	}
	
	
			
	
}
