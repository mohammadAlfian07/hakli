package com.sds.hakli.pojo;

public class BrivaCreateResp {

	private Boolean status;
    private String errDesc;
    private String responseCode;
    private BrivaData data;
    
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getErrDesc() {
		return errDesc;
	}
	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public BrivaData getData() {
		return data;
	}
	public void setData(BrivaData data) {
		this.data = data;
	}
    
    
}
