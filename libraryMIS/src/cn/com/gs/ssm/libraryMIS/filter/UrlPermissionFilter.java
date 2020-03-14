package cn.com.gs.ssm.libraryMIS.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

/**
 * 自定义Url权限拦截
 * 不可能把所有请求都存入库中，暂时只拦截二级菜单和页面菜单
 * @author Administator
 *
 */
public class UrlPermissionFilter extends PermissionsAuthorizationFilter{
	
	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {
		return super.isAccessAllowed(request, response, buildPermissionsFromRequest(request));
	}
	
	/**
	 * 这个过滤器配置在shiro的过滤器链中
	 * 从request中获取访问路径，
	 * 从shiro中取出当前用户可访问的路径，
	 * 进行对比
	 * @param request
	 * @return
	 */
	protected String[] buildPermissionsFromRequest(ServletRequest request) {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		String servletPath = servletRequest.getServletPath();
		System.out.println("UrlPermissionFilter:---"+servletPath);
		return new String[] { servletPath };
	}
}
