package cn.web.entity;

import java.io.Serializable;

/**
 * 角色权限
 * @author 杨建
 * 2016年12月14日下午4:00:14
 */
public class RolePrivilege implements Serializable{
	
	private RolePrivilegeId id;
	
	public RolePrivilege() {
	}

	public RolePrivilege(RolePrivilegeId id) {
		this.id = id;
	}

	public RolePrivilegeId getId() {
		return id;
	}

	public void setId(RolePrivilegeId id) {
		this.id = id;
	}
}
