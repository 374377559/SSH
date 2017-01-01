package cn.web.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.web.dao.ComplainDao;
import cn.web.entity.Complain;
import cn.web.service.ComplainService;

/**
 * 投诉处理
 * @author 杨建
 * 2016年12月30日上午9:46:55
 */
@Service("complainService")
public class ComplainServiceImpl extends BaseServiceImpl<Complain> implements ComplainService{
	@Resource
	private ComplainDao complainDao;
	
	@Resource
	public void setComplainDao(ComplainDao complainDao) {
		super.setBaseDao(complainDao);
		this.complainDao = complainDao;
	}


}
