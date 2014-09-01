import java.io.*;
import java.sql.*;
import java.util.*;
import java.io.IOException;

import javax.management.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
//import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@WebServlet("/Display4.do")
public class Display4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SessionFactory factory;
    public Display4() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			factory = new Configuration().configure().buildSessionFactory();
			Session session1 = factory.openSession();
			org.hibernate.Transaction t = null;
			try {
				t = session1.beginTransaction();
				List user2 = session1.createQuery("From user1").list();
				
				Iterator itr = user2.iterator();
				
				ArrayList al=null;
				ArrayList userList =new ArrayList();
				
				
				while(itr.hasNext())
				{	
					user1 user1 = (user1)itr.next();
					al  = new ArrayList();
					
					al.add(user1.getId());
					al.add(user1.getName());
					al.add(user1.getDepartment());
					al.add(user1.getPassword());
					userList.add(al);
				}
	
				request.setAttribute("userList",userList);
				
				RequestDispatcher dispatcher1 = getServletContext().getRequestDispatcher("/home2.jsp");
				dispatcher1.forward(request,response);
				
			} catch (HibernateException e) {
		         if (t!=null) t.rollback();
		         	e.printStackTrace(); 
		    }
	}
}