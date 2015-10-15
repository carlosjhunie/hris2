<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>html form</title>
</head>
<body>

<form action="/hris/HTMLServlet" method="GET">
  <input type="checkbox" name="m_access" value="My Account"/>
  <input type="checkbox" name="m_access" value="File Management"/>
  <input type="checkbox" name="m_access" value="Employee"/>
  <input type="checkbox" name="m_access" value="Time Management"/>
  <input type="checkbox" name="m_access" value="Payroll"/>
  <input type="checkbox" name="m_access" value="Employees Loan"/>
  <input type="checkbox" name="m_access" value="Payroll Reports"/>
  <input type="submit"/>
</form>
</body>
</html>