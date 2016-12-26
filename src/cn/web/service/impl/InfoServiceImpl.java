package cn.web.service.impl;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.web.dao.InfoDao;
import cn.web.entity.Info;
import cn.web.service.InfoService;


/**
 * 消息业务
 * @author 杨建
 * 2016年12月21日下午4:10:07
 */
@Service("infoService")
public class InfoServiceImpl implements InfoService {

	@Resource
	private InfoDao infoDao;
	
	@Override
	public void save(Info info) {
		infoDao.save(info);
	}

	@Override
	public void update(Info info) {
		infoDao.update(info);
	}

	@Override
	public void delete(Serializable id) {
		infoDao.delete(id);
	}

	@Override
	public Info findObjectById(Serializable id) {
		return infoDao.findObjectById(id);
	}

	@Override
	public List<Info> findObjects() {
		return infoDao.findObjects();
	}

}
