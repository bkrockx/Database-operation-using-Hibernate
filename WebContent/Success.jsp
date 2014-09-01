<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String userName = null;
	String sessionId = null;
	String user = null;
	
	session = request.getSession(false);
	/*if(session.getAttribute("user")==null){
		response.sendRedirect("login.jsp");
	}
	*/
	//else{
		user = (String)session.getAttribute("user");
		
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("user")) userName = cookie.getValue();
				if(cookie.getName().equals("JSESSIONID")) sessionId = cookie.getValue();
			}
		}
	//}
%>

<h3> <%=userName %></h3>
<h2>Login successful</h2>
<br>
<a href="Front.jsp">enter your choice</a>
<br>
<form action="LogoutServlet.do" method="post">
<input type="submit" value="Logout" >
</form>

</body>
</html>