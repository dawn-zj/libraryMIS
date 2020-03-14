package cn.com.gs.ssm.libraryMIS.test;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class SessionFilter
 */
public class MyFilter implements Filter {
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("-----1:SessionFilter-init-----");
	}
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("-----2:SessionFilter-doFilter-----");
		//放行
		chain.doFilter(arg0, arg1);
	}
	
	@Override
	public void destroy() {
		System.out.println("-----3:SessionFilter-destroy-----");
	}
}
