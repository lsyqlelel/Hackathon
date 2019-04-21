package com.Lia.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.Lia.Util.ConfigManager;

/*
 * BaseDao实现了JDBC的基本功能，作为父类
 */

public class BaseDao {
	private static final Logger logger = LogManager.getLogger(BaseDao.class);
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//获得链接
	public boolean getConnection() {
		try {
			String url = ConfigManager.getInstance().getString("url");
			String password = ConfigManager.getInstance().getString("password");
			String username = ConfigManager.getInstance().getString("username");
			try {
				Class.forName(ConfigManager.getInstance().getString("jdbc.driver"));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			logger.error("BaseDao:"+e);
			return false;
		}
		return true;
	}
	//增删改
	public int executeUpdate(String sql,Object[] params) {
		int updateRows = 0; 
		if(this.getConnection()) {
			try {
				pstmt = conn.prepareStatement(sql);
				for(int i=0;i<params.length;i++) {
					pstmt.setObject(i+1, params[i]);
				}
				updateRows = pstmt.executeUpdate();
			} catch (SQLException e) {
				logger.error("BaseDao:"+e);
			}
		}
		return updateRows;
	}
	
	//查
	public ResultSet executeSql(String sql,Object params[]) {
		
		if(this.getConnection()) {
			try {
				pstmt = conn.prepareStatement(sql);
				for(int i=0;i<params.length;i++) {
					pstmt.setObject(i+1, params[i]);
				}
				rs = pstmt.executeQuery();
			} catch (SQLException e) {
				logger.error("BaseDao:"+e);
			}
		}
		return rs;
	}
	
	//释放资源
	public boolean closeResource() {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("BaseDao:"+e);
				return false;
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				logger.error("BaseDao:"+e);
				return false;
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("BaseDao:"+e);
				return false;
			}
		}
		return true;
	}
	
	
}
