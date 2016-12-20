package cn.web.controller;


/**
 * 登录
 * @author 杨建
 * 2016年12月16日上午9:06:03
 */

public class HomeAction extends BaseAction {
	public String execute(){
		return "home";
	}
	//跳转到纳税服务系统首页
	public String frame(){
		return "frame";
	}
	public String top(){
		return "top";
	}
	public String left(){
		return "left";
	}
}
