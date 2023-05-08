package com.sds.hakli.pojo;

import java.util.List;

public class BrivaReportResp {

	private Boolean status;
    private String responseDescription;
    private String responseCode;
    private List<BrivaReport> data;
    
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getResponseDescription() {
		return responseDescription;
	}
	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public List<BrivaReport> getData() {
		return data;
	}
	public void setData(List<BrivaReport> data) {
		this.data = data;
	}
	
}
