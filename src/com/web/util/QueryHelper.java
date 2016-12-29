package com.web.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询工具类
 * @author 杨建
 * 2016年12月28日下午12:56:09
 */
public class QueryHelper {
	//from
	private String 	fromClause ="";
	//where
	private String whereClause ="";
	//order
	private String orderByClause ="";
	//排序顺序
	public static String ORDER_BY_DESC = "DESC";
	public static String ORDER_BY_ASC = "ASC";
	
	private List<Object> parameters;
	/**
	 * 构造from语句
	 * @param clazz 对象表名
	 * @param alias 参数
	 */
	public QueryHelper(Class clazz,String alias){
			fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}
	/**
	 * 构建where语句
	 * @param condition 查询条件语句
	 * @param params 查询条件中对应的值
	 * void
	 */
	public void addCondition(String condition,Object... params){
		if(whereClause.length() > 1){
			whereClause += " AND " + condition;
		}else{
			whereClause += " WHERE " + condition;
		}
		
		if(parameters == null){
			parameters= new ArrayList<Object>();
		}
		if(params != null){
			for(Object param: params){
				parameters.add(param);
			}
		}
	}
	/**
	 * 构建order by 语句
	 * @param property 排序顺序
	 * @param order 排序顺序
	 * void
	 */
	public void addOrderByProperty(String property,String order){
		if(orderByClause.length() > 1){
			orderByClause +="," +  property + " " + order;
		}else{
			orderByClause =" ORDER BY " +  property + " " + order;
		}
	}
	
	
	//查询sql语句
	public String getQueryListHql(){
		return fromClause + whereClause + orderByClause;
	}
	
	//查询计数sql语句
	public String getQueryCountHql(){
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}
		
	//查询sql语句中？号对应的参数集合
	public List<Object> getParameters(){
		return parameters;
	}
}
