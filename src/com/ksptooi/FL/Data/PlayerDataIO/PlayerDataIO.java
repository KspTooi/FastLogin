package com.ksptooi.FL.Data.PlayerDataIO;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Cache.PlayerDataCache;
import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;
import com.ksptooi.FL.Performance.PerformanceMonitorManager;
import com.ksptooi.FL.Util.Logger;
import com.ksptooi.gdc.v6.Factory.DataSessionFactory;
import com.ksptooi.gdc.v6.Session.dataSession;

public class PlayerDataIO implements PlayerDataIO_Interfrace{

	
	
	DataSessionFactory dataSessionFactory = null;
	
	Logger LM=null;
	
	public PlayerDataIO(){
		
		DataManager.getDataSessionFactory();
		LM=new Logger();
	}
	
	
	//创建玩家数据文件
	@Override
	public boolean createPlayerData(String playerName) {
		
		playerName=playerName.toLowerCase();
		
		File playerDataFile = this.getPlayerDataFile(playerName);
			
		
		//判断文件是否存在
		if( ! playerDataFile.exists()){
			
			LM.logInfo("·为玩家创建新的数据文件:"+playerName+".gd");
			
			dataSession ds = dataSessionFactory.openSession(playerDataFile);
			
			dataSessionFactory.createdata(playerDataFile);
			
			ds.addline("playername=" + playerName);
			ds.addline("password=#");
			ds.addline("register=0");
			ds.addline("login=0");
			ds.addline("loc.world=empty");
			ds.addline("loc.x=empty");
			ds.addline("loc.y=empty");
			ds.addline("loc.z=empty");
			ds.addline("loc.pitch=empty");
			ds.addline("loc.yaw=empty");
			
			ds.release();
			
			return true;
		}
		
		return false;
	}
	

	//从GD文件||缓存 加载玩家数据文件
	@Override
	public PlayerEntity queryPlayerDataByName(String playerName) {
		
				
		//检查缓存
		
		if(PlayerDataCache.isExistsOfPDE(playerName)){
						
			return PlayerDataCache.getPDE(playerName);
			
		}
		
		//更新IO计数
		PerformanceMonitorManager.addPFPC();
			
		
		File playerDataFile = this.getPlayerDataFile(playerName);
		
		PlayerEntity pde=new PlayerEntity();
		
		
		dataSession ds = dataSessionFactory.openSession(playerDataFile);
		
			
		
		pde.setPlayername(ds.get("playername"));
		pde.setPassword(ds.get("password"));
		pde.setRegister(ds.get("register"));
		pde.setLogin(ds.get("login"));
		
		//如果玩家的loc.world不等于empty则获取他的最后退出位置
		if(!ds.get("loc.world").equals("empty")){
			
			pde.setLoc_world(ds.get("loc.world"));
			pde.setLoc_x(ds.getDouble("loc.x"));
			pde.setLoc_y(ds.getDouble("loc.y"));
			pde.setLoc_z(ds.getDouble("loc.z"));
			pde.setLoc_pitch(ds.getDouble("loc.pitch"));
			pde.setLoc_yaw(ds.getDouble("loc.yaw"));
			
			ds.release();
			
			Location loc=new Location(null, 0, 0, 0, 0, 0);
			
			loc.setWorld(Bukkit.getWorld(pde.getLoc_world()));
			loc.setX(pde.getLoc_x());
			loc.setY(pde.getLoc_y());
			loc.setZ(pde.getLoc_z());
			loc.setPitch(new Float(pde.getLoc_pitch()));
			loc.setYaw(new Float(pde.getLoc_yaw()));
			
		}
			
		//添加缓存
		PlayerDataCache.updatePDE(pde);
		
		return pde;
	}

	//从GD文件加载玩家数据文件
	@Override
	public PlayerEntity queryPlayerData(Player PlayerEntity) {
		return this.queryPlayerDataByName(PlayerEntity.getName());
	}
	

	//更新玩家数据文件到GD
	@Override
	public boolean updatePlayerData(PlayerEntity playerDataEntity) {
		
		
		File playerDataFile = this.getPlayerDataFile(playerDataEntity.getPlayername());
		
		PlayerEntity pde=playerDataEntity;
		
		dataSession ds = dataSessionFactory.openSession(playerDataFile);
		
		
		ds.set("password", pde.getPassword());
		ds.set("register", pde.getRegister());
		ds.set("login", pde.getLogin());
		ds.set("loc.world", pde.getLoc_world());
		ds.set("loc.x", pde.getLoc_x());
		ds.set("loc.y", pde.getLoc_y());
		ds.set("loc.z", pde.getLoc_z());
		ds.set("loc.pitch", pde.getLoc_pitch());
		ds.set("loc.yaw", pde.getLoc_yaw());
		
		ds.release();
		
		//更新IO计数
		PerformanceMonitorManager.addPFPC();
		
		//更新缓存
		PlayerDataCache.updatePDE(playerDataEntity);

		return true;
	}
	
	

	//获取玩家数据文件位置
	@Override
	public File getPlayerDataFile(String playerName) {
		return new File(ConfigManager.fastLoginPlayerDataFolder+playerName+".gd");
	}

	//获取玩家数据文件位置
	@Override
	public File getPlayerDataFile(Player playerEntity) {
		return this.getPlayerDataFile(playerEntity.getName());
	}
	

}
