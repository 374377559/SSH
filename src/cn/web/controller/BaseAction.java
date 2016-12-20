package cn.web.controller;

import com.opensymphony.xwork2.ActionSupport;
/**
 * @author 杨建
 * 2016年12月14日下午1:55:28
 */
public abstract class BaseAction extends ActionSupport{
	
	protected String[] selectedRow;
	
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
}
