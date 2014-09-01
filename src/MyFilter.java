import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class MyFilter
 */

@WebFilter(urlPatterns={"*.jsp","*.do"})
public class MyFilter implements Filter {

	
	private ServletContext context;
	
    public MyFilter() {
       
    }

	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		response.setContentType("text/html");
		PrintWriter out  = response.getWriter();
		
		HttpServletRequest request1 = (HttpServletRequest)request;
		HttpServletResponse response1 = (HttpServletResponse)response;
		HttpSession session = request1.getSession(false);
		
		String uri = request1.getRequestURI();
		
		if( session==null || session.getAttribute("user")==null ){
			if(uri.endsWith("login.jsp")){
				chain.doFilter(request,response);
			}
			else{
				out.println("unauthorized access");
				response1.sendRedirect("login.jsp");
			}
		}
		else{
			chain.doFilter(request,response);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
	}

}
