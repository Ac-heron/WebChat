package com.herohuang.util;

/**
 * JSON 数据请求返回内容
 *
 * @datetime 2010-8-12 下午01:45:25
 * @author libinsong1204@gmail.com
 */
public class ResponseData {
	//~ Static fields ==================================================================================================
	public static final ResponseData SUCCESS_NO_DATA = new ResponseData(true,"操作成功");
	public static final ResponseData FAILED_LOGIN_MIMA = new ResponseData(false,"密码不正确");
	public static final ResponseData FAILED_NO_DATA = new ResponseData(false);
	public static final ResponseData FAILED_DEL_OWNROLE = new ResponseData(false,"当前用户不能删除自己被授于的角色");
	public static final ResponseData DELETE_FAILED = new ResponseData(false,"删除失败");
	public static final ResponseData UPDATE_FAILED = new ResponseData(false,"删除失败");
	public static final ResponseData VERIFICATION_FAILED = new ResponseData(false,"验证码错误");
	public static final ResponseData DELETE_YISHENG_FAILED = new ResponseData(false,"该医生下有排班，请先停用排班");
	public static final ResponseData DELETE_YISHENG_FAILED2 = new ResponseData(false,"不能删除当前医生");
	public static final ResponseData DELETE_KESHI_FAILED = new ResponseData(false,"该科室下有医生，请先删除医生");
	//~ Instance fields ================================================================================================
	private boolean success;
	private String type;
	private Object message;
	private String requestURI;
	private String execptionTrace;
	private String mess;
	
	//~ Constructors ===================================================================================================
	public ResponseData(boolean success) {
		this(success, null, null);
	}
	
	public ResponseData(boolean success, Object message) {
		this(success, null, message);
	}
	
	public ResponseData(boolean success, String type, Object message) {
		this.success = success;
		this.type = type;
		this.message = message;
	}
	
	public ResponseData(String message){
		this.mess = message;
	}

	//~ Methods ========================================================================================================
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public String getExecptionTrace() {
		return execptionTrace;
	}

	public void setExecptionTrace(String execptionTrace) {
		this.execptionTrace = execptionTrace;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}
}
