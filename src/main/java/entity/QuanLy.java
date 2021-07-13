package entity;

public class QuanLy {
	private String maQuanLy;
	private String tenQuanLy;
	private boolean gioiTinh;
	private String soDienThoai;
	private String matKhau;

	public QuanLy() {
		// TODO Auto-generated constructor stub
	}

	public QuanLy(String maNhanSu, String tenNhanSu, boolean gioiTinh, String soDienThoai, String matKhau) {
		super();
		this.maQuanLy = maNhanSu;
		this.tenQuanLy = tenNhanSu;
		this.gioiTinh = gioiTinh;
		this.soDienThoai = soDienThoai;
		this.matKhau = matKhau;
	}

	public QuanLy(String maQuanLy) {
		super();
		this.maQuanLy = maQuanLy;
	}

	public QuanLy(String maQuanLy, String tenQuanLy) {
		super();
		this.maQuanLy = maQuanLy;
		this.tenQuanLy = tenQuanLy;
	}

	public String getMaQuanLy() {
		return maQuanLy;
	}

	public void setMaQuanLy(String maQuanLy) {
		this.maQuanLy = maQuanLy;
	}

	public String getTenQuanLy() {
		return tenQuanLy;
	}

	public void setTenQuanLy(String tenQuanLy) {
		this.tenQuanLy = tenQuanLy;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

}
