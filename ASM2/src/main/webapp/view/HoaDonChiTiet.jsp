<%--
  Created by IntelliJ IDEA.
  User: Dungvt22
  Date: 03/04/2024
  Time: 3:45 CH
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
<form action="/hdct/add" method="post">
  <div class="mb-3">
    <label for="disabledSelect" class="form-label">id hoa don</label>
    <select id="disabledSelect" class="form-select" name="hoaDon">
      <c:forEach items="${hoaDon}" var="hoaDon">
        <option value="${hoaDon.id}">${hoaDon.id}</option>
      </c:forEach>
    </select>
  </div>
  <div class="mb-3">
    <label for="disabledSelectt" class="form-label">ctsp</label>
    <select id="disabledSelectt" class="form-select" name="chiTietSanPham">
      <c:forEach items="${chiTietSanPham}" var="chiTietSanPham">
        <option value="${chiTietSanPham.idSanPham.id}">${chiTietSanPham.idSanPham.tenSP}</option>
      </c:forEach>
    </select>
  </div>

  <div class="mb-3">
    <label class="form-label">so luong mua </label>
    <input type="text" class="form-control" name="soluong" >
  </div>
  <div class="mb-3">
    <label class="form-label">gia ban </label>
    <input type="text" class="form-control" name="giaban" >
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
    <th>hoa don</th>
    <td>Ten san pham</td>
    <td>So luong</td>
    <td>Gia ban</td>
    <td>Tong tien</td>
    <td>Trang thai</td>
    <td>Chuc nang</td>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${listhdct}" var="a" varStatus="i">
    <tr>

      <td>${i.index+1}</td>
      <td>${a.id}</td>
      <td>${a.idHoaDon.id}</td>
      <td>${a.chiTietSanPham.sanpham.tensanpham}</td>
      <td>${a.soluong}</td>
      <td>${a.giaban}</td>
      <td>${a.soluong*a.giaban}</td>

      <td>${a.trangthai}</td>

      <td>
        <a class="btn btn-warning"  href="/hdct/detal?id=${a.id}">CHI TIET</a>

          <%--                <a class="btn btn-warning"  href="/hdct/delete?id=${a.id}">XOA</a>--%>
      </td>


    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
