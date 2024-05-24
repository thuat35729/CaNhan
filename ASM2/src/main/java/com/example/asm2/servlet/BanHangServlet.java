package com.example.asm2.servlet;

import com.example.asm2.model.KhachHang;
import com.example.asm2.repository.KhachHangRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.example.asm2.model.ChiTietSanPham;
import com.example.asm2.model.HoaDon;
import com.example.asm2.model.HoaDonChiTiet;
import com.example.asm2.repository.ChiTietSanPhamRepository;
import com.example.asm2.repository.HoaDonChiTietRepository;
import com.example.asm2.repository.HoaDonRepository;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "BanHangServlet", value = {"/ban-hang", "/hoa-don/detail", "/hoa-don/add",
        "/hoa-don/new", "/hoa-don/thanh-toan", "/hoa-don/search", "/hoa-don/delete"})
public class BanHangServlet extends HttpServlet {
    ArrayList<ChiTietSanPham> listctsp;
    ArrayList<HoaDonChiTiet> listcthd2;
    ArrayList<HoaDonChiTiet> listhdct;
    ArrayList<KhachHang> khachHangs;
    ArrayList<HoaDon> listhd;
    HoaDonRepository hoaDonRepository = new HoaDonRepository();
    ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();
    HoaDonChiTietRepository hoaDonChiTietRepository = new HoaDonChiTietRepository();
    KhachHangRepository khachHangRepository = new KhachHangRepository();
    HoaDon hoaDonDetail;
    double tongTien;
    Integer idHoaDon;

    public BanHangServlet() {
        listcthd2 = new ArrayList<>();
        listctsp = new ArrayList<>();
        listhd = new ArrayList<>();
        listhdct = new ArrayList<>();
        khachHangs = new ArrayList<>();
        hoaDonDetail = new HoaDon();
        tongTien = 0;
        idHoaDon = 0;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/ban-hang")) {
            String pageParam = request.getParameter("page");
            int page = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
            int pageSize = 2;
            ArrayList<HoaDonChiTiet> list = (ArrayList<HoaDonChiTiet>) hoaDonChiTietRepository.getListHD(page, pageSize, idHoaDon);
//            int totalRecords = hoaDonRepository.getList().size();
            int totalRecords = hoaDonChiTietRepository.getList(idHoaDon).size();
            int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
            khachHangs = khachHangRepository.getList();
            listcthd2 = hoaDonChiTietRepository.getAll();
            listhd = hoaDonRepository.getList();
            listctsp = chiTietSanPhamRepository.getList();
            request.setAttribute("listkh", khachHangs);
            request.setAttribute("listhdct", list);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("listhd", listhd);
            request.setAttribute("listctsp", listctsp);
            request.setAttribute("detailhd", hoaDonDetail);
            request.getRequestDispatcher("/view/BanHang.jsp").forward(request, response);
            response.sendRedirect("/ban-hang");
        } else if (uri.equals("/hoa-don/detail")) {
            Integer id = Integer.parseInt(request.getParameter("idHoaDon"));
            hoaDonDetail = hoaDonRepository.detailhd(id);
            idHoaDon = id;
            listhdct = hoaDonChiTietRepository.getList(id);
            tongTien = 0;
            for (HoaDonChiTiet hdct : listhdct
            ) {
                tongTien += hdct.getTongTien();

            }
            response.sendRedirect("/ban-hang");
        } else if (uri.equals("/hoa-don/add")) {
            Integer idctsp = Integer.parseInt(request.getParameter("idSpct"));
            ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.detailhd(idctsp);
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            HoaDon hoaDon = new HoaDon();
            hoaDon.setId(idHoaDon);
            hoaDonChiTiet.setIdHoaDon(hoaDon);
            hoaDonChiTiet.setIdChiTietSanPham(chiTietSanPham);
            hoaDonChiTiet.setGiaBan(chiTietSanPham.getGiaBan());
            HoaDonChiTiet hoaDonChiTietTonTai = hoaDonChiTietRepository.findBySanPhamId(idctsp);//(BanHangServlet.java:88)
            boolean productExistsInCurrentOrder = false;
            for (HoaDonChiTiet hdct : listhdct) {
                if (hdct.getIdHoaDon().getId().equals(idHoaDon) && hdct.getIdChiTietSanPham().getId().equals(idctsp)) {
                    productExistsInCurrentOrder = true;
                    hoaDonChiTietTonTai = hdct;
                    break;
                }
            }
            if (productExistsInCurrentOrder) {
                int soLuongMoi = hoaDonChiTietTonTai.getSoLuongMua() + 1;
                hoaDonChiTietTonTai.setSoLuongMua(soLuongMoi);
                hoaDonChiTietTonTai.setTongTien(hoaDonChiTietTonTai.getGiaBan() * soLuongMoi);
                chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - 1);
                hoaDonChiTietTonTai.setNgaySua(new Date());
                hoaDonChiTietRepository.update(hoaDonChiTietTonTai);
            } else {
                hoaDonChiTiet.setSoLuongMua(1);
                hoaDonChiTiet.setTongTien(hoaDonChiTiet.getGiaBan() * 1);
                hoaDonChiTiet.setNgayTao(new Date());
                hoaDonChiTiet.setNgaySua(new Date());
                hoaDonChiTiet.setTrangThai("Active");
                chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - 1);
                hoaDonChiTietRepository.add(hoaDonChiTiet);
            }
            chiTietSanPhamRepository.update(chiTietSanPham);
            response.sendRedirect("/ban-hang");
        } else if (uri.equals("/hoa-don/thanh-toan")) {
            if (tongTien > 0) {
                Integer idHoaDon = Integer.parseInt(request.getParameter("idHoaDon")); // Sử dụng idHoaDon thay vì id
                HoaDon hd = hoaDonRepository.detailhd(idHoaDon);
                hd.setNgaySua(new Date());
                hd.setTrangThai("da thanh toan");
                hoaDonRepository.add(hd);
                response.sendRedirect("/ban-hang");
            } else {
                response.sendRedirect("/ban-hang");
            }
        } else if (uri.equals("/hoa-don/search")) {
            String sdt = request.getParameter("sdt");
            listhd = hoaDonRepository.Search(sdt);
            request.setAttribute("listkh", khachHangs);
            request.setAttribute("listhdct", listhdct);
            request.setAttribute("listhd", listhd);
            request.setAttribute("listctsp", listctsp);
            request.setAttribute("detailhd", hoaDonDetail);
            double tongTienHoaDon = 0;
            for (HoaDon hd : listhd) {
                for (HoaDonChiTiet hdct : listcthd2) {
                    if (hdct.getIdHoaDon().getId().equals(hd.getId())) {
                        tongTienHoaDon += hdct.getTongTien();
                    }
                }
            }
            request.setAttribute("tongTienHoaDon", tongTienHoaDon);
            request.setAttribute("listhd", listhd);
            request.getRequestDispatcher("/view/BanHang.jsp").forward(request, response);
        } else if (uri.equals("/hoa-don/delete")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.detailhdspct(id);
            hoaDonChiTiet.setTrangThai("Inactive");
            hoaDonChiTietRepository.update(hoaDonChiTiet);
            response.sendRedirect("/ban-hang");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/hoa-don/new")) {
            Integer idkh = Integer.parseInt(request.getParameter("idKhachHang"));
            HoaDon hd = new HoaDon();
            hd.setNgayTao(new Date());
            hd.setNgaySua(new Date());
            hd.setTrangThai("chua thanh toan");
            KhachHang kh = khachHangRepository.detailkh(idkh);
            kh.setId(kh.getId());
            hd.setIdKhachHang(kh);
            hd.setDiaChi(kh.getDiaChi());
            hd.setSoDienThoai(kh.getSoDienThoai());
            hoaDonRepository.add(hd);
            response.sendRedirect("/ban-hang");
        }
    }
}
