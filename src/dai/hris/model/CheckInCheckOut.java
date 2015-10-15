package dai.hris.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class CheckInCheckOut implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	private int checkInCheckOutID;
	public int getCheckInCheckOutID() {
		return checkInCheckOutID;
	}
	public void setCheckInCheckOutID(int checkInCheckOutID) {
		this.checkInCheckOutID = checkInCheckOutID;
	}
	public String getBADGENUMBER() {
		return BADGENUMBER;
	}
	public void setBADGENUMBER(String bADGENUMBER) {
		BADGENUMBER = bADGENUMBER;
	}
	public Timestamp getCHECKTIME() {
		return CHECKTIME;
	}
	public void setCHECKTIME(Timestamp cHECKTIME) {
		CHECKTIME = cHECKTIME;
	}
	public int getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(int verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	private String BADGENUMBER;
	private Timestamp CHECKTIME;
	private int verifyCode;
	private String sn;
	
	
	
 
}