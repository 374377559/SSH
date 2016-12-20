package cn.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.web.constant.Constant;
import cn.web.entity.User;
import cn.web.service.UserService;

public class LoginAction extends ActionSupport{
	
	private User user;
	@Resource
	private UserService userService;
	private String loginResult;
	
	//跳转到登录页面
	public String toLoginUI(){
		return "loginUI";
	}
	//登录
	public String login(){
		if(user != null){
			if(StringUtils.isNotBlank(user.getAccount()) && StringUtils.isNotBlank(user.getPassword())){
				//根据用户的账号和密码查询用户列表
				 List<User> list = userService.findUserByAccountAndPass(user.getAccount(),user.getPassword());
				if(list !=null && list.size() > 0){
					User user=list.get(0);
					//根据用户ID查询该用户所有角色
					user.setUserRole(userService.getUserRolesByUserId(user.getId()));
					ServletActionContext.getRequest().getSession().setAttribute(Constant.USER, user);
					 Log log = LogFactory.getLog(getClass());
					 log.info("用户名称为："+user.getName()+" 的用户登录了系统");
					 return "home";
				}else{
					loginResult = "账号和密码不正确！";
				}
				
			}else{
				loginResult = "账号或密码不能为空！！！";
			}
		}else{
			loginResult = "请输入账号和密码！！！";
		}
		return toLoginUI();
	}
	//退出
	public String logout(){
		ServletActionContext.getRequest().getSession().removeAttribute(Constant.USER);
		return toLoginUI();
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getLoginResult() {
		return loginResult;
	}
	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
	
	
}
