package cn.web.controller;

import javax.annotation.Resource;

import org.apache.struts2.dispatcher.ActionContextCleanUp;

import com.opensymphony.xwork2.ActionContext;
import com.web.util.QueryHelper;

import cn.web.entity.Complain;
import cn.web.page.PageResult;
import cn.web.service.ComplainService;

/**
 * 投诉处理
 * @author 杨建
 * 2016年12月30日上午9:00:51
 */
public class ComplainAction extends BaseAction{
	@Resource
	private ComplainService complainService;
	private Complain complain;
	
	public String listUI(){
		//加载状体
		ActionContext.getContext().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
		 QueryHelper query = new QueryHelper(Complain.class, "c");
		 pageResult = complainService.getPageResult(query, getPageNo(), getPageSize());
		return "listUI";
	}
	
}
