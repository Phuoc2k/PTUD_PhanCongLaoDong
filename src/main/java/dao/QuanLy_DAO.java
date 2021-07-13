package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connect.Connect;
import entity.QuanLy;

public class QuanLy_DAO {
	public QuanLy getQuanLy(String ma) {
		QuanLy ql =new QuanLy();
		Connection con=new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement("select * from QuanLy where maQuanLy = ?");
			p.setString(1, ma);
			ResultSet r = p.executeQuery();
			r.next();
			ql = new QuanLy(r.getString("maQuanLy").trim(), r.getString("tenQuanLy").trim());
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ql;
	}
}
