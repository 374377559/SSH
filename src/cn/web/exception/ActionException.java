package cn.web.exception;

/**
 * Action异常处理
 * @author 杨建
 * 2016年12月14日上午11:10:36
 */
public class ActionException extends SysException{
	public ActionException() {
		super("请求发生错误！");
	}
	
	public ActionException(String message) {
		super(message);
	}
}
