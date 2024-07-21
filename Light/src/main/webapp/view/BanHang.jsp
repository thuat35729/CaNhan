<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nguyenvv
  Date: 12/03/2024
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Trang Chu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>
<div class="row">
    <div class="col-7">
        <h2>Danh sách hoá đơn</h2>
        <table class="table">
            <thead>
            <tr>
                <td>STT</td>
                <td>ID</td>
                <td>Ten khach hang</td>
                <td>Ngay tao</td>
                <td>Trang Thai</td>
                <td>Chuc nang</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listhd}" var="hd" varStatus="i">
                <tr>
                    <td>${i.index +1}</td>
                    <td>${hd.id}</td>
                    <td>${hd.idKhachHang.hoTen}</td>
                    <td>${hd.ngayTao}</td>
                    <td>${hd.trangThai}</td>
                    <td><a href="/hoa-don/detail?idHoaDon=${hd.id}">
                        <button class="btn btn-primary">Chọn</button>
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <h2>Danh sách hoá đơn chi tiết</h2>
        <table class="table">
            <thead>
            <tr>
                <td>STT</td>
                <td>ID</td>
                <td>Ten san pham</td>
                <td>So luong</td>
                <td>Gia ban</td>
                <td>Tong tien</td>
                <td>Chuc nang</td>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${listhdct}" var="hdct" varStatus="i">
                <tr>
                    <td>${i.index + 1}</td>
                    <td>${hdct.id}</td>
                    <td>${hdct.idChiTietSanPham.idSanPham.tenSP}</td>
                    <td>${hdct.soLuongMua}</td>
                    <td>${hdct.giaBan}</td>
                    <td>${hdct.tongTien}</td>
                    <td>
                        <a href="/hoa-don/delete?id=${hdct.id}">
                            <button class="btn btn-danger">Xóa</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
        <nav aria-label="Page navigation example" name="page">
            <ul class="pagination">
                <li class="page-item"><c:if test="${currentPage <= totalPages}"><a class="page-link"
                                                                                   href="?page=${currentPage - 1}">Previous</a></c:if>
                </li>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="page-item"><a class="page-link" href="?page=${i}">${i}</a></li>
                </c:forEach>
                <li class="page-item"><c:if test="${currentPage <= totalPages}"><a class="page-link"
                                                                                   href="?page=${currentPage + 1}">Next</a></c:if>
                </li>
            </ul>
        </nav>
    </div>
    <div class="col-5">
        <h2>Tạo hoá đơn</h2>
        <div class="row">
            <div>
                <form action="/hoa-don/search">
                    <div>
                        <label class="mb-3 col-3">Số điện thoại</label>
                        <input type="text" class="col-7" name="sdt">
                    </div>
                    <button class="btn btn-primary" type="submit">Search</button>
                </form>
            </div>
            <form method="post" action="/hoa-don/new">
                <div class="mb-3">
                    <label class="col-3">Tên Khách hàng</label>
                    <select class="form-select col-7" aria-label="Default select example" name="idKhachHang">
                        <c:forEach var="kh" items="${listkh}">
                            <option value="${kh.id}" ${kh.id == detailhd.idKhachHang.id ? 'selected' : ''}>${kh.hoTen}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="col-3">ID Hoa don</label>
                    <input type="text" class="col-7" readonly value="${detailhd.id}">
                </div>
                <div class="mb-3">
                    <label class="col-3">Tong tien</label>
                    <input type="text" class="col-7" readonly name="tongTien" value="${tongTien}">
                </div>
                <div>
                    <button class="btn btn-primary" type="submit"
                            onclick="return confirm('Bạn có chắc muốn tạo hóa đơn không?');">Tạo hoá đơn
                    </button>
                </div>
            </form>
            <div>
                <a href="/hoa-don/thanh-toan?idHoaDon=${detailhd.id}"
                   onclick="return confirm('Bạn có chắc muốn thanh toán không?');">
                    <button class="btn btn-primary" formmethod="get">Thanh toán</button>
                </a>
            </div>
        </div>

    </div>
</div>
<div>
    <h2>Danh sách chi tiết sản phẩm</h2>
    <table class="table">
        <thead>
        <tr>
            <td>STT</td>
            <td>ID CTSP</td>
            <td>Ten san pham</td>
            <td>Mau sac</td>
            <td>Size</td>
            <td>Gia ban</td>
            <td>So luong ton</td>
            <td>Trang Thai</td>
            <td>Chuc nang</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listctsp}" var="spct" varStatus="i">
            <tr>
                <td>${i.index + 1}</td>
                <td>${spct.id}</td>
                <td>${spct.idSanPham.tenSP}</td>
                <td>${spct.idMauSac.tenMau}</td>
                <td>${spct.idSize.tenSize}</td>
                <td>${spct.giaBan}</td>
                <td>${spct.soLuong}</td>
                <td>${spct.trangThai}</td>
                <td>
                    <a href="/hoa-don/add?idSpct=${spct.id}" onclick="return confirm('Bạn có chắc muốn thêm không?');">
                        <button class="btn btn-primary">Chọn mua</button>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
