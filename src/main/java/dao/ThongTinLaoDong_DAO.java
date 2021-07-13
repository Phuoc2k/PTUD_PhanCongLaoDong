package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connect.Connect;
import entity.CongTrinh;
import entity.DiaDiem;

public class ThongTinLaoDong_DAO {
	public ArrayList<CongTrinh> getCongTrinh(String maLaoDong) {
		ArrayList<CongTrinh> list = new ArrayList<CongTrinh>();
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement(
					"select * from CongTrinh where maCongTrinh in (select maCongTrinh from ChiTietCV where maLaoDong = ? and trangThai=0)");
			p.setString(1, maLaoDong);
			ResultSet r = p.executeQuery();
			while(r.next()) {
				DiaDiem diadiem= new DiaDiem(r.getString("tinhTP").trim(),r.getString("quanHuyen").trim(), r.getString("phuongXa").trim());
				list.add(new CongTrinh(r.getString("maCongTrinh"), r.getString("tenCongTrinh"),
						diadiem, r.getDate("ngayKhoiCong"), r.getDate("ngayDKHoanThanh"),
						r.getString("trangThai"), r.getString("loaiCongTrinh")));
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}
}
