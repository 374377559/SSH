package cn.web.dao;

import cn.web.entity.Role;

/**
 * 角色Dao
 * @author 杨建
 * 2016年12月15日上午9:34:27
 */
public interface RoleDao extends BaseDao<Role> {

	public void deleteRolePrivilegeByRoleId(String roleId);
	
}
