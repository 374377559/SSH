package cn.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;

/**
 * 
 * @author 杨建
 * 2016年12月14日下午2:17:55
 */
public class SysResultAction extends StrutsResultSupport{

	@Override
	protected void doExecute(String arg0, ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		BaseAction action = (BaseAction)invocation.getAction();
	}

}
