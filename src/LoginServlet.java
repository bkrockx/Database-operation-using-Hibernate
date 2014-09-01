import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
			
		String user = request.getParameter("user");
		String password = request.getParameter("pwd");
			
		String user1 = null;
		String password1 = null;
			
		Connection conn = null;
		Statement st;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=Tpg@1234");
				
			String query = "select name,password from users where name='"+user+"'";
	
			st = conn.createStatement();
			ResultSet  rs = st.executeQuery(query);
				
			while(rs.next()){
				user1 = rs.getString(1);
				password1 = rs.getString(2);
			}
				
		}catch (Exception e) {
			e.printStackTrace();
		} 
			
		if(user.equalsIgnoreCase(user1) && password.equalsIgnoreCase(password1) ){
				
			HttpSession session = request.getSession();
			session.setAttribute("user",user);
				
			Cookie userName = new Cookie("user",user);
			response.addCookie(userName);
				
			response.sendRedirect("Success.jsp");
		}
		else{
			PrintWriter out1 = response.getWriter();
			out1.println("<h3 align = \"center\">UserName or Password is wrong. login again</h1><br>");
			//out1.println("<a href=http://localhost:8080/First/login.jsp">Back to login page</a>);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			rd.include(request, response);
			//response.sendRedirect("http://localhost:8080/First/login.jsp");
		}
	}
}
