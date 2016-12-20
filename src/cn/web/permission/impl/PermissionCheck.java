package cn.web.permission.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.web.entity.Role;
import cn.web.entity.RolePrivilege;
import cn.web.entity.User;
import cn.web.entity.UserRole;
import cn.web.service.UserService;

/**
 * 判断权限
 * @author 杨建
 * 2016年12月19日上午9:05:11
 */
public class PermissionCheck implements cn.web.permission.PermissionCheck{

	@Resource
	private UserService userService;
	
	@Override
	public boolean isAccessible(User user, String code) {
		//获取用户所有的角色
		 List<UserRole> list = user.getUserRole();
		 if(list == null){
			 list = userService.getUserRolesByUserId(user.getId());
		 }
		//根据每个角色对应权限进行对比
		 if(list != null && list.size() > 0){
			 for(UserRole ur:list){
				 Role role = ur.getId().getRole();
				 for(RolePrivilege rp: role.getRolePrivileges()){
					 //对比是否有code权限
					 if(code.equals(rp.getId().getCode())){
						 //调用权限
						 return true;
					 }
				 }
			 }
		 }
		 
		return false;
	}

}
