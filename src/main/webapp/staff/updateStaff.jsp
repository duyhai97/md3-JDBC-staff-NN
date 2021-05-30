<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TruongDubai
  Date: 5/30/2021
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .message{
            color: darkmagenta;
        }
    </style>
</head>
<body>
<h1>Sửa thông tin nhân viên</h1>
<h2><a href="/">Quay lại trang chủ</a></h2>

<c:if test='${requestScope["message"] != null}'>
    <span class="message">${requestScope["message"]}</span>
</c:if>

<form  method="post">
    <table>
        <tr>
            <td>Tên nhân viên:</td>
            <td><input type="text" name="name"  value="${requestScope["staff"].getName()}"></td>
        </tr>
        <tr>
            <td>Địa chỉ:</td>
            <td><input type="text" name="address" value="${requestScope["staff"].getAddress()}"></td>
        </tr>

        <tr>
            <td>Phân loại:</td>
            <td>
                <select name="categoryList" >
                    <c:forEach items="${categoryList}" var="a">
                        <option value="${a.getId()}">${a.getName()}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Nhấn đê :v"></td>
        </tr>
    </table>
</form>


</body>
</html>
