package cn.web.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.web.dao.RoleDao;
import cn.web.entity.Role;

/**
 * 角色Dao实现
 * @author 杨建
 * 2016年12月15日上午9:36:25
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	@Override
	public void deleteRolePrivilegeByRoleId(String roleId) {
		Query query = getSession().createQuery("DELETE FROM RolePrivilege WHERE id.role.roleId=?");
		query.setParameter(0, roleId);
		query.executeUpdate();
	}

	
}
