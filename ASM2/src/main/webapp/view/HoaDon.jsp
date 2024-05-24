<%--
  Created by IntelliJ IDEA.
  User: Dungvt22
  Date: 02/04/2024
  Time: 5:14 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>
<form action="/hoadon/add" method="post">
    <div class="mb-3">
        <label for="disabledSelect" class="form-label">Ten khach hang</label>
        <select id="disabledSelect" class="form-select" name="khachHang">
            <c:forEach items="${khachHang}" var="khachHang">
                <option value="${khachHang.id}">${khachHang.hoTen}</option>
            </c:forEach>
        </select>
    </div>

    <div class="mb-3">
        <label class="form-label">Dia chi </label>
        <input type="text" class="form-control" name="diachi" >
    </div>

    <div class="mb-3">
        <label class="form-label">SDT </label>
        <input type="text" class="form-control" name="sdt" >
    </div>
    <div class="row">
        <p class="col-4">Trang thai
        </p>
        <div class="form-check col-4">
            <input class="form-check-input" type="radio" value="Active" name="trangthai">
            <label class="form-check-label">
                Active
            </label>
        </div>
        <div class="form-check col-4">
            <input class="form-check-input" type="radio" value="Inactive" name="trangthai">
            <label class="form-check-label">
                Inactive
            </label>
        </div>
    </div>
    <button type="submit" class="btn btn-success">Submit</button>

</form>
<table class="table">
    <thead>
    <tr>
        <td>STT</td>
        <td>ID</td>
        <td>Ten khach hang</td>
        <td>Ngay tao</td>
        <td>Dia chi</td>
        <td>SDT</td>
        <td>Trang Thai</td>
        <td>Chuc nang</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listhd}" var="a" varStatus="i">
        <tr>
            <td>${i.index+1}</td>
            <td>${a.id}</td>
            <td>${a.idKhachHang.hoTen}</td>
            <td>${a.ngayTao}</td>
            <td>${a.diaChi}</td>
            <td>${a.soDienThoai}</td>
            <td>${a.trangThai}</td>
            <td>
                <a class="btn btn-warning" href="/hoadon/detal?id=${a.id}">CHI TIET</a>

                <a class="btn btn-warning" href="/hoadon/delete?id=${a.id}">XOA</a>
            </td>


        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
