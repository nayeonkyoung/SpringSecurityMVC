/*******************************************************************************
 * This program and the accompanying materials are made
 * available under the terms of the Inzent MCA License v1.0
 * which accompanies this distribution.
 *
 * Contributors:
 * Inzent Corporation - initial API and implementation
 *******************************************************************************/
package SpringSecurityMVC.SpringSecurityMVC.common.auth;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;

import SpringSecurityMVC.SpringSecurityMVC.common.auth.AuthenticationProvider.MyAuth;

/**
 * <code>AuthenticationSuccessHandler</code>
 *
 * @since 2018. 11. 16.
 * @version 5.0
 * @author Jaesuk
 */

public class AuthenticationSuccessHandler
		implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
	/**
	 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse,
	 *      org.springframework.security.core.Authentication)
	 */
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String targetUrl = "/handle";

		for(GrantedAuthority authority : authentication.getAuthorities()) {
			if(MyAuth.class.isInstance(authority)) {
				targetUrl = targetUrl.concat(MyAuth.class.cast(authority).getDefaultUrl());
				break;
			}
		}
		
//		Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//
//		if (authorities.contains("ROLE_ADM")) {
//			targetUrl = targetUrl.concat("/adm");
//		} else if (authorities.contains("ROLE_SUPER")) {
//			targetUrl = targetUrl.concat("/super");
//		} else if (authorities.contains("ROLE_USR")) {
//			targetUrl = targetUrl.concat("/pub");
//		} else {
//			throw new IllegalStateException("아닙니다");
//		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
//		request.getRequestDispatcher(targetUrl)
//				.forward(request, response);
	}
}
