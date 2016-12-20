package cn.web.service;

import java.io.Serializable;
import java.util.List;

import cn.web.entity.Role;

public interface RoleService {
		//新增
		public void save(Role role);
		//更新
		public void update(Role role);
		//根据id删除O
		public void delete(Serializable id);
		//根据id查找
		public Role findObjectById(Serializable id);
		//查找列表
		public List<Role> findObjects();
}
