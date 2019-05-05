package com.ksptooi.playerData_BLL;

import java.sql.ResultSet;
import org.bukkit.entity.Player;
import com.ksptooi.FL.Entity.PlayerDataEntity;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.LogManager;
import com.ksptooi.gdc.MysqlAPI.MysqlController;

public class PlayerSqlDataBLL{

	MysqlController MC=null;
	LogManager lm=null;
	
	String playerDataTable=null;
	String playerNameField=null;
	String playerPwdField=null;
	String playerRegStatusField=null;
	String playerLoginStatusField=null;
	
	String playerLocTable=null;
	String playerNameField1=null;
	String playerLocworld=null;
	String playerLocx=null;
	String playerLocy=null;
	String playerLocz=null;
	String playerLocpitch=null;
	String playerLocyaw=null;
	
	public PlayerSqlDataBLL(){
		
		MC=new MysqlController();
		MC.loadConfigFromgdFile(FUtil.fastLoginConfigFile);
//		MC.loadConfigFromgdFile(new File("F:\\1217/MineCraft Server/1.7.10[PT]/plugins/ksptooi/fastlogin/fastlogin.conf"));
		lm=new LogManager();
		
		playerDataTable=FUtil.config.getPlayerDataTable();
		playerNameField=FUtil.config.getPlayerNameField();
		playerPwdField=FUtil.config.getPlayerPwdField();
		playerRegStatusField=FUtil.config.getPlayerRegStatusField();
		playerLoginStatusField=FUtil.config.getPlayerLoginStatusField();
		
		playerLocTable=FUtil.config.getPlayerLocTable();
		playerNameField1=playerNameField;
		playerLocworld=FUtil.config.getPlayerLocworld();
		playerLocx=FUtil.config.getPlayerLocx();
		playerLocy=FUtil.config.getPlayerLocy();
		playerLocz=FUtil.config.getPlayerLocz();
		playerLocpitch=FUtil.config.getPlayerLocpitch();
		playerLocyaw=FUtil.config.getPlayerLocyaw();
		
		this.createTable();
	}
	
	
	
	public void createTable(){
		
		
		lm.writerInfo("检查表");
		
		if( ! MC.tableIsExists(playerDataTable)){
			
			String playerDataTableSql="create table "+playerDataTable+"("+playerNameField+" varchar(50) not NULL PRIMARY KEY,"+playerPwdField+" varchar(50) not null,"+playerRegStatusField+" varchar(5) not null,"+playerLoginStatusField+" varchar(5) not NULL)";
			
			String playerLocTableSql="CREATE TABLE "+playerLocTable+"("+playerNameField1+" varchar(50) NOT NULL PRIMARY KEY,"+playerLocworld+" varchar(50) NOT NULL,"+playerLocx+" double(20,2) NOT NULL,"+playerLocy+" double(20,2) NOT NULL,"+playerLocz+" double(20,2) NOT NULL,"+playerLocpitch+" double(20,2) NOT NULL,"+playerLocyaw+" double(20,2) NOT NULL)";
			
			String foreign="alter table "+playerLocTable+" add constraint FK_"+playerNameField+" foreign key ("+playerNameField1+") references "+playerDataTable+"("+playerNameField+")";
			
			lm.writerInfo("创建表 - "+playerDataTable);
			lm.writerInfo("创建表 - "+playerLocTable);

			//玩家数据表
			MC.noQuery(playerDataTableSql);
			
			//玩家位置表
			MC.noQuery(playerLocTableSql);
			
			lm.writerInfo("创建约束");
			
			MC.noQuery(foreign);
			
		}
		
		
	}
	
	
	public boolean createPlayerData(String playerName) {
		
		String querySql="select * from "+playerDataTable+" left JOIN "+playerLocTable+" on "+playerDataTable+"."+playerNameField+"="+playerLocTable+"."+playerNameField1+" where "+playerDataTable+"."+playerNameField+"='"+playerName+"'";
		
		String insertSql="insert "+playerDataTable+" values('"+playerName+"','#','0','0')";
		
		String insertSql1="insert "+playerLocTable+" values('"+playerName+"','empty',0,0,0,0,0);";
			
		try {
		
			ResultSet rs=MC.query(querySql);
				
			
			while(rs.next()){
				return false;
			}
			
			
			lm.writerInfo("·为玩家创建新的数据库记录:"+playerName);
			
			MC.noQuery(insertSql);
			MC.noQuery(insertSql1);
			
			rs.getStatement().close();
			
		} catch (Exception e) {
			e.printStackTrace();
			lm.writerError("数据库错误 - createPlayerData");
			System.exit(0);
			return false;
		}
		
		
		return true;
	}

	
	public boolean createPlayerData(Player playerEntity) {
		return this.createPlayerData(playerEntity.getName());
	}

	
	public PlayerDataEntity getPlayerData(String playerName){
		
		try {
		
		String querySql="select * from "+playerDataTable+" "				
				+ "left JOIN "+playerLocTable+" on "+playerDataTable+"."+playerNameField+"="+playerLocTable+"."+playerNameField1+" "
				+ "where "+playerDataTable+"."+playerNameField+"='"+playerName+"'";
		
		PlayerDataEntity PDE=new PlayerDataEntity();
	
		this.createPlayerData(playerName);
		
		
		ResultSet rs=MC.query(querySql);
		
			
			
			while(rs.next()){
				
				PDE.setPlayername(rs.getString(playerNameField));
				PDE.setPassword(rs.getString(playerPwdField));		
				PDE.setRegister(rs.getString(playerRegStatusField));
				PDE.setLogin(rs.getString(playerLoginStatusField));
				PDE.setLoc_world(rs.getString(playerLocworld));
				PDE.setLoc_x(new Double(rs.getString(playerLocx)));
				PDE.setLoc_y(new Double(rs.getString(playerLocy)));
				PDE.setLoc_z(new Double(rs.getString(playerLocz)));
				PDE.setLoc_pitch(new Double(rs.getString(playerLocpitch)));
				PDE.setLoc_yaw(new Double(rs.getString(playerLocyaw)));
				rs.getStatement().close();
				return PDE;
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			lm.writerError("数据库错误 - getPlayerData");
			System.exit(0);
		}
		
		
		
		return null;
	}

	public PlayerDataEntity getPlayerData(Player playerEntity) {
		return this.getPlayerData(playerEntity.getName());
	}

	
	public boolean updatePlayerData(PlayerDataEntity PDE) {
		
		String updateSql="UPDATE "+playerDataTable+" "
				+ "set "+playerPwdField+"='"+PDE.getPassword()+"',"+playerRegStatusField+"='"+PDE.getRegister()+"',"+playerLoginStatusField+"='"+PDE.getLogin()+"' "
				+ "where "+playerNameField+"='"+PDE.getPlayername()+"'";
		
		String updateSql1="update "+playerLocTable+" "
				+ "set "+playerLocworld+"='"+PDE.getLoc_world()+"',"+playerLocx+"='"+PDE.getLoc_x()+"',"+playerLocy+"='"+PDE.getLoc_y()+"',"+playerLocz+"='"+PDE.getLoc_z()+"',"+playerLocpitch+"='"+PDE.getLoc_pitch()+"',"+playerLocyaw+"='"+PDE.getLoc_yaw()+"' "
				+ "where "+playerNameField1+"='"+PDE.getPlayername()+"'";
		
		
		MC.noQuery(updateSql);
		MC.noQuery(updateSql1);
		
		
		return true;
	}

	
}
