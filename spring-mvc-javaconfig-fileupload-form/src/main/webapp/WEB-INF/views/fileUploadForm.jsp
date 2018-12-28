<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>BORAJI.COM</title>
</head>
<body>

<h1>Spring MVC - File Upload Example</h1>
<hr/>

<h3>Single mediaFile Upload</h3>
<form action="singleFileUpload" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>Select File</td>
            <td><input type="mediaFile" name="mediaFile"></td>
            <td>
                <button type="submit">Upload</button>
            </td>
        </tr>
    </table>
</form>
<br/>
<hr/>
<h3>Multiple mediaFile Upload</h3>
<form action="multipleFileUpload" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>Select Files</td>
            <td><input type="mediaFile" name="mediaFile" multiple="multiple"></td>
            <td>
                <button type="submit">Upload</button>
            </td>
        </tr>
    </table>
</form>
<br>
<hr/>
<span style="color: red; font-size: 14px;">${msg}</span>

</body>
</html>