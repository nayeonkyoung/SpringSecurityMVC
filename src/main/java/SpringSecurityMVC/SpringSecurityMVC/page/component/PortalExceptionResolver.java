package SpringSecurityMVC.SpringSecurityMVC.page.component;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

/**
 * @author sklee
 *
 */
public class PortalExceptionResolver
										extends
											AbstractHandlerExceptionResolver {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 *
	 */
	public PortalExceptionResolver() {
		super();
	}

	/**
	 * @see org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver#doResolveException(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	protected ModelAndView doResolveException(	HttpServletRequest request,
												HttpServletResponse response,
												Object handler,
												Exception ex) {
		this.logger.error(	"handle error",
							ex);
		try {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("",e);
			//e.printStackTrace();
		}
		return new ModelAndView();
	}

}
