package cn.web.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.web.util.QueryHelper;

import cn.web.entity.Complain;
import cn.web.entity.User;
import cn.web.service.ComplainService;
import cn.web.service.UserService;

/**
 * 登录
 * @author 杨建
 * 2016年12月16日上午9:06:03
 */

public class HomeAction extends BaseAction {
	
	@Resource
	private UserService userService;
	@Resource
	private ComplainService complainService;
	
	private Map<String, Object> return_map;
	private Complain comp;
	
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
	//跳转到我要投诉
	public String complainAddUI(){
		return "complainAddUI";
	}
	
	//根据部门查询用户列表
	public void getUserJson(){
			try {
				//1.获取部门
				String dept = ServletActionContext.getRequest().getParameter("dept");
				if(StringUtils.isNotBlank(dept)){
					  QueryHelper queryHelper = new QueryHelper(User.class,"u");
					  queryHelper.addCondition("u.dept like ?","%" + dept);
					//2.根据部门查询用户列表
					  List<User> userList = userService.findObjects(queryHelper);
					//创建json对象
					  JSONObject jso = new JSONObject();
					  jso.put("msg", "success");
					  jso.accumulate("userList", userList);
					//3.根据用户列表以格式json字符串输出 
				  	HttpServletResponse response = ServletActionContext.getResponse();
					response.setContentType("text/html");
					ServletOutputStream outputStream;
					outputStream = response.getOutputStream();
					outputStream.write(jso.toString().getBytes("utf-8"));
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	//根据部门查询用户列表方法2
	public String getUserJson2(){
		try {
			//1、获取部门
			String dept = ServletActionContext.getRequest().getParameter("dept");
			if(StringUtils.isNotBlank(dept)){
				QueryHelper queryHelper = new QueryHelper(User.class, "u");
				queryHelper.addCondition("u.dept like ?", "%" +dept);
				//2、根据部门查询用户列表
				return_map = new HashMap<String, Object>();
				return_map.put("msg", "success");
				return_map.put("userList", userService.findObjects(queryHelper));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//保存投诉
	public void  complainAdd(){
		if(comp != null){
			comp.setState(Complain.COMPLAIN_STATE_UNDONE);
			comp.setCompTime(new Timestamp(new Date().getTime()));
			complainService.save(comp);
			
			try {
				//输出
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream;
				outputStream = response.getOutputStream();
				outputStream.write("success".getBytes("utf-8"));
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
	
	
	
	public Map<String, Object> getReturn_map() {
		return return_map;
	}
	public void setReturn_map(Map<String, Object> return_map) {
		this.return_map = return_map;
	}
	public Complain getComp() {
		return comp;
	}
	public void setComp(Complain comp) {
		this.comp = comp;
	}
	
	
}
