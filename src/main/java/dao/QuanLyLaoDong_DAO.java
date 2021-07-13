package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.Connect;
import entity.DiaDiem;
import entity.LaoDong;
import entity.TrinhDo;

public class QuanLyLaoDong_DAO {

	public ArrayList<LaoDong> getAllLaoDong() {
		ArrayList<LaoDong> list = new ArrayList<LaoDong>();
		Connection con = new Connect().getConnect();
		try {

			Statement statement = con.createStatement();
			ResultSet r = statement.executeQuery("select * from LaoDong");
			while (r.next()) {
				DiaDiem diadiem = new DiaDiem(r.getString("tinhTP").trim(), r.getString("quanHuyen").trim(),
						r.getString("phuongXa").trim());
				TrinhDo trinhDo = new TrinhDo(r.getString("tenTrinhDo").trim());
				LaoDong laoDong = new LaoDong(r.getString("maLaoDong"), r.getString("tenLaoDong").trim(),
						r.getDate("ngaySinh"), r.getBoolean("gioiTinh"), r.getString("CMND").trim(), diadiem, trinhDo,
						r.getString("SDT"), r.getString("matKhau").trim());
				list.add(laoDong);
			}
			statement.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public ArrayList<LaoDong> phanTrang(int fn, int lt) {
		ArrayList<LaoDong> list = new ArrayList<LaoDong>();
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement statement = con
					.prepareStatement("select * from(select *, ROW_NUMBER() over (order by maLaoDong)"
							+ " as STT from LaoDong where trangThai = 1) as PhanTrang where PhanTrang.STT Between " + fn + " and " + lt );
			ResultSet r = statement.executeQuery();
			while (r.next()) {
				DiaDiem diadiem = new DiaDiem(r.getString("tinhTP").trim(), r.getString("quanHuyen").trim(),
						r.getString("phuongXa").trim());
				TrinhDo trinhDo = new TrinhDo(r.getString("tenTrinhDo").trim());
				LaoDong laoDong = new LaoDong(r.getString("maLaoDong").trim(), r.getString("tenLaoDong").trim(),
						r.getDate("ngaySinh"), r.getBoolean("gioiTinh"), r.getString("CMND").trim(), diadiem, trinhDo,
						r.getString("SDT"), r.getString("matKhau").trim());
				list.add(laoDong);
			}
			con.close();
		} catch (Exception e) {

		}

		return list;
	}

	public int demSluongDuLieuTrongDB() {
		int dem = 0;
		String sql = "select count(maLaoDong) as Dem from LaoDong where trangThai = 1";
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				dem = rs.getInt("Dem");
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dem;
	}

	public ArrayList<LaoDong> timKiemLaoDong(String maLaoDong, String tenLaoDong) {
		ArrayList<LaoDong> listLD = new ArrayList<LaoDong>();
		PreparedStatement stmt = null;
		Connection con = new Connect().getConnect();
		String sql = "select * from LaoDong where ";
		if(!maLaoDong.equals(""))
			sql += "maLaoDong like '%"+maLaoDong+"%' and ";
		
		if(!tenLaoDong.equals(""))
			sql += "tenLaoDong like N'%"+tenLaoDong+"%' and ";
		
		sql = sql.substring(0, sql.length()-5);
		try {
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				LaoDong ld = new LaoDong();
				ld.setMaLaoDong(rs.getString("maLaoDong").trim());
				ld.setTenLaoDong(rs.getString("tenLaoDong").trim());
				ld.setNgaySinh(rs.getDate("ngaySinh"));
				ld.setSDT(rs.getString("SDT").trim());
				ld.setCMND(rs.getString("CMND").trim());
				ld.setDiaChi(new DiaDiem(rs.getString("tinhTP").trim(), rs.getString("quanHuyen").trim(),
						rs.getString("phuongXa").trim()));
				ld.setGioiTinh(rs.getBoolean("gioiTinh"));
				ld.setTrinhDo(new TrinhDo(rs.getString("tenTrinhDo").trim()));
				ld.setMatKhau(rs.getString("matKhau").trim());
				listLD.add(ld);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listLD;
	}

	public LaoDong getLaoDong(String ma) {
		LaoDong laoDong = new LaoDong();
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement("select * from LaoDong where maLaoDong=?");
			p.setString(1, ma);
			ResultSet rs = p.executeQuery();
			rs.next();
			laoDong = new LaoDong(rs.getString("maLaoDong").trim(), rs.getString("tenLaoDong").trim(),
					rs.getDate("ngaySinh"), rs.getBoolean("gioiTinh"), rs.getString("CMND").trim(),
					new DiaDiem(rs.getString("tinhTP").trim(), rs.getString("quanHuyen").trim(),
							rs.getString("phuongXa").trim()),
					new TrinhDo(rs.getString("tenTrinhDo")), rs.getString("SDT"), rs.getString("matKhau"));
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return laoDong;
	}

	public boolean themLD(LaoDong ld) {
		Connection con = new Connect().getConnect();
		PreparedStatement stt = null;

		try {
			stt = con.prepareStatement("insert into LaoDong values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stt.setString(1, ld.getMaLaoDong());
			stt.setString(2, ld.getTenLaoDong());
			stt.setDate(3, ld.getNgaySinh());
			stt.setString(4, ld.getSDT());
			stt.setString(5, ld.getCMND());
			stt.setString(6, ld.getDiaChi().getTinhTP());
			stt.setString(7, ld.getDiaChi().getQuanHuyen());
			stt.setString(8, ld.getDiaChi().getPhuongXa());
			stt.setBoolean(9, ld.isGioiTinh());
			stt.setString(10, ld.getTrinhDo().getTenTrinhDo());
			stt.setString(11, ld.getMatKhau());
			stt.setBoolean(12, true);
			stt.execute();
			con.close();
		} catch (SQLException e) {
			// ToDO: handle exception
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean suaLD(LaoDong ld) {
		Connection con = new Connect().getConnect();
		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement(
					"update LaoDong set tenLaoDong=?, CMND=?, gioiTinh=?," + " SDT=?, ngaySinh=?, tenTrinhDo=?,"
							+ " tinhTP=?, quanHuyen=?, phuongXa=?, matKhau=? where maLaoDong=? ");
			stm.setString(1, ld.getTenLaoDong());
			stm.setString(2, ld.getCMND());
			stm.setBoolean(3, ld.isGioiTinh());
			stm.setString(4, ld.getSDT());
			stm.setDate(5, ld.getNgaySinh());
			stm.setString(6, ld.getTrinhDo().getTenTrinhDo());
			stm.setString(7, ld.getDiaChi().getTinhTP());
			stm.setString(8, ld.getDiaChi().getQuanHuyen());
			stm.setString(9, ld.getDiaChi().getPhuongXa());
			stm.setString(10, ld.getMatKhau());
			stm.setString(11, ld.getMaLaoDong());
			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// ToDo: handle exception
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean capNhatTrangThai(LaoDong ld, boolean trangThai) {
		new ChiTietCVDAO().nghiViec(ld.getMaLaoDong());
		Connection con = new Connect().getConnect();
		PreparedStatement xld = null;
		try {
			xld = con.prepareStatement("update LaoDong set trangThai = ? where maLaoDong = ?");
			xld.setBoolean(1, trangThai);
			xld.setString(2, ld.getMaLaoDong());
			xld.execute();
			con.close();
		} catch (SQLException e) {
			// ToDo: handle exception
			return false;
		}
		return true;

	}

	public Boolean doiMatKhau(String mk, String ma) {
		Connection con = new Connect().getConnect();
		String sql = "update LaoDong set matKhau =" + mk + " where maLaoDong = '" + ma + "'";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.executeUpdate();
			con.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public LaoDong getLDCuoi() {
		LaoDong ld = new LaoDong();
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement("select max(malaodong) as maCuoi from LaoDong");
			ResultSet r = p.executeQuery();
			r.next();
			ld = new LaoDong(r.getString("maCuoi"));
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ld;
	}
	
	public LaoDong timLDDuaVaoCMND(String cmnd) {
		LaoDong ld= new LaoDong();
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement("select * from LaoDong where cmnd = ? and trangThai = ?");
			p.setString(1, cmnd);
			p.setBoolean(2, false);
			ResultSet r = p.executeQuery();
			r.next();
			DiaDiem diadiem = new DiaDiem(r.getString("tinhTP").trim(), r.getString("quanHuyen").trim(),
					r.getString("phuongXa").trim());
			
			TrinhDo trinhDo = new TrinhDo(r.getString("tenTrinhDo").trim());
			ld = new LaoDong(r.getString("maLaoDong"), r.getString("tenLaoDong").trim(),
					r.getDate("ngaySinh"), r.getBoolean("gioiTinh"), r.getString("CMND").trim(), diadiem, trinhDo,
					r.getString("SDT"), r.getString("matKhau").trim());
			con.close();
		} catch (Exception e) {
			
		}
		return ld;
	}	

}