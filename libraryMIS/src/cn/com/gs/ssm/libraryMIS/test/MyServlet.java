package cn.com.gs.ssm.libraryMIS.test;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet implements Servlet {
	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	public MyServlet() {
		System.out.println("执行顺序1：MyServlet构造方法");
	}
	
	@PostConstruct
	public void test1() {
		System.out.println("执行顺序2：@PostConstruct修饰的void方法");
	}
	
	
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("执行顺序3：init方法");
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("执行顺序4：service方法");
	}
	
	@PreDestroy
	public void test2() {
		System.out.println("执行顺序5：@PreDestroy修饰的void方法");
	}
	
	@Override
	public void destroy() {
		System.out.println("执行顺序6：destroy方法");
	}
	
	public static void main(String[] args) {
		new MyServlet();
	}

}
