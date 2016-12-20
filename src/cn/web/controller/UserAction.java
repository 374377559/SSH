package cn.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.web.entity.User;
import cn.web.entity.UserRole;
import cn.web.exception.ActionException;
import cn.web.exception.ServiceException;
import cn.web.service.RoleService;
import cn.web.service.UserService;

/**
 * UserActon处理类
 * @author 杨建
 * 2016年12月14日下午1:54:36
 */
@Controller("userAction")
public class UserAction extends BaseAction{
	
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	
	private List<User> userList;
	private User user;
	private String[] selectedRow;
	//图片上传
	private File headImg;
	private String headImgContentType;
	private String headImgFileName;
	//Excle
	private File userExcel;
	private String userExcelContentType;
	private String userExcelFileName;
	//角色获取
	private String[] userRoleIds;
	
	//列表页面
	public String listUI(){
		userList = userService.findObjects();
		return "listUI";
	}
	//跳转到新增页面
	public String addUI(){
		//加载角色列表
		ActionContext.getContext().getContextMap().put("roleList", roleService.findObjects());
		
		return "addUI";
	}
	//保存新增
	public String add() throws ActionException{
		if(user != null){
			//处理头像
			if(headImg != null){
				  try {
				//1.保存到问价夹
				//获取保存的路径
				   String  filepath=ServletActionContext.getServletContext().getRealPath("upload/user");
				   String  fileName=UUID.randomUUID().toString().replace("-", "") + headImgFileName.substring(headImgFileName.lastIndexOf("."));
				   //复制文件
				   FileUtil.copyFile(headImg, new File(filepath,fileName));
				//2.设置用户头像路径
				   user.setHeadImg("user/" + fileName);
				} catch (IOException e) {
					throw new ActionException(e.getMessage());
				}
			}
			 userService.saveUserAndRole(user,userRoleIds);
		}
		return "list";
	}
	//跳转到编辑页面
	public String editUI(){
		//加载角色列表
		ActionContext.getContext().getContextMap().put("roleList", roleService.findObjects());
		
		if (user != null && user.getId() != null) {
			user = userService.findObjectById(user.getId());
			//处理角色回显
			List<UserRole> list = userService.getUserRolesByUserId(user.getId());
			if(list != null && list.size() > 0){
				userRoleIds = new String[list.size()];
				for(int i = 0; i < list.size(); i++){
					userRoleIds[i] = list.get(i).getId().getRole().getRoleId();
				}
			}
		}
		return "editUI";
	}
	//保存编辑
	public String edit() throws ActionException{
		if(user != null){
			if(headImg != null){
				  try {
				//1.保存到问价夹
				//获取保存的路径
				   String  filepath=ServletActionContext.getServletContext().getRealPath("upload/user");
				   String  fileName=UUID.randomUUID().toString().replace("-", "") + headImgFileName.substring(headImgFileName.lastIndexOf("."));
				   System.out.println("路径为："+filepath);
				   System.out.println("文件名称："+fileName);
				   //复制文件
				   FileUtil.copyFile(headImg, new File(filepath,fileName));
				//2.设置用户头像路径
				   user.setHeadImg("user/" + fileName);
					
				} catch (IOException e) {
					throw new ActionException(e.getMessage());
				}
			}
			
			userService.updateUserAndRole(user,userRoleIds);
		}
		return "list";
	}
	//删除
	public String delete(){
		if(user != null && user.getId() != null){
			userService.delete(user.getId());
		}
		return "list";
	}
	//批量删除
	public String deleteSelected(){
		if(selectedRow != null){
			for(String id: selectedRow){
				userService.delete(id);
			}
		}
		return "list";
	}
	//导出用户表
	public void exportExcel() throws ActionException{
		try {
			//查找用户列表
			userList = userService.findObjects();
			//输出
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("application/x-execl");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户列表.xls".getBytes(),"ISO-8859-1"));
			ServletOutputStream outPutStream = response.getOutputStream();
			userService.exportExcel(userList,outPutStream);
			if(outPutStream != null){
				outPutStream.close();
			}
		} catch (Exception e) {
			throw new ActionException(e.getMessage());
		}
	}
	//导入用户表
	public String importExcel() throws ServiceException{
		//获取文件
		if(userExcel != null){
			//判断是否是Excel
			if(userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){
				//导入
				userService.importExcel(userExcel,userExcelFileName);
			}
		}
		return "list";
	}
	//账号验证唯一
	public void verifyAccount() throws ActionException{
		try {
			//isNotBlank字符串工具类验证非空
			//获取账号
			if(user!=null && StringUtils.isNotBlank(user.getAccount())){
				List<User> list = userService.findUserByAccountId(user.getId(),user.getAccount());
				//根据账号验证是否存在
				String resoult="true";
				if(list != null && list.size() > 0){
					//说明账号已存在
					resoult="false";
				}
				//输出
				HttpServletResponse response=ServletActionContext.getResponse();
				response.setContentType("text/html");
				 ServletOutputStream out = response.getOutputStream();
				 out.write(resoult.getBytes());
				 out.close();
			}
		} catch (Exception e) {
			throw new ActionException(e.getMessage());
		}
		
	}
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
	public File getHeadImg() {
		return headImg;
	}
	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}
	public String getHeadImgContentType() {
		return headImgContentType;
	}
	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}
	public String getHeadImgFileName() {
		return headImgFileName;
	}
	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}
	public File getUserExcel() {
		return userExcel;
	}
	public void setUserExcel(File userExcel) {
		this.userExcel = userExcel;
	}
	public String getUserExcelContentType() {
		return userExcelContentType;
	}
	public void setUserExcelContentType(String userExcelContentType) {
		this.userExcelContentType = userExcelContentType;
	}
	public String getUserExcelFileName() {
		return userExcelFileName;
	}
	public void setUserExcelFileName(String userExcelFileName) {
		this.userExcelFileName = userExcelFileName;
	}
	public String[] getUserRoleIds() {
		return userRoleIds;
	}
	public void setUserRoleIds(String[] userRoleIds) {
		this.userRoleIds = userRoleIds;
	}
	
}
