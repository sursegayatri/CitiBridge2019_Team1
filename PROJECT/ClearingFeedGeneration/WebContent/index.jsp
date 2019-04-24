<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome to JSP</h1>
<hr>
<form action="JavaServlet">
<input type = "submit" value = "send"/>
</form>
</body>
</html>
<!-- When the form is submitted the action specified is executed.
MyServlet exists in the deployment descriptor web.xml
The <servlet-name>MyServlet is mapped to <servlet-class> MyServlet.java
 which is compiled into WEB-INF folder with same hierarchy
This "servlet-class" is mapped at runtime to "servlet-name"
 In next part we map the same servlet name with <servlet-URL>
 -->
