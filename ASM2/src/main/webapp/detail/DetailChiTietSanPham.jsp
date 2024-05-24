<%--
  Created by IntelliJ IDEA.
  User: Dungvt22
  Date: 03/04/2024
  Time: 2:23 CH
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
<form action="/ctsp/update?id=${detal.id}" method="post">
  <div class="mb-3">
    <label for="disabledSelect" class="form-label">Ten san pham</label>
    <select id="disabledSelect" class="form-select" name="sanpham">
      <c:forEach items="${sanPham}" var="sanPham">
        <option value="${sanPham.id}"
                <c:if test="${detail.idSanPham.id == sanPham.id}">selected</c:if>
        >${sanPham.tenSP}</option>
      </c:forEach>
    </select>
  </div>
  <div class="mb-3">
    <label for="disabledSelectt" class="form-label">Ten mau sac</label>
    <select id="disabledSelectt" class="form-select" name="mausac">
      <c:forEach items="${mauSac}" var="mauSac">
        <option value="${mauSac.id}"
                <c:if test="${detail.idMauSac.id == mauSac.id}">selected</c:if>
        >${mauSac.tenMau}</option>
      </c:forEach>
    </select>
  </div>
  <div class="mb-3">
    <label for="disabledSelecttt" class="form-label">Ten size</label>
    <select id="disabledSelecttt" class="form-select" name="sizesp">
      <c:forEach items="${Size}" var="sizeSp">
        <option value="${sizeSp.id}"
                <c:if test="${detail.idSize.id == sizeSp.id}">selected</c:if>
        >${sizeSp.tenSize}</option>
      </c:forEach>
    </select>
  </div>



  <div class="mb-3">
    <label class="form-label">gia ban </label>
    <input type="text" class="form-control" name="giaban"  value="${detail.giaBan}">
  </div>
  <div class="mb-3">
    <label class="form-label">so luong ton </label>
    <input type="text" class="form-control" name="soluongton" value="${detail.soLuong}" >
  </div>

  <div class="row">
    <p class="col-4">Trang thai
    </p>
    <div class="form-check col-4">
      <input class="form-check-input" type="radio" value="Active" name="trangthai"
             <c:if test="${detail.trangThai == 'Active'}">checked</c:if>
      >
      <label class="form-check-label">
        Active
      </label>
    </div>
    <div class="form-check col-4">
      <input class="form-check-input" type="radio" value="Inactive" name="trangthai"

             <c:if test="${detail.trangThai == 'Inactive'}">checked</c:if>>
      <label class="form-check-label">
        Inactive
      </label>
    </div>
  </div>
  <button type="submit" class="btn btn-success">Submit</button>
</form>
</body>
</html>
