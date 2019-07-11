package com.ksptooi.FL.Data.PlayerData;

import java.sql.ResultSet;
import org.bukkit.entity.Player;
import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Cache.PlayerDataCache;
import com.ksptooi.FL.Data.Player.Entity.PlayerData;
import com.ksptooi.FL.Util.Logger;
import com.ksptooi.gdc.v6.Factory.SqlSessionFactory;
import com.ksptooi.gdc.v6.Session.SqlSession;


public class PlayerSqlDataManager{

	Logger lm=null;
	
	private String playerDataTable=null;
	private String playerNameField=null;
	private String playerPwdField=null;
	private String playerRegStatusField=null;
	private String playerLoginStatusField=null;
	
	private String playerLocTable=null;
	private String playerNameField1=null;
	private String playerLocworld=null;
	private String playerLocx=null;
	private String playerLocy=null;
	private String playerLocz=null;
	private String playerLocpitch=null;
	private String playerLocyaw=null;
	
	private SqlSessionFactory sqlSessionFactory = null;
	
	public PlayerSqlDataManager(){
		
//		MC.loadConfigFromgdFile(new File("F:\\1217/MineCraft Server/1.7.10[PT]/plugins/ksptooi/fastlogin/fastlogin.conf"));
		
		
		lm=FastLogin.getLoggerr();
		
		playerDataTable=ConfigManager.getConfig().getPlayerDataTable();
		playerNameField=ConfigManager.getConfig().getPlayerNameField();
		playerPwdField=ConfigManager.getConfig().getPlayerPwdField();
		playerRegStatusField=ConfigManager.getConfig().getPlayerRegStatusField();
		playerLoginStatusField=ConfigManager.getConfig().getPlayerLoginStatusField();
		
		playerLocTable=ConfigManager.getConfig().getPlayerLocTable();
		playerNameField1=playerNameField;
		playerLocworld=ConfigManager.getConfig().getPlayerLocworld();
		playerLocx=ConfigManager.getConfig().getPlayerLocx();
		playerLocy=ConfigManager.getConfig().getPlayerLocy();
		playerLocz=ConfigManager.getConfig().getPlayerLocz();
		playerLocpitch=ConfigManager.getConfig().getPlayerLocpitch();
		playerLocyaw=ConfigManager.getConfig().getPlayerLocyaw();
	
		
		//构建SqlSessionFactory
		this.sqlSessionFactory = DataManager.getGeneralDataFactoryBuilder().buildSqlFactory(ConfigManager.fastLoginConfigFile);
		
		lm.logInfo("正在建立数据库连接.");
		lm.logInfo("当前连接池最大容量:" + ConfigManager.getConfig().getPoolinitSize());
		
		this.createTable();
	}
	
	
	
	public void createTable(){
		
		
		lm.logInfo("检查表");
		
		SqlSession session = sqlSessionFactory.getSqlSession();
		
		if( ! session.isExistsTable(playerDataTable)){
			
			String playerDataTableSql="create table "+playerDataTable+"("+playerNameField+" varchar(50) not NULL PRIMARY KEY,"+playerPwdField+" varchar(50) not null,"+playerRegStatusField+" varchar(5) not null,"+playerLoginStatusField+" varchar(5) not NULL)";
			
			String playerLocTableSql="CREATE TABLE "+playerLocTable+"("+playerNameField1+" varchar(50) NOT NULL PRIMARY KEY,"+playerLocworld+" varchar(50) NOT NULL,"+playerLocx+" double(20,2) NOT NULL,"+playerLocy+" double(20,2) NOT NULL,"+playerLocz+" double(20,2) NOT NULL,"+playerLocpitch+" double(20,2) NOT NULL,"+playerLocyaw+" double(20,2) NOT NULL)";
			
			String foreign="alter table "+playerLocTable+" add constraint FK_"+playerNameField+" foreign key ("+playerNameField1+") references "+playerDataTable+"("+playerNameField+")";
			
			lm.logInfo("创建表 - "+playerDataTable);
			lm.logInfo("创建表 - "+playerLocTable);

			//玩家数据表
			session.noQuery(playerDataTableSql);
			
			//玩家位置表
			session.noQuery(playerLocTableSql);
			
			lm.logInfo("创建关系约束");
			
			session.noQuery(foreign);
			
		}
		
		lm.logInfo("成功");
		
		//关闭Session
		session.release();
		
	}
	
	//创建玩家数据文件
	public boolean createPlayerData(String playerName) {
		
		String querySql="select * from "+playerDataTable+" left JOIN "+playerLocTable+" on "+playerDataTable+"."+playerNameField+"="+playerLocTable+"."+playerNameField1+" where "+playerDataTable+"."+playerNameField+"='"+playerName+"'";
		
		String insertSql="insert "+playerDataTable+" values('"+playerName+"','#','0','0')";
		
		String insertSql1="insert "+playerLocTable+" values('"+playerName+"','empty',0,0,0,0,0);";
			
		SqlSession session = sqlSessionFactory.getSqlSession();
		
		try {
		
			ResultSet rs=session.query(querySql);
				
			
			while(rs.next()){
				//关闭Session
				session.release();
				return false;
			}
						
			lm.logInfo("·为玩家创建新的数据库记录:"+playerName);
			
			session.noQuery(insertSql);
			session.noQuery(insertSql1);
			
			//关闭Session
			session.release();
			
		} catch (Exception e) {
			e.printStackTrace();
			lm.logError("数据库错误 - createPlayerData");
			System.exit(0);
			//关闭Session
			session.release();
			return false;
			
		}
		
		//关闭Session
		session.release();
		
		return true;
	}

	
	public boolean createPlayerData(Player playerEntity) {
		return this.createPlayerData(playerEntity.getName());
	}

	
	//从MYSQL数据库||缓存 加载玩家数据文件
	public PlayerData getPlayerData(String playerName){
				
		//检查缓存	
		if(PlayerDataCache.isExistsOfPlayerData(playerName)){
						
			return PlayerDataCache.getPlayerData(playerName);
			
		}
		
		
		SqlSession session = sqlSessionFactory.getSqlSession();
		
		try {
		
		String querySql="select * from "+playerDataTable+" "				
				+ "left JOIN "+playerLocTable+" on "+playerDataTable+"."+playerNameField+"="+playerLocTable+"."+playerNameField1+" "
				+ "where "+playerDataTable+"."+playerNameField+"='"+playerName+"'";
		
		PlayerData pd=new PlayerData();
	
		this.createPlayerData(playerName);
				
		ResultSet rs=session.query(querySql);
		
			
			
			while(rs.next()){
				
				pd.setPlayername(rs.getString(playerNameField));
				pd.setPassword(rs.getString(playerPwdField));		
				pd.setRegister(rs.getString(playerRegStatusField));
				pd.setLogin(rs.getString(playerLoginStatusField));
				pd.setLoc_world(rs.getString(playerLocworld));
				pd.setLoc_x(new Double(rs.getString(playerLocx)));
				pd.setLoc_y(new Double(rs.getString(playerLocy)));
				pd.setLoc_z(new Double(rs.getString(playerLocz)));
				pd.setLoc_pitch(new Double(rs.getString(playerLocpitch)));
				pd.setLoc_yaw(new Double(rs.getString(playerLocyaw)));
				rs.getStatement().close();
				
				//添加缓存
				PlayerDataCache.updatePlayerData(pd);
				
				//关闭Session
				session.release();
				return pd;
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			lm.logError("数据库错误 - getPlayerData");
			//关闭Session
			session.release();
			System.exit(0);
		}
		
		
		//关闭Session
		session.release();
		
		return null;
	}

	public PlayerData getPlayerData(Player playerEntity) {
		return this.getPlayerData(playerEntity.getName());
	}

	
	public boolean updatePlayerData(PlayerData PDE) {
		
		SqlSession session = sqlSessionFactory.getSqlSession();
		
		String updateSql="UPDATE "+playerDataTable+" "
				+ "set "+playerPwdField+"='"+PDE.getPassword()+"',"+playerRegStatusField+"='"+PDE.getRegister()+"',"+playerLoginStatusField+"='"+PDE.getLogin()+"' "
				+ "where "+playerNameField+"='"+PDE.getPlayername()+"'";
		
		String updateSql1="update "+playerLocTable+" "
				+ "set "+playerLocworld+"='"+PDE.getLoc_world()+"',"+playerLocx+"='"+PDE.getLoc_x()+"',"+playerLocy+"='"+PDE.getLoc_y()+"',"+playerLocz+"='"+PDE.getLoc_z()+"',"+playerLocpitch+"='"+PDE.getLoc_pitch()+"',"+playerLocyaw+"='"+PDE.getLoc_yaw()+"' "
				+ "where "+playerNameField1+"='"+PDE.getPlayername()+"'";
		
		
		session.noQuery(updateSql);
		session.noQuery(updateSql1);
		
		//关闭Session
		session.release();
		
		return true;
	}

	
}
