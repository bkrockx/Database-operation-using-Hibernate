<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	session = request.getSession(false);
%>
	<div id="myDiv">
		<form>
		<table border='1' width='300' cellpadding='1' cellspacing='0'>
			<tr>
				<td colspan=4 align="center"></td>
			</tr>
			<tr>
				<td>Id</td>
				<td>Name</td>
				<td>Department</td>
				<td>Password</td>
			</tr>

			<%
				List Li = new ArrayList();
				Iterator lr;

				if (request.getAttribute("userList") != null
						&& request.getAttribute("userList") != "") {
					List userList = (ArrayList) request.getAttribute("userList");
					Iterator itr = userList.iterator();

					while (itr.hasNext()) {
						Li = (ArrayList) itr.next();
						lr = Li.iterator();
						Integer id = (Integer) lr.next();
						String name = (String) lr.next();
						String department = (String) lr.next();
						String password = (String) lr.next();
			%> 

			<tr>
				<td><%=id%></td>
				<td><%=name%></td>
				<td><%=department%></td>
				<td><%=password%></td>
			</tr>

			<%
				}
				}
			%>
		</table>
		</form>
	</div>
</body>
</html>