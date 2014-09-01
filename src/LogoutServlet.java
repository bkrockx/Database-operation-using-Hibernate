import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet.do")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LogoutServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.invalidate();
		}
		PrintWriter out1 = response.getWriter();
		out1.println("<h3 align = \"center\">Successfully logged off</h1><br>");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
		rd.include(request, response);
		//response.sendRedirect("./login.jsp");
	}

}
