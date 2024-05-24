<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 28/03/2024
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<html>
<head>
  <title>Sản Phẩm</title>
</head>
<body class="container">
<form action="/san-pham/add" method="post">
  <div class="mb-3">
    <label class="form-label">Ma sản phẩm</label>
    <input type="text" class="form-control" name="maSanPham">
  </div>
  <div class="mb-3">
    <label class="form-label">Ten sản phẩm</label>
    <input type="text" class="form-control" name="tenSanPham">
  </div>

  <div class="row">
    <p class="col-4"> Trang thai
    </p>
    <div class="form-check col-4">
      <input class="form-check-input" type="radio" value="Active" name="trangThai">
      <label class="form-check-label">
        Active
      </label>
    </div>
    <div class="form-check col-4">
      <input class="form-check-input" type="radio" value="Inactive" name="trangThai">
      <label class="form-check-label">
        Inactive
      </label>
    </div>
  </div>
  <div class="mb-3">
    <label class="form-label">Ten danh mục</label>
    <select class="form-control" name="danhMuc">
      <c:forEach var="dm" items="${listdm}">
        <option value="${dm.id}">${dm.tenDanhMuc}</option>
      </c:forEach>
    </select>
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>
<table class="table">
  <thead>
  <tr>
    <td>ID</td>
    <td>Ma san pham</td>
    <td>Ten san pham</td>
    <td>Trang thai</td>
    <td>Ten danh muc</td>
    <td>Ngay tao</td>
    <td>Ngay sua</td>
    <td>Chuc nang</td>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${litsp}" var="sp" varStatus="i">
    <tr>
      <td>${sp.id}</td>
      <td>${sp.maSP}</td>
      <td>${sp.tenSP}</td>
      <td>${sp.trangThai}</td>
      <td>${sp.danhMuc.tenDanhMuc}</td>
      <td>${sp.ngayTao}</td>
      <td>${sp.ngaySua}</td>
      <td>
        <a href="/san-pham/detail?idsp=${sp.id}" class="btn btn-info">Detail</a>
        <a href="/san-pham/delete?id=${sp.id}" class="btn btn-info">Delete</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
