package cn.web.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import cn.web.entity.Info;
import cn.web.entity.User;
import cn.web.entity.UserRole;
import cn.web.exception.ServiceException;

/**
 * 
 * @author 杨建
 * 2016年12月14日下午2:18:59
 */
public interface UserService extends BaseService<User>{
	
		
		//导出用户列表
		public void exportExcel(List<User> userList, ServletOutputStream outPutStream)throws ServiceException;
		//导入用户列表
		public void importExcel(File userExcel, String userExcelFileName)throws ServiceException;
		//根据账号ID查询用户
		public List<User> findUserByAccountId(String id, String account);
		//添加用户角色
		public void saveUserAndRole(User user, String... roleIds);
		//修改用户角色
		public void updateUserAndRole(User user, String... roleIds);
		//更加用户Id获取对应的所有用户角色
		public List<UserRole> getUserRolesByUserId(String id);
		//根据用户的账号和密码查询用户列表
		public List<User> findUserByAccountAndPass(String account, String password);
	
}
