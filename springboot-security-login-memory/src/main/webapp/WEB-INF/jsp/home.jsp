<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <sec:csrfMetaTags/>
</head>
<body>
<sec:authorize access="isAnonymous()">
    로그인
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <sec:authentication var="user" property="principal"/>
    로그인 유저：${user.username}
</sec:authorize>
<h1>톱페이지</h1>
톱 페이지입니다.
<ul>
    <li><a href="user/user.jsp">일반 사용자용 페이지로</a></li>
    <sec:authorize access="hasRole('ADMIN')">
        <li><a href="admin/admin.jsp">관리자 전용 페이지로</a></li>
    </sec:authorize>
</ul>
<form action="logout" method="post">
    <sec:csrfInput/>
    <button>로그아웃</button>
</form>
</body>
</html>
