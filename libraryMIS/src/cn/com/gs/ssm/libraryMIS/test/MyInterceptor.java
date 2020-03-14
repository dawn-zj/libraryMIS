package cn.com.gs.ssm.libraryMIS.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {
	private final String sysUserSession = "username";

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		System.out.println("-----1:SysUserInterceptor-preHandle-----");
		Object sessionObj = request.getSession().getAttribute(sysUserSession);

		if (sessionObj != null) {
			return true;//表示继续流程（如调用下一个拦截器或处理器）
		}
		response.sendRedirect(request.getContextPath() + "/user/login.do");
		return false;//表示流程中断，不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应
	}
	
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		//后处理回调方法，实现处理器的后处理（但在渲染视图之前）
		System.out.println("-----2:SysUserInterceptor-postHandle-----");
	}
	
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		//整个请求处理完毕回调方法，即在视图渲染完毕时回调
		System.out.println("-----3:SysUserInterceptor-afterCompletion-----");
	}

}
