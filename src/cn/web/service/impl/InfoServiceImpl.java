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
public class InfoServiceImpl extends BaseServiceImpl<Info> implements InfoService {

	private InfoDao infoDao;
	
	@Resource
	public void setInfoDao(InfoDao infoDao) {
		super.setBaseDao(infoDao);
		this.infoDao = infoDao;
	}
	
	
	
}
