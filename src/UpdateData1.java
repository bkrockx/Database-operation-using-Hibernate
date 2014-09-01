import java.io.*;
import java.util.*;
import java.sql.*;

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

import com.mysql.jdbc.Statement;



@WebServlet("/UpdateData1.do")
public class UpdateData1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SessionFactory factory;
       
    
    public UpdateData1() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String Id = request.getParameter("id");
		String name = request.getParameter("name");
		String department = request.getParameter("department");
		String password = request.getParameter("password");
		
		Integer id = Integer.parseInt(Id);
		
		factory = new Configuration().configure().buildSessionFactory();
		Session session3 = factory.openSession();
		
		Transaction t = null;
		
		try{
			
			t = session3.beginTransaction();
			
			user1 user = (user1)session3.get(user1.class,id);
			user.setName(name);
			user.setDepartment(department);
			user.setPassword(password);
			session3.update(user);
			t.commit();
			
			response.sendRedirect("http://localhost:8080/First/Display4.do");
		
		}
		catch (HibernateException e) {
	         if (t!=null) t.rollback();
	         	e.printStackTrace(); 
	    }
		finally {
	         session3.close(); 
	    }
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


}
