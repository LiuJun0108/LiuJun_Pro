package com.rying.model;

/**
 * JSON返回消息
 * 
 * @author LiuJun
 *
 */
public class JsonMsg {
	private boolean success;// 是否成功
	private String message;// 提示信息
	private Object obj;// 其他信息

	public JsonMsg() {
	}

	public JsonMsg(boolean success) {
		this.success = success;
	}

	public JsonMsg(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public JsonMsg(boolean success, String message, Object obj) {
		this.success = success;
		this.message = message;
		this.obj = obj;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
