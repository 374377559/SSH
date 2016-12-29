 package cn.web.service;

import java.io.Serializable;
import java.util.List;

import com.web.util.QueryHelper;

import cn.web.entity.Info;

/**
 * 对Service的封装
 * @author 杨建
 * 2016年12月27日下午3:58:59
 */
public interface BaseService<T> {
		//新增
		public void save(T entity);
		//更新
		public void update(T entity);
		//根据id删除
		public void delete(Serializable id);
		//根据id查找
		public T findObjectById(Serializable id);
		//查找列表
		public List<T> findObjects();
		//条件查询-----该注解表示不推荐使用方法
		@Deprecated
		List<T> findObjects(String hql, List<Object> parameters);
		//根据条件查询
		List<Info> findObjects(QueryHelper queryHelper);
}
