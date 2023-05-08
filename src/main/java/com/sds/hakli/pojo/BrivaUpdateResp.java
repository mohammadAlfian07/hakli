package com.sds.hakli.pojo;

public class BrivaUpdateResp {

	private Boolean status;
    private String responseDescription;
    private String responseCode;
    private BrivaDataUpdate data;
    
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
	public BrivaDataUpdate getData() {
		return data;
	}
	public void setData(BrivaDataUpdate data) {
		this.data = data;
	}
    
    
}
