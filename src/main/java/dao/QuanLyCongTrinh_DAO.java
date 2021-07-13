package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import connect.Connect;
import entity.CongTrinh;
import entity.DiaDiem;

public class QuanLyCongTrinh_DAO {

	public ArrayList<CongTrinh> getAllCongTrinh() {
		ArrayList<CongTrinh> congTrinhs= new ArrayList<CongTrinh>();
		Connection con=new Connect().getConnect();
		try {
			
			Statement statement= con.createStatement();
			ResultSet r= statement.executeQuery("select * from CongTrinh where trangThai = N'Chưa hoàn thành'");
			while(r.next()) {
				DiaDiem diadiem= new DiaDiem(r.getString("tinhTP").trim(),r.getString("quanHuyen").trim(), r.getString("phuongXa").trim());
				congTrinhs.add(new CongTrinh(r.getString("maCongTrinh"), r.getString("tenCongTrinh"),
						diadiem, r.getDate("ngayKhoiCong"), r.getDate("ngayDKHoanThanh"),
						r.getString("trangThai"), r.getString("loaiCongTrinh")));
			}
			statement.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return congTrinhs;
	}
	
	public ArrayList<CongTrinh> phanTrang(int fn, int lt, String trangThai) {
		ArrayList<CongTrinh> listCongTrinh = new ArrayList<CongTrinh>();
		Connection con=new Connect().getConnect();
		try {
			PreparedStatement statement = con.prepareStatement("select * from(select *, ROW_NUMBER() over (order by maCongTrinh)"
					+ " as STT from CongTrinh where trangThai = ?) as PhanTrang where PhanTrang.STT Between "+fn+" and "+lt);
			statement.setString(1, trangThai);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				listCongTrinh.add(new CongTrinh(rs.getString("maCongTrinh"), rs.getString("tenCongTrinh").trim(),
						new DiaDiem(rs.getString("tinhTP").trim(), rs.getString("quanHuyen").trim(), rs.getString("phuongXa").trim()), rs.getDate("ngayKhoiCong"), rs.getDate("ngayDKHoanThanh"),
						rs.getString("trangThai").trim(), rs.getString("loaiCongTrinh").trim()));
			}
			con.close();
		} catch (Exception e) {

		}

		return listCongTrinh;
	}
	
	public int demSluongDuLieuTrongDB(String trangThai) {
		int dem = 0;
		Connection con=new Connect().getConnect();
		String sql = "select count(maCongTrinh) as Dem from CongTrinh where trangThai = ?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, trangThai);
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

	public CongTrinh getCTTheoMa(String ma) {
		CongTrinh ct = new CongTrinh();
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement p=con.prepareStatement("select * from CongTrinh where maCongTrinh = ?");
			p.setString(1, ma);
			ResultSet rs = p.executeQuery();
			rs.next();
			ct =new CongTrinh(rs.getString("maCongTrinh"), rs.getString("tenCongTrinh").trim(),
					new DiaDiem(rs.getString("tinhTP").trim(), rs.getString("quanHuyen").trim(), rs.getString("phuongXa").trim()), rs.getDate("ngayKhoiCong"), rs.getDate("ngayDKHoanThanh"),
					rs.getString("trangThai"), rs.getString("loaiCongTrinh").trim());
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return ct;
	}
	
	public int demLaoDong(String maCT, String maCV) {
		int dem = 0;
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement("select sl=count(maLaoDong) from ChiTietCV c join CongViec cv on c.maCongViec = cv.maCongViec where c.maCongViec = ? and maCongTrinh =? group by c.maCongViec ");
			p.setString(1, maCV);
			p.setString(2, maCT);
			ResultSet r = p.executeQuery();
			r.next();
			dem = r.getInt("sl");
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dem;
	}
	
	
	public boolean themCT(CongTrinh ct) {
		Connection con = new Connect().getConnect();
		PreparedStatement stt = null;

		try {
			stt = con.prepareStatement("insert into CongTrinh values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stt.setString(1, ct.getMaCongTrinh());
			stt.setString(2, ct.getTenCongTrinh());
			stt.setString(3, ct.getLoaiCongTrinh());
			stt.setDate(4, ct.getNgayKhoiCong());
			stt.setDate(5, ct.getNgayDKHoanThanh());
			stt.setString(6, ct.getDiaDiem().getTinhTP());
			stt.setString(7, ct.getDiaDiem().getQuanHuyen());
			stt.setString(8, ct.getDiaDiem().getPhuongXa());
			stt.setString(9, ct.getTrangThai());
			stt.setDate(10, null);
			stt.execute();
			con.close();
		} catch (SQLException e) {
			// ToDO: handle exception

			return false;
		}
		return true;
	}

	// suaCT
	public boolean suaCT(CongTrinh ct) {
		Connection con = new Connect().getConnect();
		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement(
					"update CongTrinh set tenCongTrinh=?, loaiCongTrinh=?, ngayDKHoanThanh=?, "
					+ "ngayKhoiCong=?, trangThai=?, tinhTP=?, quanHuyen=?, phuongXa=? where maCongTrinh=? ");
			stm.setString(1, ct.getTenCongTrinh());
			stm.setString(2, ct.getLoaiCongTrinh());
			stm.setDate(4, ct.getNgayKhoiCong());
			stm.setDate(3, ct.getNgayDKHoanThanh());
			stm.setString(5, ct.getTrangThai());
			stm.setString(6, ct.getDiaDiem().getTinhTP());
			stm.setString(7, ct.getDiaDiem().getQuanHuyen());
			stm.setString(8, ct.getDiaDiem().getPhuongXa());
			stm.setString(9, ct.getMaCongTrinh());
			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public ArrayList<CongTrinh> timKiemCongTrinh(DiaDiem diaDiem, String maCT, String tenCT, String trangThai) {
		Connect c = new Connect();

		Connection con = c.getConnect();
		ArrayList<CongTrinh> listCT = new ArrayList<CongTrinh>();
		PreparedStatement stmt = null;
		String sql = "select * from CongTrinh where trangThai = N'"+trangThai+"' and ";
		if(!maCT.equals(""))
			sql += "maCongTrinh like N'%"+ maCT +"%' and ";
		
		if(!tenCT.equals(""))
			sql += "tenCongTrinh like N'%"+tenCT+"%' and ";
		
		if(!diaDiem.getTinhTP().equals(""))
			sql += "tinhTP like N'%"+diaDiem.getTinhTP()+"%' and ";
		
		if(!diaDiem.getQuanHuyen().equals(""))
			sql += "quanHuyen like N'%"+diaDiem.getQuanHuyen()+"%' and ";
		
		if(!diaDiem.getPhuongXa().equals(""))
			sql += "phuongXa like N'%"+diaDiem.getPhuongXa()+"%' and ";
		sql = sql.substring(0, sql.length()-5);
		System.out.println(sql);
		try {
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				CongTrinh ct = new CongTrinh();
				ct.setMaCongTrinh(rs.getString("maCongTrinh").trim());
				ct.setTenCongTrinh(rs.getString("tenCongTrinh").trim());
				ct.setLoaiCongTrinh(rs.getString("loaiCongTrinh").trim());
				ct.setTrangThai(rs.getString("trangThai").trim());
				ct.setNgayKhoiCong(rs.getDate("ngayKhoiCong"));
				ct.setNgayDKHoanThanh(rs.getDate("ngayDKHoanThanh"));
				ct.setDiaDiem(new DiaDiem(rs.getString("tinhTP").trim(), rs.getString("quanHuyen").trim(),
						rs.getString("phuongXa").trim()));
				listCT.add(ct);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCT;
	}
	
	public CongTrinh getCTCuoi() {
		CongTrinh ct = new CongTrinh();
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement("select max(maCongtrinh) as ma from CongTrinh");
			ResultSet r = p.executeQuery();
			r.next();
			ct = new CongTrinh(r.getString("ma"));
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ct;
	}
	
	public boolean capNhatTrangThai(String maCT) {
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement("update ChiTietCV set trangThai = 0 where maCongTrinh = ?");
			p.setString(1, maCT);
			p.execute();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean setNgayHoanThanh(String maCT, Date ngayHT) {
		Connection con = new Connect().getConnect();
		try {
			PreparedStatement  p =con.prepareStatement("update CongTrinh set ngayHoanThanh = ? where maCongTrinh = ?");
			p.setDate(1, ngayHT);
			p.setString(2, maCT);
			p.execute();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			return false;
		}		
		
		return true;
	}
}
