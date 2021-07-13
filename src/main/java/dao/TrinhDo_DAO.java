package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connect.Connect;
import entity.TrinhDo;

public class TrinhDo_DAO {

	public ArrayList<TrinhDo> getTrinhDo() {
		ArrayList<TrinhDo> list =new ArrayList<TrinhDo>();
		
		Connection con =new Connect().getConnect();
		try {
			PreparedStatement p=con.prepareStatement("select tenTrinhDo from TrinhDo");
			ResultSet r=p.executeQuery();
			while(r.next()) {
				list.add(new TrinhDo(r.getString("tenTrinhDo").trim()));
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}
	
	public TrinhDo getTrinhDoTheoMaLD(String maLD) {
		TrinhDo t = new TrinhDo();
		Connection con =new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement("select tenTrinhDo from LaoDong where maLaoDong = ?");
			p.setString(1, maLD);
			ResultSet r =p.executeQuery();
			r.next();
			t = new TrinhDo(r.getString("tenTrinhDo"));
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return t;
	}
	
	public TrinhDo getTrinhDoTheoTenTrinhDo(String tenTD) {
		TrinhDo t = new TrinhDo();
		Connection con =new Connect().getConnect();
		try {
			PreparedStatement p = con.prepareStatement("select * from TrinhDo where tenTrinhDo = ?");
			p.setString(1, tenTD);
			ResultSet r =p.executeQuery();
			r.next();
			t = new TrinhDo(r.getString("tenTrinhDo"), r.getInt("capBacTrinhDo"));
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return t;
	}
	
}
