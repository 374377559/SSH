package cn.web.dao.impl;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.web.util.DBUtil;

import cn.web.dao.UserDao;
import cn.web.entity.User;
import cn.web.entity.UserRole;

/**
 * 
 * @author 杨建
 * 2016年12月14日下午2:18:38
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Override
	public List<User> findUserByAccountId(String id, String account) {
		String hq="FROM User WHERE account = ?";
		if(StringUtils.isNotBlank(id)){
			hq += " AND id!=?";
		}
		 Query query = getSession().createQuery(hq);
		 System.out.println(query);
		 query.setParameter(0,account);
		 if(StringUtils.isNotBlank(id)){
			 query.setParameter(1,account);
		 }
		return query.list();
	}

	@Override
	public void saveUserRole(UserRole userRole) {
		getSession().save(userRole);
	}

	@Override
	public List<UserRole> getUserRolesByUserId(String id) {
		Query query = getSession().createQuery("FROM UserRole WHERE id.userId=?");
		query.setParameter(0, id);
		return query.list();
	}

	@Override
	public List<User> findUserByAccountAndPass(String account, String password) {
		Query query = getSession().createQuery("FROM User WHERE account=? AND password=?");
		query.setParameter(0, account);
		query.setParameter(1, password);
		return query.list();
	}

	@Override
	public void deleteUserRoleByUserId(Serializable id) {
		Query query = getSession().createQuery("DELETE FROM UserRole WHERE id.userId=?");
		query.setParameter(0, id);
		query.executeUpdate();
	}

	

}
