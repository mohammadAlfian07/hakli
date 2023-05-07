package com.sds.hakli.pojo;

public class BrivaInquiryResp {

	private Boolean status;
    private String responseDescription;
    private String responseCode;
    private BrivaStatus data;
    
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
	public BrivaStatus getData() {
		return data;
	}
	public void setData(BrivaStatus data) {
		this.data = data;
	}
    
    
}
