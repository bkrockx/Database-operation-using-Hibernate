<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script language="javascript">


function editData(id)
{
	var newName = id+"name";
	var dep = id+"department";
	var pass = id+"password";
	var button = id+"button";
	
	var name = document.getElementById(newName).textContent;
	var department = document.getElementById(dep).textContent;
	var password = document.getElementById(pass).textContent;
	
	
	var newName2 = id+"NAME";
	var dep2 = id+"DEPARTMENT";
	var pass2 = id+"PASSWORD";
	
	console.log(name);
	
	
	var v = "<input type='text' value='"+name+"' id='"+newName+"'>";
	//console.log(v);
    document.getElementById(newName).innerHTML = "<input type='text' value='"+name+"' id='"+newName2+"'>";
	document.getElementById(dep).innerHTML = "<input type='text' value='"+department+"' id='"+dep2+"'>";
	document.getElementById(pass).innerHTML = "<input type='text' value='"+password+"' id='"+pass2+"'>";     	   

	document.getElementById(button).disabled = true;
}


function saveData(id){
	
	var xmlhttp1;
	
	var newName1 = id+"NAME";
	var dep1 = id+"DEPARTMENT";
	var pass1 = id+"PASSWORD";
	
	var name1 = document.getElementById(newName1).value;
	var department1 = document.getElementById(dep1).value;
	var password1 = document.getElementById(pass1).value;
	
	if(window.XMLHttpRequest){
		xmlhttp1 = new XMLHttpRequest();
	}
	else{
		xmlhttp1 = new ActiveXobject("Microsoft.XMLHTTP");
	}
	
    xmlhttp1.open("GET","http://localhost:8080/First/UpdateData1.do?id="+id+"&name="+name1+"&department="+department1+"&password="+password1,true);
	xmlhttp1.send(null);

}


function deleteData(id){
	var xmlhttp;
	if(window.XMLHttpRequest){
		xmlhttp = new XMLHttpRequest();
	}
	else{
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp.open("GET","http://localhost:8080/First/deleteData1.do?id="+id+"&format=fragment",true);
	xmlhttp.send(null);
	
	xmlhttp.onreadystatechange = function()
	{
		if(xmlhttp.readyState==4 && xmlhttp.status==200){
			document.getElementById("myDiv").innerHTML = xmlhttp.responseText;
		}
	}
	
	
}


function showData(){
	var xmlhttp2;
	if(window.XMLHttpRequest){
		xmlhttp2 = new XMLHttpRequest();
	}
	else{
		xmlhttp2 = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp2.open("GET","http://localhost:8080/First/Display4.do",true);
	xmlhttp2.send(null);
	
	xmlhttp2.onreadystatechange = function()
	{
		if(xmlhttp2.readyState==4 && xmlhttp2.status==200){
			document.getElementById("myDiv").innerHTML = xmlhttp2.responseText;
		}
	}
	
	
}



</script>

</head>
<body>
<%
	session = request.getSession(false);

%>

	<div id="myDiv">
		<form name="form11">
		<table border='1' width='300' cellpadding='1' cellspacing='0'>
			<tr>
				<td colspan=8 align="center"></td>
			</tr>
			<tr>
				<td>Id</td>
				<td>Name</td>
				<td>Department</td>
				<td>Password</td>
				<td>Edit</td>
				<td>Delete</td>
				<td>Show</td>
				<td>Save</td>
			</tr>

			<%
				List Li = new ArrayList();
				Iterator lr;

				if (request.getAttribute("userList") != null && request.getAttribute("userList") != "") {
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
				<td id="<%=id%>"><%=id%></td>
				<td id="<%=id%>name"><%=name%></td>
				<td id="<%=id%>department"><%=department%></td>
				<td id="<%=id%>password"><%=password%></td>

				<td><input type="button" name="edit"  id="<%=id%>button" value="edit" onclick="editData(<%=id%>);"></td>
				
				<td><input type="button" name="delete" value="Delete" onclick="deleteData(<%=id%>);"></td>
				<td><input type="button" name="show" value="show" onclick="showData();"></td>
				
				<td><input type="button" name="Save" value="Save" onclick="saveData(<%=id%>);"></td>
				
			</tr>

			<%
				}
				}
			%>
		</table>
		<input type="hidden" name="hdnText">
		</form>
	</div>
</body>
</html>