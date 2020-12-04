<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Test</title>
    </head>
    <body>
        Test!<br>
        <c:forEach var="cook" items="${cookie}">
            <li>
                <p><c:out value="${cook.value.name}"/></p>
                <p><c:out value="${cook.value.value}"/></p>
            </li>
            Hello!
        </c:forEach>

    </body>
</html>
