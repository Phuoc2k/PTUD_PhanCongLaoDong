package entity;

import java.sql.Date;

public class LaoDong {
	private String maLaoDong;
	private String tenLaoDong;
	private Date ngaySinh;
	private boolean gioiTinh;
	private String CMND;
	private DiaDiem diaChi;
	private TrinhDo trinhDo;
	private String SDT;
	private String matKhau;

	public LaoDong(String maLaoDong, String tenLaoDong, Date ngaySinh, boolean gioiTinh, String cMND, DiaDiem diaChi,
			TrinhDo trinhDo, String sDT, String matKhau) {
		super();
		this.maLaoDong = maLaoDong;
		this.tenLaoDong = tenLaoDong;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		CMND = cMND;
		this.diaChi = diaChi;
		this.trinhDo = trinhDo;
		SDT = sDT;
		this.matKhau = matKhau;
	}

	public LaoDong() {
		// TODO Auto-generated constructor stub
	}
	
	

	

	public LaoDong(String maLaoDong) {
		super();
		this.maLaoDong = maLaoDong;
	}

	public LaoDong(String maLaoDong, String tenLaoDong, TrinhDo trinhDo) {
		super();
		this.maLaoDong = maLaoDong;
		this.tenLaoDong = tenLaoDong;
		this.trinhDo = trinhDo;
	}

	public String getMaLaoDong() {
		return maLaoDong;
	}

	public void setMaLaoDong(String maLaoDong) {
		this.maLaoDong = maLaoDong;
	}

	public String getTenLaoDong() {
		return tenLaoDong;
	}

	public void setTenLaoDong(String tenLaoDong) {
		this.tenLaoDong = tenLaoDong;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getCMND() {
		return CMND;
	}

	public void setCMND(String cMND) {
		CMND = cMND;
	}

	public DiaDiem getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(DiaDiem diaChi) {
		this.diaChi = diaChi;
	}

	public TrinhDo getTrinhDo() {
		return trinhDo;
	}

	public void setTrinhDo(TrinhDo trinhDo) {
		this.trinhDo = trinhDo;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maLaoDong == null) ? 0 : maLaoDong.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LaoDong other = (LaoDong) obj;
		if (maLaoDong == null) {
			if (other.maLaoDong != null)
				return false;
		} else if (!maLaoDong.equals(other.maLaoDong))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LaoDong [maLaoDong=" + maLaoDong + ", tenLaoDong=" + tenLaoDong + ", ngaySinh=" + ngaySinh
				+ ", gioiTinh=" + gioiTinh + ", CMND=" + CMND + ", diaChi=" + diaChi + ", trinhDo=" + trinhDo.getTenTrinhDo() + ", SDT="
				+ SDT + ", matKhau=" + matKhau + "]";
	}
	
	
	

}
