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

    <jsp:include page="/routesTable" />
    <br>

    <form action="${pageContext.request.contextPath}/view/AddRoutePage.jsp" method="get">
        <p>
            <input type="submit" value="Добавить" />
        </p>
    </form>

</body>
</html>