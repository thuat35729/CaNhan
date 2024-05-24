<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 4/3/2024
  Time: 5:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh Muc</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</head>
<body class="container">
<form action="/DanhMuc/Add" method="post">
    <p>
        Ma Danh Muc
    </p>
    <input type="text" name="maDM" class="from-control">
    <p>
        Ten Danh Muc
    </p>
    <input type="text" name="tenDM" class="from-control">
    <p>
        Trang Thai
    </p>
    <div class="form-check form-check-inline">
        <input class="form-check-input" type="radio" name="trangthai" id="inlineRadio1" value="Active">
        <label class="form-check-label" for="inlineRadio1">Active</label>
    </div>
    <div class="form-check form-check-inline">
        <input class="form-check-input" type="radio" name="trangthai" id="inlineRadio2" value="Inactive">
        <label class="form-check-label" for="inlineRadio2">Inactive</label>
    </div>
    <div>
        <button type="submit" class="btn btn-success">ADD</button>
    </div>

</form>
<table class="table">
    <thead>
    <td>STT</td>
    <td>Ten Danh Muc</td>
    <td>Ma Danh Muc</td>
    <td>Trang Thai</td>
    <td>Ngay Tao</td>
    <td>Ngay Sua</td>
    <td>Chuc nang</td>
    </thead>
    <tbody>
    <c:forEach var="a" items="${listdm}" varStatus="i">
        <tr>
            <td>
                    ${i.index + 1}
            </td>
            <td>
                    ${a.tenDanhMuc}
            </td>
            <td>
                    ${a.maDanhMuc}
            </td>
            <td>
                    ${a.trangThai}
            </td>
            <td>
                    ${a.ngayTao}
            </td>
            <td>
                    ${a.ngaySua}
            </td>
            <td>
                <a href="/danh-muc/delete?id=${a.id}">
                    <button class="btn btn-danger">Delete</button>
                </a>
                <a href="/danh-muc/detail?id=${a.id}">
                    <button class="btn btn-warning">Detail</button>
                </a>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
