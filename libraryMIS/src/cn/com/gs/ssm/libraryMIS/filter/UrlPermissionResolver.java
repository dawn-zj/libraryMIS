package cn.com.gs.ssm.libraryMIS.filter;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;

/**
 * 自定义Url拦截后的解析
 * @author Administator
 *
 */
public class UrlPermissionResolver implements PermissionResolver {

	@Override
	public Permission resolvePermission(String permissionString) {
		// TODO Auto-generated method stub
		System.out.println("resolvePermission:----"+permissionString);
		return new UrlPathPermission(permissionString);
	}
	
	protected class UrlPathPermission implements Permission {
		private String permissionString;
		public UrlPathPermission(String permissionString) {
			this.permissionString = permissionString;
		}
		public String getPermissionString() {
			return permissionString;
		}
		
		/**
		 * Permission是Info里已经遍历好的每一个路径
		 */
		@Override
		public boolean implies(Permission p) {
			if (!(p instanceof UrlPathPermission)) {
				return false;
			}
			UrlPathPermission urlPermission = (UrlPathPermission)p;
			String allPermission = urlPermission.getPermissionString();
			if (allPermission.equals(permissionString)) {
				return true;
			}
			return false;
		}
		
		
	}

}
