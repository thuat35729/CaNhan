package com.example.asm2.servlet;

import com.example.asm2.model.KhachHang;
import com.example.asm2.repository.KhachHangRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "KhachHangServlet", value = {"/khachhang/trangchu", "/khachhang/delete", "/khachhang/add", "/khachhang/detal", "/khachhang/update"})
public class KhachHangServlet extends HttpServlet {
    KhachHangRepository khachHangRep = new KhachHangRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/khachhang/trangchu")) {
            ArrayList<KhachHang> listkh = khachHangRep.getList();
            request.setAttribute("listkh", listkh);
            request.getRequestDispatcher("/view/KhachHang.jsp").forward(request, response);
        } else if (uri.contains("/khachhang/delete")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            KhachHang khachHang = khachHangRep.detailkh(id);
            khachHangRep.delete(khachHang);
            response.sendRedirect("/khachhang/trangchu");
        } else if (uri.equals("/khachhang/detal")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("detal", khachHangRep.detailkh(id));
            request.getRequestDispatcher("/detail/DetailKhachHang.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/khachhang/add")) {
            String ten = request.getParameter("hoten");
            String diachi = request.getParameter("diachi");
            String sdt = request.getParameter("sdt");
            String trangthai = request.getParameter("trangthai");
            KhachHang kh = new KhachHang();
            kh.setHoTen(ten);
            kh.setDiaChi(diachi);
            kh.setSoDienThoai(sdt);
            kh.setTrangThai(trangthai);
            kh.setNgayTao(new Date());
            kh.setNgaySua(new Date());
            khachHangRep.add(kh);
            response.sendRedirect("/khachhang/trangchu");
        } else if (uri.equals("/khachhang/update")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String ten = request.getParameter("hoten");
            String diachi = request.getParameter("diachi");
            String sdt = request.getParameter("sdt");
            String trangthai = request.getParameter("trangthai");
            KhachHang khachHang = khachHangRep.detailkh(id);
            khachHang.setHoTen(ten);
            khachHang.setDiaChi(diachi);
            khachHang.setSoDienThoai(sdt);
            khachHang.setTrangThai(trangthai);
            khachHang.setNgaySua(new Date());
            khachHangRep.add(khachHang);
            response.sendRedirect("/khachhang/trangchu");
        }

    }
}
