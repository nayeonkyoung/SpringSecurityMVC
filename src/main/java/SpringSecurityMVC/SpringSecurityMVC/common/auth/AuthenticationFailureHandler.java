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

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Component;

import SpringSecurityMVC.SpringSecurityMVC.common.exception.PasswordMissMatchExceed;
import SpringSecurityMVC.SpringSecurityMVC.common.exception.PasswordMissMatchException;

/**
 * <code>AuthenticationFailureHandler</code>
 *
 * @since 2018. 11. 16.
 * @version 5.0
 * @author Jaesuk
 */
@Component
public class AuthenticationFailureHandler
												implements
												org.springframework.security.web.authentication.AuthenticationFailureHandler {
	/**
	 * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	public void onAuthenticationFailure(HttpServletRequest request,
										HttpServletResponse response,
										AuthenticationException exception)	throws IOException,
																			ServletException {
		HttpSession session = request.getSession(true);
		String userId = null;
		Cookie[] cookie = request.getCookies();
		for (int ii = 0; ii < cookie.length; ii++) {
			Cookie c = cookie[ii];
			if (c	.getName()
					.equals("userInputId"))
				userId = c.getValue();
		}

		request.setAttribute(	WebAttributes.AUTHENTICATION_EXCEPTION,
								exception);
		if (PasswordMissMatchException.class.isInstance(exception)) {
			// 비밀번호 틀렸을때 보이는 페이지로 redirect
			response.sendRedirect("passwordMissmatch.html");
			return;
		}
		else if (PasswordMissMatchExceed.class.isInstance(exception)) {
			// 비밀번호 초과 해지 신청 페이지로 redirect
			session.setAttribute(	"userId",
									userId);
			response.sendRedirect("passwordResetRequest.html");
			return;
		}

		request	.getRequestDispatcher(request.getRequestURI()
											.substring(request	.getContextPath()
																.length()))
				.forward(	request,
							response);

		request.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
