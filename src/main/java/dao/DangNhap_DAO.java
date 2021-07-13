package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connect.Connect;

public class DangNhap_DAO {
	public boolean ktLaoDong(String maLaoDong, String matKhau) {
		Connection con=new Connect().getConnect();
		try {
			PreparedStatement p=con.prepareStatement("select maLaoDong from LaoDong where maLaoDong=? and matKhau=?");
			p.setString(1, maLaoDong);
			p.setString(2, matKhau);
			ResultSet r=p.executeQuery();
			while(r.next()) {
				if(!r.getString("maLaoDong").equals(""))
					return true;
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public boolean ktQuanLy(String maQuanLy, String matKhau) {
		Connection con=new Connect().getConnect();
		try {
			PreparedStatement p=con.prepareStatement("select maQuanLy from QuanLy where maQuanLy=? and matKhau=?");
			p.setString(1, maQuanLy);
			p.setString(2, matKhau);
			ResultSet r=p.executeQuery();
			while(r.next()) {
				if(!r.getString("maQuanLy").equalsIgnoreCase(""))
					return true;
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
