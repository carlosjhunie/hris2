package dai.hris.action;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SessionTimeoutFilter  implements javax.servlet.Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse  res = (HttpServletResponse) response;
	    String uri = req.getRequestURI();
		HttpSession session = req.getSession(false);
		
		if(session != null && !session.isNew() ) {
		    chain.doFilter(request, response);
		} 
		else if (uri.equalsIgnoreCase("/hris/login.jsp")) {
			 chain.doFilter(request, response);
		}
		else {
			res.sendRedirect("/hris/login.jsp");
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
