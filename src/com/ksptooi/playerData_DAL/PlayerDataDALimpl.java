package com.ksptooi.playerData_DAL;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import com.ksptooi.FL.Entity.PlayerDataEntity;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.LogManager;
import com.ksptooi.gdc.FileAPI.IOController_V5;

public class PlayerDataDALimpl implements PlayerDataDAL_Interface{

	IOController_V5 v5=null;
	LogManager LM=null;
	
	public PlayerDataDALimpl(){
		v5=new IOController_V5();
		LM=new LogManager();
	}
	
	
	//创建玩家数据文件
	@Override
	public boolean createPlayerData(String playerName) {
		
		File playerDataFile = this.getPlayerDataFile(playerName);
		
		v5.setTarget(playerDataFile);
		
		//判断文件是否存在
		if( ! playerDataFile.exists()){
			
			LM.writerInfo("·为玩家创建新的数据文件:"+playerName+".gd");
			
			v5.createNewGdcFile(playerDataFile);
			v5.addLine("playername=" + playerName);
			v5.addLine("password=#");
			v5.addLine("register=0");
			v5.addLine("login=0");
			v5.addLine("loc.world=empty");
			v5.addLine("loc.x=empty");
			v5.addLine("loc.y=empty");
			v5.addLine("loc.z=empty");
			v5.addLine("loc.pitch=empty");
			v5.addLine("loc.yaw=empty");
			return true;
		}
		
		return false;
	}
	

	//从GD文件加载玩家数据文件
	@Override
	public PlayerDataEntity queryPlayerDataByName(String playerName) {
		
	
		
		File playerDataFile = this.getPlayerDataFile(playerName);
		
		PlayerDataEntity PDE=new PlayerDataEntity();
		
		v5.setTarget(playerDataFile);
		
		
			
		PDE.setPlayername(v5.getKeyValue("playername"));
		PDE.setPassword(v5.getKeyValue("password"));
		PDE.setRegister(v5.getKeyValue("register"));
		PDE.setLogin(v5.getKeyValue("login"));
		
		//如果玩家的loc.world不等于empty则获取他的最后退出位置
		if(!v5.getKeyValue("loc.world").equals("empty")){
			
			PDE.setLoc_world(v5.getKeyValue("loc.world"));
			PDE.setLoc_x(new Double(v5.getKeyValue("loc.x")));
			PDE.setLoc_y(new Double(v5.getKeyValue("loc.y")));
			PDE.setLoc_z(new Double(v5.getKeyValue("loc.z")));
			PDE.setLoc_pitch(new Double(v5.getKeyValue("loc.pitch")));
			PDE.setLoc_yaw(new Double(v5.getKeyValue("loc.yaw")));
			
			Location loc=new Location(null, 0, 0, 0, 0, 0);
			
			loc.setWorld(Bukkit.getWorld(PDE.getLoc_world()));
			loc.setX(PDE.getLoc_x());
			loc.setY(PDE.getLoc_y());
			loc.setZ(PDE.getLoc_z());
			loc.setPitch(new Float(PDE.getLoc_pitch()));
			loc.setYaw(new Float(PDE.getLoc_yaw()));
			
		}
			
			
		
		return PDE;
	}

	//从GD文件加载玩家数据文件
	@Override
	public PlayerDataEntity queryPlayerData(Player PlayerEntity) {
		return this.queryPlayerDataByName(PlayerEntity.getName());
	}
	

	//更新玩家数据文件到GD
	@Override
	public boolean updatePlayerData(PlayerDataEntity playerDataEntity) {
		
		
		
		File playerDataFile = this.getPlayerDataFile(playerDataEntity.getPlayername());
		
		PlayerDataEntity PDE=playerDataEntity;
		
		
		
		v5.setTarget(playerDataFile);
		
		v5.setKeyValue("password", PDE.getPassword());
		v5.setKeyValue("register", PDE.getRegister());
		v5.setKeyValue("login", PDE.getLogin());
		v5.setKeyValue("loc.world", PDE.getLoc_world());
		v5.setKeyValue("loc.x", String.valueOf(PDE.getLoc_x()));
		v5.setKeyValue("loc.y", String.valueOf(PDE.getLoc_y()));
		v5.setKeyValue("loc.z", String.valueOf(PDE.getLoc_z()));
		v5.setKeyValue("loc.pitch", String.valueOf(PDE.getLoc_pitch()));
		v5.setKeyValue("loc.yaw", String.valueOf(PDE.getLoc_yaw()));
		
		return true;
	}
	
	
	
	//获取玩家数据文件位置
	@Override
	public File getPlayerDataFile(String playerName) {
		return new File(FUtil.fastLoginPlayerDataFolder+playerName+".gd");
	}

	//获取玩家数据文件位置
	@Override
	public File getPlayerDataFile(Player playerEntity) {
		return this.getPlayerDataFile(playerEntity.getName());
	}
	

}
