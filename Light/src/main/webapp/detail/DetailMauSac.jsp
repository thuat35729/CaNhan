<%--
  Created by IntelliJ IDEA.
  User: Dungvt22
  Date: 03/04/2024
  Time: 12:38 SA
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
<form action="/mausac/update?id=${detal.id}" method="post">



    <div class="mb-3">
        <label class="form-label">Ma </label>
        <input type="text" class="form-control" name="mamau" value="${detal.maMau}">
    </div>
    <div class="mb-3">
        <label class="form-label">Ten </label>
        <input type="text" class="form-control" name="tenmau"  value="${detal.tenMau}">
    </div>
    <div class="row">
        <p class="col-4">Trang thai
        </p>
        <div class="form-check col-4">
            <input class="form-check-input" type="radio" value="Active" name="trangthai"
                   <c:if test="${detal.trangThai == 'Active'}">checked</c:if>
            >
            <label class="form-check-label">
                Active
            </label>
        </div>
        <div class="form-check col-4">
            <input class="form-check-input" type="radio" value="Inactive" name="trangthai"
                   <c:if test="${detal.trangThai == 'Inactive'}">checked</c:if>

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
