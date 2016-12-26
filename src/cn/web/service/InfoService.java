package cn.web.service;

import java.io.Serializable;
import java.util.List;

import cn.web.entity.Info;

/**
 * 消息接口
 * @author 杨建
 * 2016年12月21日下午4:10:25
 */
public interface InfoService {
		//新增
		public void save(Info info);
		//更新
		public void update(Info info);
		//根据id删除
		public void delete(Serializable id);
		//根据id查找
		public Info findObjectById(Serializable id);
		//查找列表
		public List<Info> findObjects();
}
