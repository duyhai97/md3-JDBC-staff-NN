<%--
  Created by IntelliJ IDEA.
  User: TruongDubai
  Date: 5/30/2021
  Time: 5:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete staff</title>
    <style>
        .message{
            color: darkmagenta;
        }
    </style>
</head>
<body>
<h1>Xóa nhân viên</h1>
<h2><a href="/">Quay lại trang chủ </a></h2>

<h4>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</h4>

<form action="" method="post">
    <h3>Bạn chắc chắn muốn xóa</h3>
    <fieldset>
        <table>
            <tr>
                <td>Tên nhân viên:</td>
                <td>${requestScope["staff"].getName()}</td>
            </tr>
            <tr>
                <td>Địa chỉ</td>
                <td>${requestScope["staff"].getAddress()}</td>
            </tr>

            <tr>
                <td></td>
                <td><input type="submit" value="Xóa"></td>
            </tr>

        </table>
    </fieldset>
</form>


</body>
</html>
