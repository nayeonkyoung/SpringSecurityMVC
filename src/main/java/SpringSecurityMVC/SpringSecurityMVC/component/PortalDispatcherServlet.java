package SpringSecurityMVC.SpringSecurityMVC.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sklee
 *
 */
public class PortalDispatcherServlet
										extends
											org.springframework.web.servlet.DispatcherServlet {
	/**
	 *
	 */
	private static final long	serialVersionUID	= 818298759624023537L;

	/**
	 *
	 */
	protected final Logger		logger				= LoggerFactory.getLogger(this.getClass());

	/**
	 *
	 */
	public PortalDispatcherServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param webApplicationContext
	 */
	public PortalDispatcherServlet(WebApplicationContext webApplicationContext) {
		super(webApplicationContext);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see org.springframework.web.servlet.DispatcherServlet#processHandlerException(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	protected ModelAndView processHandlerException(	HttpServletRequest request,
													HttpServletResponse response,
													Object handler,
													Exception ex) throws Exception {
		this.logger.error(	"handle error",
							ex);

		return super.processHandlerException(	request,
												response,
												handler,
												ex);
	}
}
