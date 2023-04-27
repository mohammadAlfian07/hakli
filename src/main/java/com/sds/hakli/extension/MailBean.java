package com.sds.hakli.extension;

import java.util.Map;

public class MailBean {

	private Integer appid;
	private String smtpname;
	private Integer smtpport;
	private String mailid;
	private String mailpassword;
	private String from;
	private String recipient;
	private String[] recipients;
	private String subject;
	private String bodymsg;
	private Map<String, String> attachments;
	private String attachment;
	private String filename;
	
	public Integer getAppid() {
		return appid;
	}
	public void setAppid(Integer appid) {
		this.appid = appid;
	}
	public String getSmtpname() {
		return smtpname;
	}
	public void setSmtpname(String smtpname) {
		this.smtpname = smtpname;
	}
	public Integer getSmtpport() {
		return smtpport;
	}
	public void setSmtpport(Integer smtpport) {
		this.smtpport = smtpport;
	}
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public String getMailpassword() {
		return mailpassword;
	}
	public void setMailpassword(String mailpassword) {
		this.mailpassword = mailpassword;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String[] getRecipients() {
		return recipients;
	}
	public void setRecipients(String[] recipients) {
		this.recipients = recipients;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBodymsg() {
		return bodymsg;
	}
	public void setBodymsg(String bodymsg) {
		this.bodymsg = bodymsg;
	}
	public Map<String, String> getAttachments() {
		return attachments;
	}
	public void setAttachments(Map<String, String> attachments) {
		this.attachments = attachments;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

}
