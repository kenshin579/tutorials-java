<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Spring MVC @ExceptionHandler Example</h2>

<c:if test="${not empty msg}">
    <h4>${msg}</h4>
</c:if>

</body>
</html>
