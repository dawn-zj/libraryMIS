package cn.com.gs.common.util.cert;

import java.security.Provider;
import java.security.Security;
import java.util.Enumeration;

public class ProviderTest {
	public static void main(String[] args) {
		Provider[] providers = Security.getProviders();
		for (int i = 0; i < providers.length; i++) {
			System.out.println("" + (i + 1) + ":" + providers[i]);
			for (Enumeration<?> e = providers[i].keys(); e.hasMoreElements();) {
				System.out.println("\t" + e.nextElement());
			}
		}
	}
}
