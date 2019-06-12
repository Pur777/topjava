<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 11.06.2019
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактор</title>
</head>
<body>
<form method="post" action="<c:url value='/add'/>">
    <label><input type="datetime-local" name="datetime"></label>Дата<br>
    <label><input type="text" name="name"></label>Имя<br>
    <label><input type="number" name="calories"></label>Калории<br>
    <input type="submit" value="Ok" name="Ok">
</form>
</body>
</html>
