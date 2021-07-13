package entity;

public class TrinhDo {
	private String tenTrinhDo;
	private int capBacTrinhDo;

	public TrinhDo() {
		super();
	}

	public TrinhDo(String tenTrinhDo) {
		super();
		this.tenTrinhDo = tenTrinhDo;
	}

	public TrinhDo(String tenTrinhDo, int capBacTrinhDo) {
		super();
		this.tenTrinhDo = tenTrinhDo;
		this.capBacTrinhDo = capBacTrinhDo;
	}

	public String getTenTrinhDo() {
		return tenTrinhDo;
	}

	public void setTenTrinhDo(String tenTrinhDo) {
		this.tenTrinhDo = tenTrinhDo;
	}

	public int getCapBacTrinhDo() {
		return capBacTrinhDo;
	}

	public void setCapBacTrinhDo(int capBacTrinhDo) {
		this.capBacTrinhDo = capBacTrinhDo;
	}

	@Override
	public String toString() {
		return  tenTrinhDo ;
	}

}
