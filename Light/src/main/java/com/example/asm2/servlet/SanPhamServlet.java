package com.example.asm2.servlet;

import com.example.asm2.model.DanhMuc;
import com.example.asm2.model.SanPham;
import com.example.asm2.repository.DanhMucRepository;
import com.example.asm2.repository.SanPhamRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "SanPhamServlet", value = {"/sanpham-hienthi", "/san-pham/add", "/san-pham/update",
        "/san-pham/detail", "/san-pham/delete"})
public class SanPhamServlet extends HttpServlet {
    ArrayList<SanPham> listsp = new ArrayList<>();

    ArrayList<DanhMuc> listt = new ArrayList<>();
    SanPhamRepository sanPhamRepository = new SanPhamRepository();
    DanhMucRepository danhMucRepository = new DanhMucRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/sanpham-hienthi")) {
            listt = danhMucRepository.getList();
            listsp = sanPhamRepository.getList();
            request.setAttribute("litsp", listsp);
            request.setAttribute("listdm", listt);
            request.getRequestDispatcher("/view/SanPham.jsp").forward(request, response);
        } else if (uri.equals("/san-pham/detail")) {
            Integer id = Integer.parseInt(request.getParameter("idsp"));
            SanPham listsp = sanPhamRepository.getDetal(id);
            listt = danhMucRepository.getList();
            request.setAttribute("spdetal", listsp);
            request.setAttribute("listdm", listt);
            request.getRequestDispatcher("/detail/DetailSanPham.jsp").forward(request, response);
        } else if (uri.equals("/san-pham/delete")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            SanPham sanPham = sanPhamRepository.getDetal(id);
            sanPhamRepository.delete(sanPham);
            response.sendRedirect("/sanpham-hienthi");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/san-pham/add")) {
            String ma = request.getParameter("maSanPham");
            String ten = request.getParameter("tenSanPham");
            String trangThai = request.getParameter("trangThai");
            Integer idDanhMuc = Integer.valueOf(request.getParameter("danhMuc"));
            SanPham sanPham = new SanPham();
            sanPham.setMaSP(ma);
            sanPham.setTenSP(ten);
            sanPham.setTrangThai(trangThai);
            sanPham.setNgaySua(new Date());
            sanPham.setNgayTao(new Date());
            DanhMuc danhMuc = new DanhMuc();
            danhMuc.setId(idDanhMuc);
            sanPham.setDanhMuc(danhMuc);
            sanPhamRepository.add(sanPham);
            response.sendRedirect("/sanpham-hienthi");
        }
        else if (uri.equals("/san-pham/update")) {
            Integer id = Integer.parseInt(request.getParameter("idsp"));
            String ma = request.getParameter("maSanPham");
            String ten = request.getParameter("tenSanPham");
            String trangThai = request.getParameter("trangThai");
            Integer idDanhMuc = Integer.valueOf(request.getParameter("danhMuc"));
            SanPham sanPham = sanPhamRepository.getDetal(id);
            sanPham.setMaSP(ma);
            sanPham.setTenSP(ten);
            sanPham.setTrangThai(trangThai);
            sanPham.setNgaySua(new Date());
            sanPham.setNgayTao(new Date());
            DanhMuc danhMuc = new DanhMuc();
            danhMuc.setId(idDanhMuc);
            sanPham.setDanhMuc(danhMuc);
            sanPhamRepository.add(sanPham);
            response.sendRedirect("/sanpham-hienthi");
        }
    }
}
