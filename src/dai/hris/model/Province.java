package dai.hris.model;

import java.io.Serializable;

public class Province implements Serializable{

	public Province() {
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L; 
	
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	private int provinceId;
	private String provinceName;
}
