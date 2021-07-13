package entity;

import java.sql.Date;

public class CongTrinh {
	private String maCongTrinh;
	private String tenCongTrinh;
	private DiaDiem diaDiem;
	private Date ngayKhoiCong;
	private Date ngayDKHoanThanh;
	private String trangThai;
	private String loaiCongTrinh;
	private Date ngayHoanThanh;

	public CongTrinh() {
		super();
	}

	public CongTrinh(String maCongTrinh, String tenCongTrinh, DiaDiem diaDiem, Date ngayKhoiCong, Date ngayHoanThanh,
			String trangThai, String loaiCongTrinh) {
		super();
		this.maCongTrinh = maCongTrinh;
		this.tenCongTrinh = tenCongTrinh;
		this.diaDiem = diaDiem;
		this.ngayKhoiCong = ngayKhoiCong;
		this.ngayDKHoanThanh = ngayHoanThanh;
		this.trangThai = trangThai;
		this.loaiCongTrinh = loaiCongTrinh;
	}

	public CongTrinh(String maCongTrinh, String tenCongTrinh) {
		super();
		this.maCongTrinh = maCongTrinh;
		this.tenCongTrinh = tenCongTrinh;
	}

	
	
	public CongTrinh(String maCongTrinh, String tenCongTrinh, Date ngayKhoiCong) {
		super();
		this.maCongTrinh = maCongTrinh;
		this.tenCongTrinh = tenCongTrinh;
		this.ngayKhoiCong = ngayKhoiCong;
	}

	
	
	public CongTrinh(String maCongTrinh, String tenCongTrinh, DiaDiem diaDiem, Date ngayKhoiCong) {
		super();
		this.maCongTrinh = maCongTrinh;
		this.tenCongTrinh = tenCongTrinh;
		this.diaDiem = diaDiem;
		this.ngayKhoiCong = ngayKhoiCong;
	}

	public CongTrinh(String maCongTrinh) {
		super();
		this.maCongTrinh = maCongTrinh;
	}

	public String getMaCongTrinh() {
		return maCongTrinh;
	}

	public void setMaCongTrinh(String maCongTrinh) {
		this.maCongTrinh = maCongTrinh;
	}

	public String getTenCongTrinh() {
		return tenCongTrinh;
	}

	public void setTenCongTrinh(String tenCongTrinh) {
		this.tenCongTrinh = tenCongTrinh;
	}

	public DiaDiem getDiaDiem() {
		return diaDiem;
	}

	public void setDiaDiem(DiaDiem diaDiem) {
		this.diaDiem = diaDiem;
	}

	public Date getNgayKhoiCong() {
		return ngayKhoiCong;
	}

	public void setNgayKhoiCong(Date ngayKhoiCong) {
		this.ngayKhoiCong = ngayKhoiCong;
	}

	public Date getNgayDKHoanThanh() {
		return ngayDKHoanThanh;
	}

	public void setNgayDKHoanThanh(Date ngayHoanThanh) {
		this.ngayDKHoanThanh = ngayHoanThanh;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getLoaiCongTrinh() {
		return loaiCongTrinh;
	}

	public void setLoaiCongTrinh(String loaiCongTrinh) {
		this.loaiCongTrinh = loaiCongTrinh;
	}

	public Date getNgayHoanThanh() {
		return ngayHoanThanh;
	}

	public void setNgayHoanThanh(Date ngayHoanThanh) {
		this.ngayHoanThanh = ngayHoanThanh;
	}

	public CongTrinh(String maCongTrinh, String tenCongTrinh, DiaDiem diaDiem, Date ngayKhoiCong, Date ngayDKHoanThanh,
			String trangThai, String loaiCongTrinh, Date ngayHoanThanh) {
		super();
		this.maCongTrinh = maCongTrinh;
		this.tenCongTrinh = tenCongTrinh;
		this.diaDiem = diaDiem;
		this.ngayKhoiCong = ngayKhoiCong;
		this.ngayDKHoanThanh = ngayDKHoanThanh;
		this.trangThai = trangThai;
		this.loaiCongTrinh = loaiCongTrinh;
		this.ngayHoanThanh = ngayHoanThanh;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maCongTrinh == null) ? 0 : maCongTrinh.hashCode());
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
		CongTrinh other = (CongTrinh) obj;
		if (maCongTrinh == null) {
			if (other.maCongTrinh != null)
				return false;
		} else if (!maCongTrinh.equals(other.maCongTrinh))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CongTrinh [maCongTrinh=" + maCongTrinh + ", tenCongTrinh=" + tenCongTrinh + ", diaDiem=" + diaDiem
				+ ", ngayKhoiCong=" + ngayKhoiCong + ", ngayHoanThanh=" + ngayDKHoanThanh + ", trangThai=" + trangThai
				+ ", loaiCongTrinh=" + loaiCongTrinh + "]";
	}

	
}
