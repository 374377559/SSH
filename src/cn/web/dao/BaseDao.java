package cn.web.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author 杨建
 * 2016年12月14日下午2:18:04
 */
public interface BaseDao<T> {
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
		
}
