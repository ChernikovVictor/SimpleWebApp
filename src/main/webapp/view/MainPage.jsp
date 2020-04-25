<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/style.css">
    <title>InfoSystem</title>
</head>

<body>
    <p>Schedule</p>
    <br>
    <br>

    <p>
        <a href="${pageContext.request.contextPath}/findRoutes?kind=all">Все маршруты</a>
        <a href="${pageContext.request.contextPath}/findRoutes?kind=trains">Только поезда</a>
        <a href="${pageContext.request.contextPath}/findRoutes?kind=planes">Только самолеты</a>
    </p>

    <%-- Таблица маршрутов    --%>
    <jsp:include page="/routesTable" />
    <br>

    <%-- Добавить маршрут    --%>
    <form action="${pageContext.request.contextPath}/view/AddRoutePage.jsp">
        <p>
            <input type="submit" value="Добавить"/>
        </p>
    </form>

    <%-- Сохранить как xml-документ    --%>
    <form action="${pageContext.request.contextPath}/xmlLoader" method="get">
        <p>
            <input type="submit" value="Сохранить"/>
        </p>
    </form>

</body>
</html>