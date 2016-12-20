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
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;

	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

	@Override
	public void update(Role role) {
		//更新前先把原来的选中的清空，插入最新选中的
		roleDao.deleteRolePrivilegeByRoleId(role.getRoleId());
		//更新角色权限
		roleDao.update(role);
	}

	@Override
	public void delete(Serializable id) {
		roleDao.delete(id);
	}

	@Override
	public Role findObjectById(Serializable id) {
		return roleDao.findObjectById(id);
	}

	@Override
	public List<Role> findObjects() {
		return roleDao.findObjects();
	}

}
