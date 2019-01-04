<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Insert title here</title>
</head>
<body>
<table>
    <tr>
        <td>도메인</td>
        <td>상위코드</td>
        <td>하위코드</td>
        <td>한글명</td>
        <td>영문명</td>
        <td>설명</td>
        <td>사용여부</td>
        <td>코드정렬순번</td>
        <td>변경자</td>
        <td>변경일자</td>
        <td>생성자</td>
        <td>생성일자</td>
    </tr>
    <c:forEach items="${list}" var="row">
        <tr>
            <td>${row.CD_DO}</td>
            <td>${row.CD_UP}</td>
            <td>${row.CD_DN}</td>
            <td>${row.CD_KOR}</td>
            <td>${row.CD_ENG}</td>
            <td>${row.CD_DESC}</td>
            <td>${row.USE_YN}</td>
            <td>${row.SORT_SEQ}</td>
            <td>${row.MOD_ID}</td>
            <td>${row.MOD_DT}</td>
            <td>${row.CRE_ID}</td>
            <td>${row.CRE_DT}</td>
        </tr>
    </c:forEach>
</table>
<br/>
<input type="button" value="이전" onclick="javascript:history.back();"/>
</body>
</html>