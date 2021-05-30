<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TruongDubai
  Date: 5/30/2021
  Time: 2:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm mới nhân viên</title>
    <style>
        .message{
            color: darkmagenta;
        }
    </style>
</head>
<body>
<h1> Create New Staff</h1>
<h2>
    <a href="/">Ve trang chu</a>
</h2>

<form  method="post">
    <c:if test='${requestScope["message"] != null}'>
        <h2 class="message">${requestScope["message"]}</h2>
    </c:if>
    <table>
        <tr>
            <td>Tên nhân viên:</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>Địa chỉ:</td>
            <td><input type="text" name="address"></td>
        </tr>

        <tr>
            <td>Phân loại</td>
            <td>
                <select name="categoryList" id="" multiple>
                    <c:forEach var="l" items="${categoryList}">
                        <option value="${l.getId()}">${l.getName()}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td></td>
            <td><input type="submit" value="Thêm mới"></td>
        </tr>
    </table>
</form>
</body>
</html>
