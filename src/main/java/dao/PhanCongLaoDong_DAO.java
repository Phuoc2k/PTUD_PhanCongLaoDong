package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connect.Connect;
import entity.ChiTietCV;
import entity.CongTrinh;
import entity.CongViec;
import entity.LaoDong;
import entity.QuanLy;
import entity.TrinhDo;

public class PhanCongLaoDong_DAO {

	public ArrayList<LaoDong> getLaoDongTheoCV(String maCV) {
		ArrayList<LaoDong> list = new ArrayList<LaoDong>();
		Connection con = new Connect().getConnect();
		PreparedStatement p = null;
		try {

			p = con.prepareStatement(
					"select * from LaoDong where tenTrinhDo in (select tenTrinhDo from TrinhDo where capBacTrinhDo >=(select capBacTrinhDo from TrinhDo where tenTrinhDo in (select tenTrinhDo from CongViec where maCongViec =?))) and trangThai = 1");

			p.setString(1, maCV);

			ResultSet r = p.executeQuery();

			while (r.next()) {
				list.add(new LaoDong(r.getString("maLaoDong"), r.getString("tenLaoDong").trim(),
						new TrinhDo(r.getString("tenTrinhDo"))));
			}
			con.close();
		} catch (Exception e) {

		}
		return list;
	}

	public ArrayList<ChiTietCV> getChiTietCVTheoMaLD(String maLD) {
		ArrayList<ChiTietCV> list = new ArrayList<ChiTietCV>();
		Connection con = new Connect().getConnect(); 
		try {
			PreparedStatement p = con
					.prepareStatement("select * from ChiTietCV where maLaoDong = ? and trangThai = 0 order by ngayThucHien");
			p.setString(1, maLD);
			ResultSet r = p.executeQuery();
			while (r.next())
				list.add(new ChiTietCV(new CongTrinh(r.getString("maCongTrinh")),new CongViec(r.getString("maCongViec")), new LaoDong(r.getString("maLaoDong")),
						r.getDate("ngayThucHien"), r.getDate("ngayHoanThanh")));
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	
	public boolean phanCong(LaoDong ld, CongTrinh ct, CongViec cv, Date ngayTH, Date ngayHT, QuanLy ql, boolean trangThai) {

		Connection con = new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement("insert into ChiTietCv values(?, ?, ?, ?, ?, ?, ?)");
			p.setString(1, ct.getMaCongTrinh());
			p.setString(2, cv.getMaCongViec());
			p.setString(3, ld.getMaLaoDong());
			p.setDate(4, ngayTH);
			p.setDate(5, ngayHT);
			p.setString(6, ql.getMaQuanLy());
			p.setBoolean(7, trangThai);
			p.execute();
			con.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
