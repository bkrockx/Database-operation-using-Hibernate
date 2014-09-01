import java.io.*;

import java.sql.*;
import java.util.*;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/editData1")
public class editData1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public editData1() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Connection conn = null;
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=Tpg@1234");
			String query = "select * from users where id="+id;
			Statement stmt = conn.createStatement();
			
			stmt = conn.createStatement();
			ResultSet  rs = stmt.executeQuery(query);
			
			ArrayList al=null;
			ArrayList UserList =new ArrayList();


			while(rs.next())
			{
				al  = new ArrayList();
				al.add(rs.getInt(1));
				al.add(rs.getString(2));
				al.add(rs.getString(3));
				al.add(rs.getString(4));
				UserList.add(al);
			}

			request.setAttribute("UserList",UserList);
			
			RequestDispatcher dispatcher1 = getServletContext().getRequestDispatcher("/update.jsp");
			dispatcher1.forward(request,response);
			
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
