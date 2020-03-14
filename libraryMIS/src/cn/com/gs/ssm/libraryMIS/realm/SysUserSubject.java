package cn.com.gs.ssm.libraryMIS.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DelegatingSubject;

public class SysUserSubject extends DelegatingSubject{

	public SysUserSubject(PrincipalCollection principals, boolean authenticated, String host, Session session,
			boolean sessionCreationEnabled, SecurityManager securityManager) {
		super(principals, authenticated, host, session, sessionCreationEnabled, securityManager);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void login(AuthenticationToken token) throws AuthenticationException {
		// TODO 自定义login方法
		this.principals = principals;
		this.authenticated = true;
		super.login(token);
	}

}
