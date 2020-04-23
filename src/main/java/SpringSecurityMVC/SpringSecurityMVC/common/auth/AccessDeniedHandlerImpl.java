package SpringSecurityMVC.SpringSecurityMVC.common.auth;

import java.io.IOException ;

import javax.servlet.RequestDispatcher ;
import javax.servlet.ServletException ;
import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

import org.springframework.security.access.AccessDeniedException ;
import org.springframework.security.web.WebAttributes ;
import org.springframework.security.web.access.AccessDeniedHandler ;
import org.springframework.stereotype.Component ;


public class AccessDeniedHandlerImpl implements AccessDeniedHandler
{
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
      throws IOException, ServletException
  {
    if (!response.isCommitted())
    {
      // Put exception into request scope (perhaps of use to a view)
      request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException) ;

      // forward to error page.
      String ext = request.getRequestURI() ;
      ext = ext.substring(ext.lastIndexOf('.')) ;
      RequestDispatcher dispatcher = request.getRequestDispatcher(
          AuthenticateController.URI + AuthenticateController.ACCESSDENIED + ext) ;
      dispatcher.forward(request, response) ;
    }
  }
}
