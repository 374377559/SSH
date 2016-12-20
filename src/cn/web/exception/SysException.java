package cn.web.exception;

/**
 * 设定全局异常
 * @author 杨建
 * 2016年12月14日上午11:11:16
 */
public abstract class SysException extends Exception{
	private String errorMsg;
	
	public SysException() {
		
		super();
	}

	public SysException(String message, Throwable cause) {
		super(message, cause);
		errorMsg = message;
	}

	public SysException(String message) {
		super(message);
		errorMsg = message;
	}

	public SysException(Throwable cause) {
		super(cause);
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
}
