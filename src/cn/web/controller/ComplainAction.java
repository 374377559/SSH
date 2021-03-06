package cn.web.controller;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.opensymphony.xwork2.ActionContext;
import com.web.util.QueryHelper;
import cn.web.entity.Complain;
import cn.web.entity.ComplainReply;
import cn.web.service.ComplainService;

/**
 * 投诉处理
 * @author 杨建
 * 2016年12月30日上午9:00:51
 */
public class ComplainAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	@Resource
	private ComplainService complainService;
	private Complain complain;
	private String startTime;
	private String endTime;
	private ComplainReply reply;
	private String strTitle;
	private String strState;
	
	public String listUI() throws Exception{
		//加载状体
		ActionContext.getContext().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
		 QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
		 if(StringUtils.isNotBlank(startTime)){//查询开始时间之后的投诉数据
			 startTime = URLDecoder.decode(startTime,"utf-8");
			 queryHelper.addCondition("c.compTime >= ?", DateUtils.parseDate(startTime+":00", "yyyy-MM-dd HH:mm:ss"));
		 }
		 if(StringUtils.isNotBlank(endTime)){//查询结束时间之前的投诉数据
				endTime = URLDecoder.decode(endTime, "utf-8");
				queryHelper.addCondition("c.compTime <= ?", DateUtils.parseDate(endTime+":59", "yyyy-MM-dd HH:mm:ss"));
		  }
		 
		 if(complain != null){
			 if(StringUtils.isNotBlank(complain.getState())){
				 queryHelper.addCondition("c.state=?",complain.getState());
			 }
			 if(StringUtils.isNotBlank(complain.getCompTitle())){
				 complain.setCompTitle(URLDecoder.decode(complain.getCompTitle(),"utf-8"));
				 queryHelper.addCondition("c.compTitle like ?","%" + complain.getCompTitle() + "%");
			 }
			 
		 }
	 	//安装状态升序排序
		queryHelper.addOrderByProperty("c.state", QueryHelper.ORDER_BY_ASC);
		//按照投诉时间升序排序
		queryHelper.addOrderByProperty("c.compTime", QueryHelper.ORDER_BY_ASC);
		 
		pageResult = complainService.getPageResult(queryHelper, getPageNo(), getPageSize());
		return "listUI";
	}
	
	//跳转到受理页面
	public String dealUI(){
		//加载状态集合
		ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
		if(complain != null){
			strTitle = complain.getCompTitle();
			strState = complain.getState();
			complain = complainService.findObjectById(complain.getCompId());
		}
		return "dealUI";
	}
	
	
	public String deal(){
		if(complain != null){
			Complain tem = complainService.findObjectById(complain.getCompId());
			//1、更新投诉的状态为 已受理
			if(!Complain.COMPLAIN_STATE_DONE.equals(tem.getState())){//更新状态为 已受理
				tem.setState(Complain.COMPLAIN_STATE_DONE);
			}
			//2、保存回复信息
			if(reply != null){
				reply.setComplain(tem);
				reply.setReplyTime(new Timestamp(new Date().getTime()));
				tem.getComplainReplies().add(reply);
			}
			complainService.update(tem);
		}
		return "list";
	}

	public Complain getComplain() {
		return complain;
	}

	public void setComplain(Complain complain) {
		this.complain = complain;
	}

	public String getStartTime() {
		return startTime;
	}
	
	public ComplainReply getReply() {
		return reply;
	}

	public void setReply(ComplainReply reply) {
		this.reply = reply;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}

	public String getStrState() {
		return strState;
	}

	public void setStrState(String strState) {
		this.strState = strState;
	}
	
	
	
}
