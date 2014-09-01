import java.util.*;
import javax.persistence.*;


public class user1 implements java.io.Serializable{
	
	private int id;
	private String name;
	private String department;
	private String password;
	
	public user1() {}
	
	public user1(int id,String name, String department, String password) {
	      this.id = id;
	      this.name = name;
	      this.department = department;
	      this.password = password;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
