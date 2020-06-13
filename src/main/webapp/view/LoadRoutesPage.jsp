<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Load Routes Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/style.css">
</head>

<body>

    <form action="${pageContext.request.contextPath}/loadRoutesFromXml" method="post" enctype="multipart/form-data">
        <p>
            Файл:
            <input type="file" name="file" />
            <input type="submit" value="Загрузить" />
        </p>
    </form>

    <jsp:include page="/printRoutesFromXml" />

    <form action="${pageContext.request.contextPath}/view/MainPage.jsp">
        <p>
            <input type="submit" value="Назад на главную" />
        </p>
    </form>

</body>
</html>
