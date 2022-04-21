public class SanPham {
	private String maSP;
	private String tenSP;
	private long giaThanh;
	private String loai;
	private String mauSac;
	
	public SanPham(String maSP, String tenSP, long giaThanh, String loai, String mauSac) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.giaThanh = giaThanh;
		this.loai = loai;
		this.mauSac = mauSac;
	}
	public SanPham() {
		super();
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public long getGiaThanh() {
		return giaThanh;
	}
	public void setGiaThanh(long giaThanh) {
		this.giaThanh = giaThanh;
	}
	public String getLoai() {
		return loai;
	}
	public void setLoai(String loai) {
		this.loai = loai;
	}
	public String getMauSac() {
		return mauSac;
	}
	public void setMauSac(String mauSac) {
		this.mauSac = mauSac;
	}
	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", giaThanh=" + giaThanh + ", loai=" + loai + ", mauSac="
				+ mauSac + "]";
	}
	
	
	
}
