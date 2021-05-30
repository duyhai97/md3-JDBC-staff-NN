<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TruongDubai
  Date: 5/30/2021
  Time: 1:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách nhân viên</title>
    <style>
        table {
            text-align: center;
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            text-align: left;
            padding: 8px;
        }
        tr:nth-child(even){background-color: #f2f2f2}
        th {
            background-color: #4CAF50;
            color: white;
        }
        button{
            border-radius: 8px;
            background: aqua;
        }
        body{
            text-align: center;
        }
    </style>
</head>
<body>
<h1>Danh sách nhân viên</h1>


<h2><a href="/">Trở về trang chủ</a></h2>

<table>
    <tr>
        <th>ID</th>
        <th>Tên nhân viên</th>
        <th>Địa chỉ</th>
        <th>Phân loại</th>
        <th colspan="2">Action</th>

    </tr>
    <c:forEach items='${requestScope["staffList"]}' var="m">
        <tr>
            <td>${m.getId()}</td>
                <%--            <td><a href="/student?action=view&id=${s.getId()}">${s.getName()}</a></td>--%>
            <td>${m.getName()}</td>
            <td>${m.getAddress()}</td>
            <td>${m.getCategory().getName()}</td>
            <td><a href="/staff?action=updateStaff&id=${m.getId()}">Chỉnh sửa</a></td>
            <td><a href="/staff?action=deleteStaff&id=${m.getId()}">Xóa</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
