package dai.hris.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * 
 * @author Ian Orozco
 * 

 *
 */

public class EmployeeNotification implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	
	private int empNotificationId;
	private String filedDate;
	private int toRecipientEmpId;	
	private String ccRecipient;
	private int createdBy;
	private String fromSender;
	private String subject;
	private String message;
	private String remarks;
	
	
	
	
	public int getEmpNotificationId() {
		return empNotificationId;
	}
	public void setEmpNotificationId(int empNotificationId) {
		this.empNotificationId = empNotificationId;
	}
	public String getFiledDate() {
		return filedDate;
	}
	public void setFiledDate(String filedDate) {
		this.filedDate = filedDate;
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
