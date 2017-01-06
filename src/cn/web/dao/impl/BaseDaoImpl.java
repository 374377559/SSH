package cn.web.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;

import com.web.util.DBUtil;
import com.web.util.QueryHelper;

import cn.web.dao.BaseDao;
import cn.web.entity.Info;
import cn.web.page.PageResult;

/**
 * 
 * @author 杨建
 * 2016年12月14日下午2:18:22
 */
public class BaseDaoImpl<T> extends DBUtil implements BaseDao<T> {

	Class<T> clazz;
	
	public BaseDaoImpl(){
		ParameterizedType pt =  (ParameterizedType)this.getClass().getGenericSuperclass();//BaseDaoImpl<User>
		clazz = (Class<T>)pt.getActualTypeArguments()[0];
	}
	
	@Override
	public void save(T entity) {
		getSession().save(entity);
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	public void delete(Serializable id) {
		getSession().delete(findObjectById(id));
	}

	@Override
	public T findObjectById(Serializable id) {
		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> findObjects() {
		 Query query = getSession().createQuery("FROM " + clazz.getSimpleName());
		return query.list();
	}
	
	@Override
	public List<T> finObjects(String hql,List<Object> parameters) {
		Query query = getSession().createQuery(hql);
		if(parameters != null){
			for(int i=0;i<parameters.size();i++){
				query.setParameter(i, parameters.get(i));
			}
		}
		return query.list();
	}

	@Override
	public List<T> finObjects(QueryHelper queryHelper) {
		Query query = getSession().createQuery(queryHelper.getQueryListHql());
		List<Object> parameters =queryHelper.getParameters();
		if(parameters != null){
			for(int i=0;i<parameters.size();i++){
				query.setParameter(i, parameters.get(i));
			}
		}
		return query.list();
	}

	@Override
	public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize) {
		Query query = getSession().createQuery(queryHelper.getQueryListHql());
		List<Object> parameters =queryHelper.getParameters();
		if(parameters != null){
			for(int i=0;i<parameters.size();i++){
				query.setParameter(i, parameters.get(i));
			}
		}
		if(pageNo < 1) pageNo = 1;
		
		query.setFirstResult((pageNo-1)*pageSize);//设置起始索引号
		query.setMaxResults(pageSize);
		List items = query.list();
		//获取总记录数
		Query queryCount = getSession().createQuery(queryHelper.getQueryCountHql());
		if(parameters != null){
			for(int i=0; i<parameters.size(); i++){
				queryCount.setParameter(i, parameters.get(i));
			}
		}
		
		long totalCount = (Long) queryCount.uniqueResult();
		return new PageResult(totalCount, pageNo, pageSize, items);
	}

}
