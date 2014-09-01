import java.io.*;
import java.sql.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


@WebServlet("/deleteData1.do")
public class deleteData1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SessionFactory factory;   
    
    public deleteData1() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		factory = new Configuration().configure().buildSessionFactory();
		Session session2 = factory.openSession();
		
		Transaction t = null;
		
		try {
			t = session2.beginTransaction();
			user1 user = (user1)session2.get(user1.class,id);
			session2.delete(user);
			t.commit();
			
			response.sendRedirect("http://localhost:8080/First/Display3.do");
		}
		catch (HibernateException e) {
	         if (t!=null) t.rollback();
	         	e.printStackTrace(); 
	    }
		finally {
	         session2.close(); 
	    }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}