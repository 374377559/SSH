package cn.web.dao;

import java.io.Serializable;
import java.util.List;

import cn.web.entity.User;
import cn.web.entity.UserRole;

/**
 * 
 * @author 杨建
 * 2016年12月14日下午2:18:12
 */
public interface UserDao extends BaseDao<User>{
	/**
	 * 更加ID查询用户账号
	 * @param id
	 * @param account
	 * @return
	 * List<User>
	 */
	public List<User> findUserByAccountId(String id, String account);
	
	/**
	 * 保存用户角色
	 * @param userRole
	 * void
	 */
	public void saveUserRole(UserRole userRole);
	/**
	 * 根据用户ID删除用户角色
	 * @param id
	 * void
	 */
	public void deleteUserRoleByUserId(Serializable id);
	/**
	 * 根据用户Id获取对应的所有用户角色
	 * @param id
	 * @return
	 * List<UserRole>
	 */
	public List<UserRole> getUserRolesByUserId(String id);
	/**
	 * 根据用户账查找用户
	 * @param account
	 * @param password
	 * @return
	 * List<User>
	 */
	public List<User> findUserByAccountAndPass(String account, String password);
	
}
