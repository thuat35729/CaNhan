<%--
  Created by IntelliJ IDEA.
  User: Dungvt22
  Date: 27/03/2024
  Time: 5:30 CH
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
<form action="/san-pham/update?idsp=${spdetal.id}" method="post">


    <input type="hidden" class="form-control" name="id" value="${spdetal.id}">

    <div class="mb-3">
        <label class="form-label">Ma </label>
        <input type="text" class="form-control" name="maSanPham" value="${spdetal.maSP}">
    </div>
    <div class="mb-3">
        <label class="form-label">Ten san pham </label>
        <input type="text" class="form-control" name="tenSanPham" value="${spdetal.tenSP}">
    </div>
    <div class="mb-3">
        <label for="disabledSelect" class="form-label">Ten danh muc</label>
        <select id="disabledSelect" class="form-select" name="danhMuc">
            <c:forEach items="${listdm}" var="danhmuc">
                <option value="${danhmuc.id}"
                        <c:if test="${spdetal.danhMuc.id == danhmuc.id}">selected</c:if>
                >${danhmuc.tenDanhMuc}</option>
            </c:forEach>
        </select>
    </div>
    <div class="row">
        <p class="col-4">Trang thai
        </p>
        <div class="form-check col-4">
            <input class="form-check-input" type="radio" value="Active" name="trangThai"
                   <c:if test="${spdetal.trangThai == 'Active'}">checked</c:if>
            >
            <label class="form-check-label">
                Active
            </label>
        </div>
        <div class="form-check col-4">
            <input class="form-check-input" type="radio" value="Inactive" name="trangThai"
                   <c:if test="${spdetal.trangThai == 'Inactive'}">checked</c:if>
            >
            <label class="form-check-label">
                Inactive
            </label>
        </div>
    </div>
    <button type="submit" class="btn btn-success">Submit</button>
</form>
</body>
</html>
