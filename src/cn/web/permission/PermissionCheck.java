package cn.web.permission;

import cn.web.entity.User;

public interface PermissionCheck {
	/**
	 * 判断用户是否有用户对应的权限
	 * @param user 用户
	 * @param code 权限
	 * @return
	 * boolean
	 */
	public boolean isAccessible(User user, String code);

}
