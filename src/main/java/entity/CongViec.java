package entity;

public class CongViec {
	private String maCongViec;
	private String tenCongViec;
	private TrinhDo trinhDo;

	public CongViec() {
		super();
	}

	public String getMaCongViec() {
		return maCongViec;
	}

	public void setMaCongViec(String maCongViec) {
		this.maCongViec = maCongViec;
	}

	public String getTenCongViec() {
		return tenCongViec;
	}

	public void setTenCongViec(String tenCongViec) {
		this.tenCongViec = tenCongViec;
	}

	public TrinhDo getTrinhDo() {
		return trinhDo;
	}

	public void setTrinhDo(TrinhDo trinhDo) {
		this.trinhDo = trinhDo;
	}

	public CongViec(String maCongViec, String tenCongViec, TrinhDo trinhDo) {
		super();
		this.maCongViec = maCongViec;
		this.tenCongViec = tenCongViec;
		this.trinhDo = trinhDo;
	}

	public CongViec(String maCongViec) {
		super();
		this.maCongViec = maCongViec;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maCongViec == null) ? 0 : maCongViec.hashCode());
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
		CongViec other = (CongViec) obj;
		if (maCongViec == null) {
			if (other.maCongViec != null)
				return false;
		} else if (!maCongViec.equals(other.maCongViec))
			return false;
		return true;
	}

	
	

}
