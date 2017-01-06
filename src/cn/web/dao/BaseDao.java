package cn.web.dao;

import java.io.Serializable;
import java.util.List;

import com.web.util.QueryHelper;

import cn.web.entity.Info;
import cn.web.page.PageResult;

/**
 * 对Dao的封装
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
		//条件查询
		List<T> finObjects(String hql, List<Object> parameters);
		//条件查询----条件助手
		public List<T> finObjects(QueryHelper queryHelper);
		//分页查询
		public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize);
		
}
