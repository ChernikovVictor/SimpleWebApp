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
        <a href="${pageContext.request.contextPath}/view/MainPage.jsp?kind=all">Все маршруты</a>
        <a href="${pageContext.request.contextPath}/view/MainPage.jsp?kind=trains">Только поезда</a>
        <a href="${pageContext.request.contextPath}/view/MainPage.jsp?kind=planes">Только самолеты</a>
    </p>

    <jsp:include page="/routesTable?${requestScope.kind}" />
    <br>

    <form action="${pageContext.request.contextPath}/view/AddRoutePage.jsp" method="get">
        <p>
            <input type="submit" value="Добавить" />
        </p>
    </form>

</body>
</html>