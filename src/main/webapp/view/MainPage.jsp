<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/style.css">
    <title>InfoSystem</title>
</head>

<body>
    <p>Schedule</p>
    <br>

    <form action="${pageContext.request.contextPath}/findRoutesByCityName">
        <p>
            Поиск по городам:
            <input type="text" name="cityName" />
            <input type="submit" value="Поиск"/>
        </p>
    </form>
    <br>

    <p>
        <a href="${pageContext.request.contextPath}/findRoutesByTransportKind?kind=all">Все маршруты</a>
        <a href="${pageContext.request.contextPath}/findRoutesByTransportKind?kind=trains">Только поезда</a>
        <a href="${pageContext.request.contextPath}/findRoutesByTransportKind?kind=planes">Только самолеты</a>
    </p>

    <%-- Таблица маршрутов    --%>
    <jsp:include page="/routesTable" />
    <br>

    <div style="width:800px;">

        <%-- Добавить маршрут    --%>
        <div style="float: left; width: 130px">
            <form action="${pageContext.request.contextPath}/view/AddRoutePage.jsp">
                <p>
                    <input type="submit" value="Добавить"/>
                </p>
            </form>
        </div>

        <%-- Сохранить как xml-документ    --%>
        <div style="float: right; width: 630px">
            <form action="${pageContext.request.contextPath}/saveAsXml" method="get">
                <p>
                    <input type="submit" value="Сохранить как..." />
                    <input type="text" name="filepath" value="C:\XML_STORAGE\" />
                </p>
            </form>
        </div>
    </div>

    <p>
        <a href="${pageContext.request.contextPath}/view/LoadRoutesPage.jsp">Загрузить из файла</a>
    </p>

</body>
</html>