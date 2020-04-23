package SpringSecurityMVC.SpringSecurityMVC.common.exception;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author sklee
 *
 */
public class PasswordMissMatchException
										extends
											AuthenticationException {

	/**
	 *
	 */
	private static final long serialVersionUID = 2789462109755768352L;

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
	public PasswordMissMatchException(	UserDetails userDetails,
										UsernamePasswordAuthenticationToken authentication) {
		super("password miss match");
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
