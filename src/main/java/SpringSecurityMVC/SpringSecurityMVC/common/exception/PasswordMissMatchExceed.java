package SpringSecurityMVC.SpringSecurityMVC.common.exception;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author sklee
 *
 */
public class PasswordMissMatchExceed
										extends
											AuthenticationException {

	/**
	 *
	 */
	private static final long serialVersionUID = -8697527285701224689L;

	/**
	 *
	 */
	private final UserDetails userDetails;

	/**
	 *
	 */
	private final UsernamePasswordAuthenticationToken authentication;

	/**
	 * @param msg
	 */
	public PasswordMissMatchExceed(	UserDetails userDetails,
									UsernamePasswordAuthenticationToken authentication) {
		super("password miss match count exceed");
		this.userDetails = userDetails;
		this.authentication = authentication;
	}

	/**
	 * @return the userDetails
	 */
	public UserDetails getUserDetails() {
		return this.userDetails;
	}

	/**
	 * @return the authentication
	 */
	public UsernamePasswordAuthenticationToken getAuthentication() {
		return this.authentication;
	}

}
