package cn.com.gs.ssm.libraryMIS.controller.sysUser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.gs.common.util.ConfigUtil;
import cn.com.gs.common.util.date.DateUtil;
import cn.com.gs.common.util.logger.LoggerUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.gs.common.define.Constants;
import cn.com.gs.ssm.libraryMIS.aop.LibraryLog;
import cn.com.gs.ssm.libraryMIS.model.Menu;
import cn.com.gs.ssm.libraryMIS.model.User;
import cn.com.gs.ssm.libraryMIS.realm.SysUserSubject;
import cn.com.gs.ssm.libraryMIS.service.IUserService;

@Controller
@RequestMapping("/sysUser")
public class SysUserController {
	@Autowired
	private IUserService sysUserService;
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping("toLogin")
	public String toLogin() {
		System.out.println("------toLogin--------");
		return "login";
	}
	
	@RequestMapping("toShiroLogin")
	public String toShiroLogin() {
		System.out.println("------toShiroLogin--------");
		return "login";
	}
	
	@RequestMapping("toMain")
	public String toMain() {
		return "common/main";
	}
	
	@RequestMapping("toRefuse")
	public String toRefuse() {
		return "common/refuse";
	}
	
	@RequestMapping("timeout")
	public String timeout() {
		System.out.println("------timeout--------");
		return "timeout";
	}
	@RequestMapping("/login")
	@LibraryLog(optype = Constants.LOG_OPTYPE_SYSUSER_LOGIN)
	public String login(User user) {
		/* 方式一：使用shiro的登录认证
		 * 用户点击登录，先经过shiro的过滤器，无需认证本方法
		 * 进入本方法，封装token，获取subject并执行其login方法
		 * 进入配置的realm完成认证返回认证信息
		 * 由subject判断是否验证通过
		 * 
		 * */
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(),user.getPassword());
		try {
			subject.login(token);
			if(subject.isAuthenticated()){
				User sysUser = (User)subject.getPrincipal();
				//查询该用户的菜单列表
				List<Menu> menuList = sysUserService.getMenuByRoleId(sysUser.getRoleId());
				
				//设置过期时间：无操作30分钟后过期
				Long timeout = ConfigUtil.getInstance().getTimeoutTime();
				subject.getSession().setTimeout(timeout);//30分钟 = 30 * 60 * 1000
				subject.getSession().setAttribute("sysUser", sysUser);
				request.setAttribute("menuList", menuList);
				return "common/main";
			}else{
				request.setAttribute("message", "账号或密码不正确");
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.errorLog("登录失败", e);
			request.setAttribute("message", "账号或密码不正确");
			return "login";
		}
		
		//TODO 方式二：自定义登录认证（涉及公司内部加密算法）
		/*
		 * 方式二：自定义登录认证（涉及公司内部加密算法）
		 * 用户点击登录，先经过shiro的过滤器，无需认证本方法
		 * 进入本方法，执行自定义登录方法
		 * 
		 * 
		 * */

		
	}
	
	@RequestMapping("/welcome")
	//@LibraryLog(optype = Constants.LOG_OPTYPE_SYSUSERLOGIN)
	public String welcome() {
		return "common/welcome";
	}
}
