<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 11.06.2019
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="id" scope="request" type="java.lang.String"/>
<html>
<head>
    <title>Редактировать</title>
</head>
<body>
<form method="post" action="<c:url value='/update'/>">
    <label><input type="hidden" name="id" value="${id}"></label>
    <label><input type="datetime-local" name="datetime"></label>Дата<br>
    <label><input type="text" name="name"></label>Имя<br>
    <label><input type="number" name="calories"></label>Калории<br>
    <input type="submit" value="Ok" name="Ok">
</form>
</body>
</html>
