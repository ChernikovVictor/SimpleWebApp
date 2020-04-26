<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/style.css" />
</head>
<body>

    <p>
        ${requestScope.message}
    </p>

    <p>
        <a href="${pageContext.request.contextPath}/view/LoadRoutesPage.jsp">Назад</a>
    </p>

</body>
</html>
