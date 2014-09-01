import java.io.*;

import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class user2 {
	
	private static SessionFactory factory;
	public static void main(String [] args) throws IOException{
		
		try{
			factory = new Configuration().configure().buildSessionFactory();
		
		}
		catch (Throwable ex) { 
			System.err.println("SessionFactory object could not be created"+ex);
			throw new ExceptionInInitializerError(ex); 
		}
		
		user2 User = new user2();
		//User.addUser(3,"cc","cse","cc");
		//User.deleteUser(Integer.valueOf(3));
		
		System.out.println("enter your choice");
		System.out.println("Enter_1"+" "+"show employee");
		System.out.println("Enter_2"+" "+"Create new employee");
		System.out.println("Enter_3"+" "+"Delete employee");
		System.out.println("Enter_4"+" "+"Update");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int choice = 0;
		
		String str = br.readLine();
		choice = Integer.parseInt(str);
		
		if(choice==2){
			System.out.println("Enter the id,name,department,password of the employee you want to add");
			
			String str1,str2,str3,str4;
			str1 = br.readLine();
			str2 = br.readLine();
			str3 = br.readLine();
			str4 = br.readLine();
			int id = Integer.parseInt(str1);
			
			User.addUser(id,str2,str3,str4);
		}
		else if(choice==1){
			User.showUser();
		}
		else if(choice==3){
			System.out.println("Enter the id of the employee you want to delete");
			String id1 = br.readLine();
			int Id1 = Integer.parseInt(id1);
			User.deleteUser(Integer.valueOf(id1));
		}
		else{
			
			System.out.println("Enter the id,name,department,password of the employee you want to edit");
			
			String str1,str2,str3,str4;
			str1 = br.readLine();
			str2 = br.readLine();
			str3 = br.readLine();
			str4 = br.readLine();
			int id = Integer.parseInt(str1);
			
			User.updateUser(Integer.valueOf(id),str2,str3,str4);
		}

	}
	
	public void addUser(int id,String name,String department,String password){
		Session session = factory.openSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			user1 User1 = new user1(id,name,department,password);
			session.save(User1);
			t.commit();
		}
		catch (HibernateException e) {
	         if (t!=null) t.rollback();
	         	e.printStackTrace(); 
	    }
		finally {
	         session.close(); 
	    }
	}
	 
	public void showUser(){
		Session session = factory.openSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			List User = session.createQuery("From user1").list();
			Iterator itr = User.iterator();
			
			while(itr.hasNext()){
				user1 user = (user1)itr.next();
				System.out.println(user.getId());
				System.out.println(user.getName());
				System.out.println(user.getDepartment());
				System.out.println(user.getPassword());	
			}
		}
		catch (HibernateException e) {
	         if (t!=null) t.rollback();
	         	e.printStackTrace(); 
	    }
		finally {
	         session.close(); 
	    }
	}
	
	public void updateUser(Integer id,String name,String department,String password){
		Session session = factory.openSession();
		Transaction t = null;
		try{
			
			t = session.beginTransaction();
			user1 user = (user1)session.get(user1.class,id);
			
			user.setName(name);
			user.setDepartment(department);
			user.setPassword(password);
			session.update(user);
			t.commit();
		}
		catch (HibernateException e) {
	         if (t!=null) t.rollback();
	         	e.printStackTrace(); 
	    }
		finally {
	         session.close(); 
	    }
		
	}
	
	public void deleteUser(Integer id){
		Session session = factory.openSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			user1 user = (user1)session.get(user1.class,id);
			session.delete(user);
			t.commit();
		}
		catch (HibernateException e) {
	         if (t!=null) t.rollback();
	         	e.printStackTrace(); 
	    }
		finally {
	         session.close(); 
	    }
	}
		
}

