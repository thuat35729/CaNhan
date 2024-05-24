<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 4/3/2024
  Time: 5:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>DanhMucDetail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</head>
<body class="container">
<form action="/DanhMuc/update" method="post" class="row">
    <p>
        id
    </p>
    <input name="id" value="${list.id}" readonly class="from-control">
    <p>
        Ma Danh Muc
    </p>
    <input type="text" name="maDM" value="${list.maDanhMuc}" class="from-control">
    <p>
        Ten Danh Muc
    </p>
    <input type="text" name="tenDM" value="${list.tenDanhMuc}" class="from-control">
    <p>
        Trang Thai
    </p>
    <div class="form-check form-check-inline">
        <input class="form-check-input" type="radio" name="trangthai" id="inlineRadio1" value="Active"
               <c:if test="${list.trangThai == 'Active' }">checked</c:if>>
        <label class="form-check-label" for="inlineRadio1">Active</label>
    </div>
    <div class="form-check form-check-inline">
        <input class="form-check-input" type="radio" name="trangthai" id="inlineRadio2" value="Inactive"
               <c:if test="${list.trangThai == 'Inactive' }">checked</c:if>>
        <label class="form-check-label" for="inlineRadio2">Inactive</label>
    </div>
    <div>
        <button type="submit" class="btn btn-success">Update</button>
    </div>

</form>
</body>
</html>
