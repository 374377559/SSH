package cn.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.web.constant.Constant;
import cn.web.entity.User;
import cn.web.permission.PermissionCheck;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		String uri=request.getRequestURI();
		//判断是否是登录的地址
		if(!uri.contains("login_")){
			if(request.getSession().getAttribute(Constant.USER) != null){
				//说明已经登录过
				//判断是否访问纳税服务系统
				if(uri.contains("/nsfw/")){
					//访问纳税服务系统
					 User user =(User)request.getSession().getAttribute(Constant.USER);
					 PermissionCheck pc=null;
					 if(pc.isAccessible(user,"nsfw")){
						 //说明有权限放行
						 chain.doFilter(request, response);
					 }else{
						//没有权限
						 response.sendRedirect(request.getContextPath() + "/login_noPermissionUI.jsp.action");
					 }
					 
				}else{
					chain.doFilter(request, response);
				}
				
			}else{
				response.sendRedirect(request.getContextPath() + "/login_toLoginUI.action");
			}
		}else{
			//通过放行
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
