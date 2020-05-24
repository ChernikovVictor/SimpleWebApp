<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Information</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/style.css" />
</head>

<body>

    <form action="${pageContext.request.contextPath}/routeUpdate?id=${requestScope.id}" method="post">
        <p>
            Номер:
            <input type="text" name="index" value="${requestScope.index}"/>
        </p>

        <p>
            Откуда: ${requestScope.departure.name} ${requestScope.departure.station}
            <select name="departure">
                <option value=${requestScope.departure.id}>&nbsp;</option>
                <jsp:include page="/citiesComboBox" />
            </select>
        </p>

        <p>
            Куда: ${requestScope.destination.name} ${requestScope.destination.station}
            <select name="destination">
                <option value=${requestScope.destination.id}>&nbsp;</option>
                <jsp:include page="/citiesComboBox" />
            </select>
        </p>

        <p>Время отправления: <input type="text" name="newDepartureTime" value=${requestScope.departure_time} /></p>
        <p>Время прибытия: <input type="text" name="newArrivalTime" value=${requestScope.arrival_time} /></p>
        <p>
            Транспорт: ${requestScope.transport.kind} ${requestScope.transport.name}
            <select name="transport_kind">
                <option value=${requestScope.transport.id}>&nbsp;</option>
                <jsp:include page="/transportsComboBox" />
            </select>
        </p>

        <p><input type="submit" value="Сохранить изменения"></p>
    </form>

</body>
</html>
