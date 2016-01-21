package com.tools;

import java.io.InputStream; 
import java.sql.*; 
import java.util.Properties;

public class ConnDB {
	public Connection conn=null;
	public Statement stmt=null;
	public ResultSet rs=null;
	private static String propFileName="/connDB.properties";
	private static Properties prop=new Properties();
	private static String dbClassName="com.mysql.jdbc.Driver";
	private static String dbUrl="jdbc:mysql://localhost:3306/wishWall";
	private static String dbUser="root";
	private static String dbPwd="root";
	
	public ConnDB(){
		try{
			InputStream in = getClass().getResourceAsStream(propFileName);
			prop.load(in); 
			dbClassName = prop.getProperty("DB_CLASS_NAME");
			dbUrl = prop.getProperty("DB_URL", dbUrl);	
			dbUser = prop.getProperty("DB_USER", dbUser);
			dbPwd = prop.getProperty("DB_PWD", dbPwd);	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(dbClassName).newInstance();
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (conn == null) {
			System.err.println("Error: DbConnectionManager.getConnection() getting DB connection Failed.");		
		}
		return conn;
	}
	
	public ResultSet executeQuery(String sql){
		try { 
			conn = getConnection(); 
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);			
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return rs; 
	}
	
	public int executeUpdate(String sql) {
		int result = 0; 
		try { 
			conn = getConnection(); 
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			result = stmt.executeUpdate(sql); 
		} catch (SQLException ex) {
			result = 0; 
		}
		return result; 
	}
	
	public int executeUpdate_id(String sql) {
	    int result = 0; 
	    try {
	      conn = getConnection();
	      stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                                  ResultSet.CONCUR_READ_ONLY);
	      result = stmt.executeUpdate(sql);		
	      String ID = "select @@IDENTITY as id";
	      rs = stmt.executeQuery(ID);
	      if (rs.next()) {
	        int autoID = rs.getInt("id");
	        result = autoID;
	      }
	    }
	    catch (SQLException ex) {
	      result = 0;
	    }
	    return result;
	  }
	
	public void close() {
		try {
			if (rs != null) { 
				rs.close(); 
			}
			if (stmt != null) {
				stmt.close(); 
			}
			if (conn != null) {
				conn.close(); 
			}
		} catch (Exception e) {
			e.printStackTrace(System.err); 
		}
	}
}
