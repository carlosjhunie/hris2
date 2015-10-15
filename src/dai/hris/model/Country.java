package dai.hris.model;

import java.io.Serializable;

public class Country  implements Serializable {
	private static final long serialVersionUID = 1L; 
	private int countryId;
	private String countryName;
	
	
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	

}
