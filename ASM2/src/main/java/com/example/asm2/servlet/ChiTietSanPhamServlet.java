package com.example.asm2.servlet;

import com.example.asm2.model.MauSac;
import com.example.asm2.model.SanPham;
import com.example.asm2.model.Size;
import com.example.asm2.repository.MauSacRepository;
import com.example.asm2.repository.SanPhamRepository;
import com.example.asm2.repository.SizeRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.example.asm2.model.ChiTietSanPham;
import com.example.asm2.repository.ChiTietSanPhamRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "ChiTietSanPhamServlet", value = {"/chitiet-sanpham/hien-thi", "/ctsp/add", "/ctsp/detail",
        "/ctsp/delete","/ctsp/update"})
public class ChiTietSanPhamServlet extends HttpServlet {
    ArrayList<ChiTietSanPham> listctsp = new ArrayList<>();
    ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();
    ArrayList<SanPham> listsp = new ArrayList<>();
    ArrayList<Size> listsize = new ArrayList<>();
    ArrayList<MauSac> listmau = new ArrayList<>();
    SanPhamRepository sanPhamRepository = new SanPhamRepository();
    SizeRepository sizeRepository = new SizeRepository();
    MauSacRepository mauSacRepository = new MauSacRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/chitiet-sanpham/hien-thi")) {
            listctsp = chiTietSanPhamRepository.getList();
            listmau = mauSacRepository.getList();
            listsize = sizeRepository.getlist();
            listsp = sanPhamRepository.getList();
            request.setAttribute("Size", listsize);
            request.setAttribute("mauSac", listmau);
            request.setAttribute("sanPham", listsp);
            request.setAttribute("listspct", listctsp);
            request.getRequestDispatcher("/view/SanPhamChiTiet.jsp").forward(request, response);
        } else if (uri.equals("/ctsp/detail")) {
            Integer id = Integer.parseInt(request.getParameter("ctspid"));
            ChiTietSanPham list =  chiTietSanPhamRepository.getDetal(id);
            request.setAttribute("detail", list);
            listmau = mauSacRepository.getList();
            listsize = sizeRepository.getlist();
            listsp = sanPhamRepository.getList();
            request.setAttribute("Size", listsize);
            request.setAttribute("mauSac", listmau);
            request.setAttribute("sanPham", listsp);
            request.getRequestDispatcher("/detail/DetailChiTietSanPham.jsp").forward(request, response);

        }
        else if (uri.equals("/ctsp/delete")) {
            Integer id = Integer.parseInt("id");
            ChiTietSanPham list = chiTietSanPhamRepository.detailhd(id);
            chiTietSanPhamRepository.delete(list);
            response.sendRedirect("/chitiet-sanpham/hien-thi");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/ctsp/add")) {
            String sanpham = request.getParameter("sanpham");
            String mausac = request.getParameter("mausac");
            String sizesp = request.getParameter("sizesp");
            Double giaban = Double.parseDouble(request.getParameter("giaban"));
            Integer soluongton = Integer.parseInt(request.getParameter("soluongton"));
            String trangthai = request.getParameter("trangthai");

            ChiTietSanPham chiTietSanPham = new ChiTietSanPham();

            SanPham sanPham1 = new SanPham();
            sanPham1.setId(Integer.parseInt(sanpham));
            chiTietSanPham.setIdSanPham(sanPham1);

            MauSac mauSac1 = new MauSac();
            mauSac1.setId(Integer.parseInt(mausac));
            chiTietSanPham.setIdMauSac(mauSac1);

            Size sizeSp1 = new Size();
            sizeSp1.setId(Integer.parseInt(sizesp));
            chiTietSanPham.setIdSize(sizeSp1);

            chiTietSanPham.setGiaBan(giaban);
            chiTietSanPham.setSoLuong(soluongton);
            chiTietSanPham.setTrangThai(trangthai);
            chiTietSanPham.setNgayTao(new Date());
            chiTietSanPham.setNgaySua(new Date());

            chiTietSanPhamRepository.add(chiTietSanPham);
            response.sendRedirect("/chitiet-sanpham/hien-thi");
        } else if (uri.equals("/ctsp/update")) {
            Integer id = Integer.parseInt("id");
            String sanpham = request.getParameter("sanpham");
            String mausac = request.getParameter("mausac");
            String sizesp = request.getParameter("sizesp");
            Double giaban = Double.parseDouble(request.getParameter("giaban"));
            Integer soluongton = Integer.parseInt(request.getParameter("soluongton"));
            String trangthai = request.getParameter("trangthai");

            ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.detailhd(id);
            SanPham sanPham1 = new SanPham();
            sanPham1.setId(Integer.parseInt(sanpham));
            chiTietSanPham.setIdSanPham(sanPham1);

            MauSac mauSac1 = new MauSac();
            mauSac1.setId(Integer.parseInt(mausac));
            chiTietSanPham.setIdMauSac(mauSac1);

            Size sizeSp1 = new Size();
            sizeSp1.setId(Integer.parseInt(sizesp));
            chiTietSanPham.setIdSize(sizeSp1);

            chiTietSanPham.setGiaBan(giaban);
            chiTietSanPham.setSoLuong(soluongton);
            chiTietSanPham.setTrangThai(trangthai);
            chiTietSanPham.setNgayTao(new Date());
            chiTietSanPham.setNgaySua(new Date());

            chiTietSanPhamRepository.add(chiTietSanPham);
            response.sendRedirect("/chitiet-sanpham/hien-thi");
        }
    }
}
