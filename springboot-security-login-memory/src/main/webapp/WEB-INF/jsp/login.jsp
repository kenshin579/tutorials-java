<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>

<h1>로그인 페이지</h1>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    로그인 에러입니다.<br>
    예외 타입：${SPRING_SECURITY_LAST_EXCEPTION.getClass().name}<br>
    메시지：${SPRING_SECURITY_LAST_EXCEPTION.message}<br>
    <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
</c:if>
<form action="processLogin" method="post">
    <sec:csrfInput/>
    <dl>
        <dt>
            로그인 ID
        </dt>
        <dd>
            <input type="text" name="paramLoginId">
        </dd>
        <dt>
            패스워드
        </dt>
        <dd>
            <input type="password" name="paramPassword">
        </dd>
    </dl>
    <button>로그인</button>
</form>
</body>
</html>
