package cn.web.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.web.dao.RoleDao;
import cn.web.dao.UserDao;
import cn.web.entity.Role;
import cn.web.service.RoleService;

/**
 * 权限Service实现
 * @author 杨建
 * 2016年12月14日下午9:22:13
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
	
	private RoleDao roleDao;

	@Resource
	public void setRoleDao(RoleDao roleDao) {
		super.setBaseDao(roleDao);
		this.roleDao = roleDao;
	}


	@Override
	public void update(Role role) {
		//更新前先把原来的选中的清空，插入最新选中的
		roleDao.deleteRolePrivilegeByRoleId(role.getRoleId());
		//更新角色权限
		roleDao.update(role);
	}


}
