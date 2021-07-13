package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connect.Connect;

public class DiaDiem_DAO {
	public ArrayList<String> getTinh() {
		ArrayList<String> list = new ArrayList<String>();
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement("select tinhTP from DiaChi group by tinhTP");
			ResultSet r = p.executeQuery();
			while (r.next()) {
				list.add(r.getString("tinhTP").trim());
			}
			con.close();
		} catch (Exception e) {
			return null;
		}
		return list;
	}

	public ArrayList<String> getQuan(String tinh) {
		ArrayList<String> list = new ArrayList<String>();
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement p = con
					.prepareStatement("select quanHuyen from DiaChi where tinhTP=? group by quanHuyen");
			p.setString(1, tinh);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				list.add(r.getString("quanHuyen").trim());
			}
			con.close();
		} catch (Exception e) {
			return null;
		}
		return list;
	}

	public ArrayList<String> getPhuong(String tinh, String quan) {
		ArrayList<String> list = new ArrayList<String>();
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement("select phuongXa from DiaChi where tinhTP=? and quanHuyen=?");
			p.setString(1, tinh);
			p.setString(2, quan);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				list.add(r.getString("phuongXa").trim());
			}
			con.close();
		} catch (Exception e) {
			return null;
		}
		return list;
	}
	

}
