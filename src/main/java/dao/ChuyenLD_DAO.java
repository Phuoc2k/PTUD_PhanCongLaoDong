package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connect.Connect;
import entity.ChiTietCV;
import entity.CongTrinh;
import entity.CongViec;
import entity.LaoDong;

public class ChuyenLD_DAO {

	public ArrayList<ChiTietCV> getChiTietTheoCV(String maCT, String maCV) {
		ArrayList<ChiTietCV> list = new ArrayList<ChiTietCV>();
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement("select * from laodong ld join ChiTietCV ct on ld.maLaoDong = ct.maLaoDong where tenTrinhDo in(select tenTrinhDo from trinhdo where capBacTrinhDo>=(select capBacTrinhDo from TrinhDo where tenTrinhDo in (select tenTrinhDo from CongViec where maCongViec = ?))) and maCongTrinh = ? and ct.trangThai=?");
			p.setString(1, maCV);
			p.setString(2, maCT);
			p.setBoolean(3, false);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				list.add(new ChiTietCV(new CongTrinh(r.getString("maCongTrinh")),
						new CongViec(r.getString("maCongViec")), new LaoDong(r.getString("maLaoDong")),
						r.getDate("ngayThucHien"), r.getDate("ngayHoanThanh")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	
	public boolean updateTrangThai(String maLD, String maCT, String maCongViec, boolean trangThai) {
		boolean rs= false;
		Connection con = new Connect().getConnect();
		String sql = "update ChiTietCV set trangThai = ? where maCongTrinh = ? and maLaoDong = ? and maCongViec = ?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setBoolean(1, trangThai);
			statement.setString(2, maCT);
			statement.setString(3, maLD);
			statement.setString(4, maCongViec);
			int n = statement.executeUpdate();
			if(n>0) {
				rs= true;
			}
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
}
