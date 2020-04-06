<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Information</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/style.css" />
</head>

<body>
    <p>${requestScope.id}</p>
    <p>${requestScope.kind}</p>
    <p>${requestScope.name}</p>
    <p>${requestScope.capacity}</p>
</body>

</html>

