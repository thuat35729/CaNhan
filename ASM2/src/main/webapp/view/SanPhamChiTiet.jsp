<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 3/26/2024
  Time: 12:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Chi Tiet San Pham</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>

</head>
<body>
<form action="/ctsp/add" method="post">
    <div class="mb-3">
        <label for="disabledSelect" class="form-label">Ten san pham</label>
        <select id="disabledSelect" class="form-select" name="sanpham">
            <c:forEach items="${sanPham}" var="sanPham">
                <option value="${sanPham.id}">${sanPham.tenSP}</option>
            </c:forEach>
        </select>
    </div>
    <div class="mb-3">
        <label for="disabledSelectt" class="form-label">Ten mau sac</label>
        <select id="disabledSelectt" class="form-select" name="mausac">
            <c:forEach items="${mauSac}" var="mauSac">
                <option value="${mauSac.id}">${mauSac.tenMau}</option>
            </c:forEach>
        </select>
    </div>
    <div class="mb-3">
        <label for="disabledSelecttt" class="form-label">Ten size</label>
        <select id="disabledSelecttt" class="form-select" name="sizesp">
            <c:forEach items="${Size}" var="sizeSp">
                <option value="${sizeSp.id}">${sizeSp.tenSize}</option>
            </c:forEach>
        </select>
    </div>


    <div class="mb-3">
        <label class="form-label">gia ban </label>
        <input type="text" class="form-control" name="giaban">
    </div>
    <p>${error}</p>
    <div class="mb-3">
        <label class="form-label">so luong ton </label>
        <input type="text" class="form-control" name="soluongton">
    </div>
    <p>${error}</p>
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
    <td>STT</td>
    <td>Ten San Pham</td>
    <td>Ten Mau</td>
    <td>Ten Size</td>
    <td>Gia Ban</td>
    <td>So Luong</td>
    <td>Trang Thai</td>
    <td>Ngay Tao</td>
    <td>Ngay Sua</td>
    </thead>
    <tbody>
    <c:forEach var="a" items="${listspct}">
        <tr>
            <td>
                    ${a.id}
            </td>
            <td>
                    ${a.idSanPham.tenSP}
            </td>
            <td>
                    ${a.idMauSac.tenMau}
            </td>
            <td>
                    ${a.idSize.tenSize}
            </td>
            <td>
                    ${a.giaBan}
            </td>
            <td>
                    ${a.soLuong}
            </td>
            <td>
                    ${a.trangThai}
            </td>
            <td>${a.ngayTao}</td>
            <td>${a.ngaySua}</td>
            <td><a href="/ctsp/detail?ctspid=${a.id}">
                <button class="btn btn-warning">Detail</button>
            </a></td>
            <td><a href="/ctsp/delete?id=${a.id}">
                <button class="btn btn-warning">Detail</button>
            </a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
