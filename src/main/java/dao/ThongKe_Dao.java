package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connect.Connect;
import entity.CongTrinh;
import entity.DiaDiem;

public class ThongKe_Dao {

	public ArrayList<CongTrinh> getAllCongTrinh(int nam, int thoiGian, int thangOrQuy, int trangThai) {
		ArrayList<CongTrinh> list = new ArrayList<CongTrinh>();
		Connection con = new Connect().getConnect();
		String sql = "select * from CongTrinh where";
//		0 tất cả
//		1 la quy
//		2 thang
		sql += " year(ngayDKHoanThanh) = " + nam + " and";
		if (thangOrQuy==1) {
			if (thoiGian == 1)
				sql += " MONTH(ngayDKHoanThanh) between 1 and 3 and";
			else if (thoiGian == 2)
				sql += " MONTH(ngayDKHoanThanh) between 4 and 6 and";
			else if (thoiGian == 3)
				sql += " MONTH(ngayDKHoanThanh) between 7 and 9 and";
			else
				sql += " MONTH(ngayDKHoanThanh) between 10 and 12 and";
		} else if(thangOrQuy==2)
			sql += " MONTH(ngayDKHoanThanh) = " + thoiGian + " and";
		if (trangThai == 1)
			sql += " trangThai = N'Hoàn thành'";
		else if (trangThai == 2)
			sql += " ngayKhoiCong < getdate() and trangThai = N'Chưa hoàn thành'";
		else if(trangThai==3)
			sql += " ngayDKHoanThanh < getdate() and trangThai = N'Chưa hoàn thành'";
		else
			sql = sql.substring(0, sql.length()-4);
		try {
			PreparedStatement p = con.prepareStatement(sql);
			ResultSet r = p.executeQuery();
			while (r.next()) {
				DiaDiem diadiem = new DiaDiem(r.getString("tinhTP").trim(), r.getString("quanHuyen").trim(),
						r.getString("phuongXa").trim());
				list.add(new CongTrinh(r.getString("maCongTrinh"), r.getString("tenCongTrinh").trim(), diadiem,
						r.getDate("ngayKhoiCong"), r.getDate("ngayDKHoanThanh"), r.getString("trangThai"),
						r.getString("loaiCongTrinh").trim()));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

}
