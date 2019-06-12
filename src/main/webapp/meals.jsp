<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 09.06.2019
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Еда</h2>
<h3>
    <table>
        <tr>
            <th><h3>Дата</h3></th>
            <th><h3>Описание</h3></th>
            <th><h3>Калории</h3></th>
            <th></th>
        </tr>
        <jsp:useBean id="mealTos" scope="request" type="java.util.List"/>
        <c:forEach var="mealTo" items="${mealTos}">
            <tr
                    <c:if test="${mealTo.excess == true}">
                        style="color: red">
                    </c:if>
                    <c:if test="${mealTo.excess == false}">
                        style="color: green">
                    </c:if>

                <td>${mealTo.dateTime}</td>
                <td>${mealTo.description}</td>
                <td>${mealTo.calories}</td>
                <td>
                    <form method="get" action="<c:url value='/update'/>">
                        <input type="number" hidden name="id" value="${mealTo.id}" />
                        <input type="submit" value="Редактированть"/>
                    </form>
                </td>
                <td>
                    <form method="post" action="<c:url value='/delete'/>">
                        <input type="number" hidden name="id" value="${mealTo.id}" />
                        <input type="submit" name="delete" value="Удалить"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</h3>

<p><a href="add.jsp">Добавить</a></p>

</body>
</html>
