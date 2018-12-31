<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%--%>
    <%--request.setCharacterEncoding("UTF-8");--%>
<%--%>--%>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ADVENOH</title>
</head>
<body>

<h1>스프링 MVC - 파일 업로드 예제</h1>
<hr/>
<h3>단일 파일 업로드</h3>
<form action="singleFileUpload" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>Select File</td>
            <td><input type="file" name="mediaFile"></td>
            <td>
                <button type="submit">Upload</button>
            </td>
        </tr>
    </table>
</form>
<br/>
<hr/>
<h3>다중 파일 업로드</h3>
<form action="multipleFileUpload" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>Select Files</td>
            <td><input type="file" name="mediaFile" multiple="multiple"></td>
            <td>
                <button type="submit">Upload</button>
            </td>
        </tr>
    </table>
</form>
<br>
<hr/>
<h3>파일 업로드 + 추가 정보 by @RequestParam</h3>
<form action="singleFileUploadWithAdditionalData" method="post" enctype="multipart/form-data">
    Creator:<br>
    <input type="text" name="creator">
    <br>
    CallbackUrl:<br>
    <input type="text" name="callbackUrl">
    <br>
    <input type="file" name="mediaFile">
    <br><br>
    <button type="submit">Upload</button>
</form>
<br/>
<h3>파일 업로드 + 추가 정보 by @ModelAttribute</h3>
<form action="uploadFileModelAttribute" method="post" enctype="multipart/form-data">
    Creator:<br>
    <input type="text" name="creator">
    <br>
    CallbackUrl:<br>
    <input type="text" name="callbackUrl">
    <br>
    <input type="file" name="mediaFile">
    <br><br>
    <button type="submit">Upload</button>
</form>
<br/>
<span style="color: red; font-size: 14px;">${msg}</span>

</body>
</html>