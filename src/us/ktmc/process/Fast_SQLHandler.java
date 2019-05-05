package us.ktmc.process;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.entity.Player;

import us.ktmc.util.Var;

public class Fast_SQLHandler {

	
	Statement stm=null;
	
	//创建玩家数据表,如果存在则不进行任何操作
	public void CreateDataTable(){
		
		try {
			
			stm=Var.SQLConn.createStatement();
			
			
		System.out.println("[FastLogin]·检查玩家数据表.");
		//检查玩家数据表是否存在
		if(Var.V4.TableExists(Var.SQLConn, Var.PlayerDataTable)){
			return;
		}
		
		System.out.println("[FastLogin]·创建玩家数据表.");
		//创建表
		stm.executeUpdate("create table "+Var.PlayerDataTable+"("
						+Var.PlayerNameField+" varchar(50) NOT NULL PRIMARY KEY,"
						+Var.PlayerPwdField+ " varchar(50) NOT NULL,"
						+Var.PlayerRegStatusField+ " varchar(5) NOT NULL,"
						+Var.PlayerLoginStatusField+ " varchar(5) NOT NULL)");
		
		
		stm.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]·严重:数据库连接错误！");
		}
		
	}
	
	//创建玩家位置表,如果存在则不进行任何操作
	public void CreateLocTable(){
		
		try {
			
			stm=Var.SQLConn.createStatement();
			

		
			System.out.println("[FastLogin]·检查玩家位置表.");
		//检查玩家位置表是否存在
		if(Var.V4.TableExists(Var.SQLConn, Var.PlayerLocTable)){
			return;
		}
		
		System.out.println("[FastLogin]·创建玩家位置表.");
		//创建表
		stm.executeUpdate("create table "+Var.PlayerLocTable+"("
						+Var.PlayerNameField+" varchar(50) NOT NULL PRIMARY KEY,"
						+Var.PlayerLocworld+ " varchar(50) NOT NULL,"
						+Var.PlayerLocx+ " DOUBLE(20,2) NOT NULL,"
						+Var.PlayerLocy+" DOUBLE(20,2) NOT NULL,"
						+Var.PlayerLocz+ " DOUBLE(20,2) NOT NULL,"
						+Var.PlayerLocpitch+ " DOUBLE(20,2) NOT NULL,"
						+Var.PlayerLocyaw+ " DOUBLE(20,2) NOT NULL)");
		
		//创建约束
		
		System.out.println("[FastLogin]·创建约束.");
		stm.executeUpdate("alter table "+Var.PlayerLocTable+" add constraint FK_playername foreign key("+Var.PlayerNameField+") references "+Var.PlayerDataTable+"("+Var.PlayerNameField+")");
		
		stm.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]·严重:数据库连接错误！");
		}
		
	}
	
	
	/**
	 * 查询玩家记录是否存在。
	 * 
	 * @param Player 玩家实体
	 * 
	 */
	public boolean SQL_PlayerExists(Player pl){
		
		try {
			
			stm=Var.SQLConn.createStatement();
			
			ResultSet RS=stm.executeQuery("select "+Var.PlayerNameField+" from "+Var.PlayerDataTable+" "
										+ "where "+Var.PlayerNameField+"='"+pl.getName()+"'");
			
			while(RS.next()){
				
				if(RS.getString(Var.PlayerNameField).equalsIgnoreCase(pl.getName())){
					stm.close();
					return true;
				}
				
			}
			
			stm.close();
			return false;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]·严重:数据库连接错误！");
		}	
		
		
		return false;
		
	}
	
	
	
	
	/**
	 * 创建一条玩家记录,当记录已存在时不执行任何操作。
	 * 
	 * @param Player 玩家实体
	 * 
	 */
	public void SQL_CreatePlayerData(Player pl){
		
		if(SQL_PlayerExists(pl)){
			return;
		}
		
		try {
			
			System.out.println("[FastLogin]·为玩家创建新的数据库记录:"+pl.getName());
			
			stm=Var.SQLConn.createStatement();
			
			//添加玩家数据表 - 记录
			stm.executeUpdate("INSERT INTO "+Var.PlayerDataTable+"("+Var.PlayerNameField+","+Var.PlayerPwdField+","+Var.PlayerRegStatusField+","+Var.PlayerLoginStatusField+")"
					+ "VALUES('"+pl.getName().toLowerCase()+"',"+"'#','0','0')");
			
			//添加玩家位置表 - 记录
			stm.executeUpdate("INSERT INTO "+Var.PlayerLocTable+"("+Var.PlayerNameField+","+Var.PlayerLocworld+","+Var.PlayerLocx+","+Var.PlayerLocy+","+Var.PlayerLocz+","+Var.PlayerLocpitch+","+Var.PlayerLocyaw+") "
					+ "VALUES('"+pl.getName().toLowerCase()+"','world',0,0,0,0,0)");
			
			stm.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]·严重:数据库连接错误！");
		}
			
		
	}
	
	/**
	 * 设置玩家登录状态
	 * 
	 * @param Player 玩家实例
	 * @param isLogin 设置是否登录 如果值为True则将玩家文件同步至已登录反则未登录
	 */
	public void SQL_setPlayerLogin(Player pl,Boolean isLogin){
		
		try {
			
			stm=Var.SQLConn.createStatement();
			
			if(isLogin){
				
				stm.executeUpdate("UPDATE "+Var.PlayerDataTable+" set "+Var.PlayerLoginStatusField+"='1'"
						+ "WHERE "+Var.PlayerNameField+"='"+pl.getName()+"'");
				
				stm.close();
				return;
				
			}
			
			stm.executeUpdate("UPDATE "+Var.PlayerDataTable+" set "+Var.PlayerLoginStatusField+"='0'"
					+ "WHERE "+Var.PlayerNameField+"='"+pl.getName()+"'");
			
			stm.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]·严重:数据库连接错误！");
		}
		
		
	}
	
	
	
	/**
	 * 判断玩家是否注册
	 * @param PlayerEntity 玩家实例 
	 * @return 如果为True则玩家已注册.
	 */
	public boolean SQL_isReg(Player PlayerEntity){	
		return SQL_isReg(PlayerEntity.getName());
	}
	
	
	
	/**
	 * 判断玩家是否注册
	 * @param PlayerName 玩家名称
	 * @return 如果为True则玩家已注册.
	 */
	
	public boolean SQL_isReg(String Player){
		
		try {
			
			stm=Var.SQLConn.createStatement();
			
			ResultSet RS=stm.executeQuery("select "+Var.PlayerRegStatusField+" from "+Var.PlayerDataTable+" "
										+ "where playername='"+Player.toLowerCase()+"'");
			
			while(RS.next()){
				
				if(RS.getString(Var.PlayerRegStatusField).equalsIgnoreCase("1")){
					System.out.println("已注册！");
					return true;
				}
				
			}
				
			System.out.println("未注册！");
			stm.close();
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]·严重:数据库连接错误！");
		}
		
		return false;

	}
	
	
	/**
	 * 判断玩家是否登录
	 * @param pl 玩家实例
	 * @return 如果为True则玩家已登录.
	 */
	public boolean SQL_isLogin(Player pl){
		return SQL_isLogin(pl.getName());
	}
	
	
	/**
	 * 判断玩家是否登录
	 * @param PlayerName 玩家名称
	 * @return 如果为True则玩家已登录.
	 */
	public boolean SQL_isLogin(String Player){
		
		try {
			
			stm=Var.SQLConn.createStatement();
			
			ResultSet RS=stm.executeQuery("select "+Var.PlayerLoginStatusField+" from "+Var.PlayerDataTable+" "
										+ "where playername='"+Player.toLowerCase()+"'");
			
			while(RS.next()){
				
				if(RS.getString(Var.PlayerLoginStatusField).equalsIgnoreCase("1")){
					return true;
				}
				
			}
				
			
			stm.close();
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]·严重:数据库连接错误！");
		}
		
		return false;

	}
	
	/**
	 * 设置玩家注册状态
	 * 
	 * @param Player 玩家实例
	 * @param isReg 设置是否注册 如果值为True则将玩家文件同步至已注册反则未注册
	 */
	public void SQL_setPlayerReg(Player pl,Boolean isReg){
		
		try {
			
			stm=Var.SQLConn.createStatement();
			
			if(isReg){
				
				stm.executeUpdate("UPDATE "+Var.PlayerDataTable+" set "+Var.PlayerRegStatusField+"='1'"
						+ "WHERE "+Var.PlayerNameField+"='"+pl.getName()+"'");
				
				stm.close();
				return;
				
			}
			
			stm.executeUpdate("UPDATE "+Var.PlayerDataTable+" set "+Var.PlayerRegStatusField+"='0'"
					+ "WHERE "+Var.PlayerNameField+"='"+pl.getName()+"'");
			
			stm.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]·严重:数据库连接错误！");
		}
		
		
	}
	
	/**
	 * 设置玩家密码
	 * 
	 * @param Player 玩家实例
	 * @param pwd 密码
	 */
	public void SQL_setPlayerPwd(Player pl,String pwd){
		
		try {
			
			stm=Var.SQLConn.createStatement();
			
			
			
			stm.executeUpdate("UPDATE "+Var.PlayerDataTable+" set "+Var.PlayerPwdField+"='"+pwd+"'"
					+ "WHERE "+Var.PlayerNameField+"='"+pl.getName()+"'");
			
			
			stm.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]·严重:数据库连接错误！");
		}
		
		
	}
	
	/**
	 * 判断玩家密码是否正确
	 * @param pl 玩家实例
	 * @param pwd 玩家密码
	 * @return 如果值为true则玩家密码正确
	 */
	public boolean SQL_isPassword(Player pl,String pwd){
		
		String SQLpwd="";
		
		try {
			stm=Var.SQLConn.createStatement();
			
			
			ResultSet RS=stm.executeQuery("select "+Var.PlayerPwdField+" from "+Var.PlayerDataTable+" "
										+ "where playername='"+pl.getName().toLowerCase()+"'");
			
			while(RS.next()){
				SQLpwd=RS.getString(Var.PlayerPwdField);
			}
			
			if(pwd.equals(SQLpwd)){
				return true;
			}
			
			
			stm.close();
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]·严重:数据库连接错误！");
		}
		
		
		return false;
	}
	
	
	
	
	
}
