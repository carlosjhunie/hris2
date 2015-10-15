package dai.hris.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * 
 * @author danielpadilla
 * 

 *
 */

public class EmployeeMemo implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	
	private int employeeMemoId;
	private String memoFiledDate;
	private int toRecipientEmpId;	
	private String ccRecipient;
	private int createdBy;
	private String fromSender;
	private String subject;
	private String message;
	private String remarks;
	
	
	
	public int getEmployeeMemoId() {
		return employeeMemoId;
	}
	public void setEmployeeMemoId(int employeeMemoId) {
		this.employeeMemoId = employeeMemoId;
	}
	public String getMemoFiledDate() {
		return memoFiledDate;
	}
	public void setMemoFiledDate(String memoFiledDate) {
		this.memoFiledDate = memoFiledDate;
	}
	public int getToRecipientEmpId() {
		return toRecipientEmpId;
	}
	public void setToRecipientEmpId(int toRecipientEmpId) {
		this.toRecipientEmpId = toRecipientEmpId;
	}
	public String getCcRecipient() {
		return ccRecipient;
	}
	public void setCcRecipient(String ccRecipient) {
		this.ccRecipient = ccRecipient;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public String getFromSender() {
		return fromSender;
	}
	public void setFromSender(String fromSender) {
		this.fromSender = fromSender;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
