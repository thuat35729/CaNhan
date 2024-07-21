package com.example.asm2.servlet;

import com.example.asm2.model.HoaDon;
import com.example.asm2.model.KhachHang;
import com.example.asm2.repository.HoaDonRepository;
import com.example.asm2.repository.KhachHangRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "HoaDonServlet", value = {"/hoadon/trangchu","/hoadon/add","/hoadon/delete","/hoadon/detal","/hoadon/update"})
public class HoaDonServlet extends HttpServlet {
    ArrayList<KhachHang> khachHang = new ArrayList<>();
    KhachHangRepository khachHangRep = new KhachHangRepository();
    HoaDonRepository hoaDonRep = new HoaDonRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/hoadon/trangchu")) {
            ArrayList<HoaDon> listhd = hoaDonRep.getList();
            khachHang = khachHangRep.getList();
            request.setAttribute("khachHang", khachHang);
            request.setAttribute("listhd", listhd);
            request.getRequestDispatcher("/view/HoaDon.jsp").forward(request, response);
        } else if (uri.contains("/hoadon/delete")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            HoaDon hoaDon = hoaDonRep.detailhd(id);
            hoaDonRep.delete(hoaDon);
            response.sendRedirect("/hoadon/trangchu");
        } else if (uri.equals("/hoadon/detal")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            ArrayList<HoaDon> listhd = hoaDonRep.getList();
            khachHang = khachHangRep.getList();
            request.setAttribute("khachHang", khachHang);
            request.setAttribute("detal", hoaDonRep.detailhd(id));
            request.getRequestDispatcher("/detail/DetailHoaDon.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/hoadon/add")) {
            Integer khachhangStr = Integer.parseInt(request.getParameter("khachHang"));
            String diachi = request.getParameter("diachi");
            String sdt = request.getParameter("sdt");
            String trangthai = request.getParameter("trangthai");
            if (khachhangStr == null || khachhangStr == 0 ||
                    diachi == null || diachi.isEmpty() ||
                    sdt == null || sdt.isEmpty() ||
                    trangthai == null || trangthai.isEmpty()) {

                // Thêm thông báo lỗi vào request
                request.setAttribute("error", "Vui lòng điền đầy đủ thông tin.");
                request.getRequestDispatcher("/path/to/error/page").forward(request, response);
                return;
            }
            Integer khachhang = Integer.parseInt(String.valueOf(khachhangStr));
            HoaDon hd = new HoaDon();
            KhachHang khachHang1 = new KhachHang();
            khachHang1.setId(khachhang);
            hd.setNgayTao(new Date());
            hd.setIdKhachHang(khachHang1);
            hd.setDiaChi(diachi);
            hd.setSoDienThoai(sdt);
            hd.setTrangThai(trangthai);
            hoaDonRep.add(hd);
            response.sendRedirect("/hoadon/trangchu");
        } else if (uri.equals("/hoadon/update")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            khachHang = khachHangRep.getList();
            String khachhang = request.getParameter("khachHang");
            String diachi = request.getParameter("diachi");
            String sdt = request.getParameter("sdt");
            String trangthai = request.getParameter("trangthai");
            HoaDon hoaDon = hoaDonRep.detailhd(id);
            KhachHang khachHang1 = new KhachHang();
            khachHang1.setId(Integer.parseInt(khachhang));
            hoaDon.setNgayTao(new Date());
            hoaDon.setIdKhachHang(khachHang1);
            hoaDon.setDiaChi(diachi);
            hoaDon.setSoDienThoai(sdt);
            hoaDon.setTrangThai(trangthai);
            hoaDonRep.add(hoaDon);
            response.sendRedirect("/hoadon/trangchu");
        }

    }
}
