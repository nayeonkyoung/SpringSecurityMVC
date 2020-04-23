/*******************************************************************************
 * This program and the accompanying materials are made
 * available under the terms of the Inzent MCA License v1.0
 * which accompanies this distribution.
 *
 * Contributors:
 * Inzent Corporation - initial API and implementation
 *******************************************************************************/
package SpringSecurityMVC.SpringSecurityMVC.common.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import SpringSecurityMVC.SpringSecurityMVC.common.exception.PasswordMissMatchExceed;
import SpringSecurityMVC.SpringSecurityMVC.common.exception.PasswordMissMatchException;

/**
 * <code>AuthenticationProvider</code>
 *
 * @since 2014. 12. 17.
 * @version 5.0
 * @author Jaesuk
 */
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	public class MyAuth implements GrantedAuthority {
		final String authority;

		final String defaultUrl;
		
		final String logoutUrl;
		
		public MyAuth(String authority,String defaultUrl,String logoutUrl) {
			super();
			this.authority = authority;
			this.defaultUrl = defaultUrl;
			this.logoutUrl = logoutUrl;
		}

		public String getLogoutUrl() {
			return logoutUrl;
		}


		public String getDefaultUrl() {
			return defaultUrl;
		}

		@Override
		public String getAuthority() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class MyUser implements UserDetails {
		private String userId;
		private String password;
		private List<MyAuth> auth;

		public MyUser(String userId, String password, String auth, String loginurl, String logouturl) {
			// TODO Auto-generated constructor stub
			this.userId = userId;
			this.password = password;
			this.auth = new ArrayList<>();
			this.auth.add(new MyAuth(auth,loginurl, logouturl));
		}

		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getPassword() {
			// TODO Auto-generated method stub
			return password;
		}

		public String getUsername() {
			// TODO Auto-generated method stub
			return userId;
		}

		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}

		

	}

	public static final String ROLE_PREFIX = "ROLE_";

	private static final String IMANAGER_LOGIN_RETRY = "imanager.login.retry";

	protected Map<String, MyUser> UserService = new HashMap<>();

	protected final int loginRetryCount;

	public AuthenticationProvider() {
		this.loginRetryCount = Integer.getInteger(IMANAGER_LOGIN_RETRY, 5);
		this.UserService.put("admin", new MyUser("admin", "admin", "ROLE_ADM","/adm", "/admLogout"));
		this.UserService.put("super", new MyUser("super", "super", "ROLE_SUPER","/super", "/superLogout"));
		this.UserService.put("pub", new MyUser("pub", "pub", "ROLE_USR","/pub", "/pubLogout"));
	}

	/**
	 * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider#retrieveUser(java.lang.String,
	 *      org.springframework.security.authentication.UsernamePasswordAuthenticationToken)
	 */
	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		MyUser userTable = this.UserService.get(username);

		if (null == userTable) {
			throw new BadCredentialsException(this.messages
					.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}

		return userTable;
	}

	/**
	 * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider#additionalAuthenticationChecks(org.springframework.security.core.userdetails.UserDetails,
	 *      org.springframework.security.authentication.UsernamePasswordAuthenticationToken)
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		//
		System.out.println(userDetails.toString());
	}

}
