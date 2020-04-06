<%@ page import="application.dao.CityDAO" %>
<%@ page import="application.service.RouteService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/style.css">
    <title>Add Transport</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/addRoute" method="post">

    <b>Введите информацию о рейсе</b><br>

    <p>
        Откуда:
        <select name="departure">
            <jsp:include page="/citiesComboBox" />
        </select>
    </p>

    <p>
        Куда:
        <select name="destination">
            <jsp:include page="/citiesComboBox" />
        </select>
    </p>

    <p>
        Время отправления:
        <input type="text" name="departure_time" />
    </p>

    <p>
        Время прибытия:
        <input type="text" name="arrival_time" />
    </p>

    <p>
        Вид транспорта:
        <select name="transport_kind">
            <jsp:include page="/transportsComboBox" />
        </select>
    </p>

    <p>
        <input type="submit" name="addToBaseBtn" value="Добавить в базу" />
    </p>

</form>

</body>
</html>