<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>

메시지 : ${message}<br>
메시지 : <c:out value="${message}" default="냉무" /><br>
팀 목록<br>
<ul>
  <c:forEach items="${list }" var="team">
    <li>${team }</li>
  </c:forEach>
</ul>

</body>
</html>










