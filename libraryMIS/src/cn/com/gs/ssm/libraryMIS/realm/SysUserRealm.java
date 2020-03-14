package cn.com.gs.ssm.libraryMIS.realm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.com.gs.common.util.StringUtil;
import cn.com.gs.ssm.libraryMIS.dao.ButtonDao;
import cn.com.gs.ssm.libraryMIS.dao.MenuDao;
import cn.com.gs.ssm.libraryMIS.dao.RoleDao;
import cn.com.gs.ssm.libraryMIS.dao.sysUser.UserDao;
import cn.com.gs.ssm.libraryMIS.model.Button;
import cn.com.gs.ssm.libraryMIS.model.Menu;
import cn.com.gs.ssm.libraryMIS.model.Role;
import cn.com.gs.ssm.libraryMIS.model.User;

public class SysUserRealm extends AuthorizingRealm{
	@Resource
	private UserDao userDao;
	@Resource
	private RoleDao roleDao;
	@Resource
	private MenuDao menuDao;
	@Resource
	private ButtonDao buttonDao;
	
	protected String getRealmName(){
		return "sysUserRealm";
	}
	
	/**
	 * 认证
	 * 
	 * */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("------认证：doGetAuthenticationInfo--------");
		//1.从token中获取用户输入的用户名
		String account = token.getPrincipal().toString();
		System.out.println("输入的账户名："+account);
		//2.根据用户名去查询数据库，得到账密信息（要求用户名唯一）
		User user = userDao.getUserByAccount(account);
		if(user == null){
			return null;
		}
		/*
		 * 3.将信息封装给SimpleAuthenticationInfo：
		 * Object principal, 	身份信息：可以传入查询出的对象，也可以只传入用户名（传入对象subject就可以获取到）
		 * Object credentials, 	密码信息：传入数据库查询出的密码信息
		 * String realmName		Realm的名字
		 * 
		 * shiro内部将会比对token的密码和传入的数据库密码是否相等，返回SimpleAuthenticationInfo对象
		 * Subject就会得到该SimpleAuthenticationInfo信息，
		 * 最后在controller的登录方法里，由subject判断是否认证通过
		 * 		通过：获取对象存入session
		 * */
		//TODO 密码加密的情况，学习盐值
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getRealmName());
		return info;
	}


	/**
	 * 授权
	 * 前台发请求，先走shiro的过滤器链（包含自定义过滤器）
	 * 前台进入页面后，每个被shiro标记的按钮都会发请求来验证权限，效率很低
	 * 请整合redis缓存
	 * 
	 * */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		Integer menuId = null;
		Integer buttonId = null;
		Menu menu = null;
		Button button = null;
		System.out.println("-----------授权方法------------");
		//1.获取已登录的个人身份信息(认证时传入SimpleAuthenticationInfo的个人信息)
		User user = (User)principal.getPrimaryPrincipal();
		
		//TODO 已集成ehcache 学习整合Redis，先查缓存，缓存中没有再查数据库
		
		//2.获取该用户的已授权按钮信息（菜单级别暂时用NetSeal的写法）
		Role role = roleDao.getRoleById(user.getRoleId());
		//TODO 已实现按钮用shiro标签的实现，基于url的暂未实现
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//3.将菜单的code信息（shiro要求的授权规则）传给SimpleAuthorizationInfo
		String menuIds = role.getMenuId();
		String[] menuIdArr = menuIds.split(",");
		for(String tempId : menuIdArr){
			tempId = tempId.trim();
			menuId = Integer.parseInt(tempId);
			menu = menuDao.getMenuById(menuId);
			if(StringUtil.isNotBlank(menu.getMenuCode())) {
				info.addStringPermission(menu.getMenuCode());
			}
			//添加url，在自定义过滤器中控制访问
			info.addStringPermission(menu.getUrl());
		}
		//4.将按钮的code信息（shiro要求的授权规则）传给SimpleAuthorizationInfo
		String buttonIds = role.getButtonId();
		String[] buttonIdArr = buttonIds.split(",");
		for(String tempId : buttonIdArr){
			tempId = tempId.trim();
			buttonId = Integer.parseInt(tempId);
			button = buttonDao.getButtonById(buttonId);
			info.addStringPermission(button.getButtonCode());
		}
		//添加url，在自定义过滤器中控制访问
		info.addStringPermission(button.getUrl());
		return info;
	}

}
