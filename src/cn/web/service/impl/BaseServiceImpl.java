package cn.web.service.impl;

import java.io.Serializable;
import java.util.List;

import com.web.util.QueryHelper;

import cn.web.dao.BaseDao;
import cn.web.dao.InfoDao;
import cn.web.entity.Info;
import cn.web.page.PageResult;
import cn.web.service.BaseService;

/**
 * BaseService接口封装的实现
 * @author 杨建
 * 2016年12月27日下午4:02:32
 */
public class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> baseDao;
	
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao=baseDao;
	}
	
	
	@Override
	public void save(T entity) {
		baseDao.save(entity);
	}

	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		baseDao.delete(id);
	}

	@Override
	public T findObjectById(Serializable id) {
		return baseDao.findObjectById(id);
	}

	@Override
	public List<T> findObjects() {
		return baseDao.findObjects();
	}

	@Override
	public List<T> findObjects(String hql, List<Object> parameters) {
		return baseDao.finObjects(hql, parameters);
	}


	@Override
	public List<Info> findObjects(QueryHelper queryHelper) {
		return baseDao.finObjects(queryHelper);
	}


	@Override
	public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize) {
		return baseDao.getPageResult(queryHelper,pageNo,pageSize);
	}

	
}
