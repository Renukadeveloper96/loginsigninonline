package com.in.pathshala.onlineLoginSignup.Dto;
// BaseResponseVO
public class BaseResponseDTO {
	public BaseResponseDTO() {
	}
	private Object data;

	public Object getData() {
		return data;
	}
	@Override
	public String toString() {
		return "BaseResponseDTO [data=" + data + ", error=" + error + ", httpCode=" + httpCode + "]";
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(String httpCode) {
		this.httpCode = httpCode;
	}

	private boolean error;
	private String httpCode;

}
