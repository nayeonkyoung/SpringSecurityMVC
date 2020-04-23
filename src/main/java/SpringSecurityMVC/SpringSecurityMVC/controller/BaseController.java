/*******************************************************************************
 * This program and the accompanying materials are made
 * available under the terms of the Inzent MCA License v1.0
 * which accompanies this distribution.
 *
 * Contributors:
 * Inzent Corporation - initial API and implementation
 *******************************************************************************/
package SpringSecurityMVC.SpringSecurityMVC.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;


/**
 * <code>BaseController</code>
 *
 * @since 2011. 6. 15.
 * @version 1.x
 * @author Jaesuk Byon
 */
public abstract class BaseController {
	public static final String EXT_HTML = ".html";

	public static final String EXT_JSON = ".json";

	public static final String MODEL_MODE = "_client_mode";

	public static final String MODEL_OBJECT = "object";

	public static final String MODEL_ERROR = "error";

	public static final String MODEL_RESPONSE = "response";

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected HttpSession httpSession;

	/**
	 *
	 */

	private Object th;

	

	/**
	 * @param request
	 * @param model
	 * @see SpringSecurityMVC.SpringSecurityMVC.common.auth.AuthenticateController#initializerHttpSession(HttpServletRequest, Authentication)
	 */




	protected Throwable unwrapThrowable(Throwable throwable) {
		if (this.logger.isErrorEnabled()) { this.logger.error(	this.getThrowableMessage(throwable),
																throwable); }

		if (throwable instanceof DataAccessException) {
			Throwable cause = throwable;

			do {
				if (null == cause) { break; }

				cause = cause.getCause();
			} while (false == cause instanceof SQLException);

			return null == cause	? throwable
									: cause;
		}
		else {
			return throwable;
		}
	}

	protected String getThrowableMessage(Throwable throwable) {
		return throwable.toString();
	}
}
