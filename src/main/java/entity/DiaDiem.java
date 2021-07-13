package entity;

public class DiaDiem {
	private String tinhTP;
	private String quanHuyen;
	private String phuongXa;

	public String getTinhTP() {
		return tinhTP;
	}

	public void setTinhTP(String tinhTP) {
		this.tinhTP = tinhTP;
	}

	public String getQuanHuyen() {
		return quanHuyen;
	}

	public void setQuanHuyen(String quanHuyen) {
		this.quanHuyen = quanHuyen;
	}

	public String getPhuongXa() {
		return phuongXa;
	}

	public void setPhuongXa(String phuongXa) {
		this.phuongXa = phuongXa;
	}

	public DiaDiem(String tinhTP, String quanHuyen, String phuongXa) {
		super();
		this.tinhTP = tinhTP;
		this.quanHuyen = quanHuyen;
		this.phuongXa = phuongXa;
	}

	public DiaDiem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return   phuongXa+", "+quanHuyen+", "+ tinhTP  ;
	}

}
