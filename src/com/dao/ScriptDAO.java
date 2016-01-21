package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.ScriptForm;
import com.opensymphony.xwork2.ActionSupport;
import com.tools.ConnDB;

public class ScriptDAO extends ActionSupport {
	private ConnDB conn;

	public ScriptDAO() {
		conn = new ConnDB();
	}
	
	public List<ScriptForm> query(String condition) {
		List<ScriptForm> list = new ArrayList<ScriptForm>();
		String sql = "";
		if (condition == null) {
			sql = "SELECT * FROM tb_script";
		} else {
			sql = "SELECT * FROM tb_script " + condition;
		}
		System.out.println("Executing SQL statement："+sql);
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				ScriptForm scripF = new ScriptForm();
				scripF.setId(rs.getInt(1));
				scripF.setWishMan(rs.getString(2));
				scripF.setWellWisher(rs.getString(3));
				scripF.setContent(rs.getString(4));
				scripF.setColor(rs.getInt(5));
				scripF.setFace(rs.getInt(6));
				scripF.setSendTime(rs.getDate(7));
				scripF.setHits(rs.getInt(8));
				list.add(scripF); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//conn.close();
		return list;
	}
	
	public List<ScriptForm> queryTop() {
		List<ScriptForm> list = new ArrayList<ScriptForm>();
		String sql = "SELECT * FROM tb_script ORDER BY sendTime DESC LIMIT 10";
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				ScriptForm scripF = new ScriptForm();
				scripF.setId(rs.getInt(1));
				scripF.setWishMan(rs.getString(2));
				scripF.setWellWisher(rs.getString(3));
				scripF.setContent(rs.getString(4));
				scripF.setColor(rs.getInt(5));
				scripF.setFace(rs.getInt(6));
				scripF.setSendTime(rs.getDate(7));
				scripF.setHits(rs.getInt(8));
				list.add(scripF);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//conn.close();
		return list;
	}
	
	public int getRSCount() {
		String sql = "SELECT COUNT(*) AS count FROM tb_script";
		ResultSet rs = conn.executeQuery(sql);
		int rsCount = 0;
		try {
			if (rs.next()) {
				rsCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error occurs when getting results:" + e.getMessage());
		}
		return rsCount;
	}
	
	public String insert(ScriptForm SF) {
		String str = "";
		try {
			String sql = "INSERT INTO tb_script (wishMan,wellWisher,color,face,content) values('"
					+ SF.getWishMan()
					+ "','"
					+ SF.getWellWisher()
					+ "',"
					+ SF.getColor()
					+ ","
					+ SF.getFace()
					+ ",'"
					+ SF.getContent() + "')";
			int flag = conn.executeUpdate_id(sql);
			if (flag > 0) {
				str = "Congratulations! You have succussfully submitted your wish, you wish number is：[" + flag + "]";
			} else {
				str = "Sorry, you wish failed to submit.";
			}
			System.out.println("SQL：" + sql);
		} catch (Exception e) {
			str = "Sorry, you wish failed to submit!";
			System.out.println("Error Message:" + e.getMessage());
		}
		return str;
	}
	
	public String holdoutAdd(int id) {
		String sql = "UPDATE tb_script SET hits=hits+1 WHERE id=" + id + "";
		conn.executeUpdate(sql);
		String sql1 = "SELECT * FROM tb_script WHERE id=" + id + "";
		ResultSet rs = conn.executeQuery(sql1);
		String rtn = "";
		try {
			if (rs.next()) {
				rtn = id + "#" + String.valueOf(rs.getInt("hits")); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rtn;
	}
}
