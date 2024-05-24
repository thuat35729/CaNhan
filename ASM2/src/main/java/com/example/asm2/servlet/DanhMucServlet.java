package com.example.asm2.servlet;

import com.example.asm2.model.DanhMuc;
import com.example.asm2.repository.DanhMucRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "DanhMucServlet", value = {"/danh-Muc/hien-thi", "/DanhMuc/Add", "/danh-muc/delete", "/danh-muc/detail", "/DanhMuc/update"})
public class DanhMucServlet extends HttpServlet {
    ArrayList<DanhMuc> listdm = new ArrayList<>();

    DanhMucRepository danhMucRepository = new DanhMucRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/danh-Muc/hien-thi")) {
            listdm = danhMucRepository.getList();
            request.setAttribute("listdm", listdm);
            request.getRequestDispatcher("/view/DanhMuc.jsp").forward(request, response);
        } else if (uri.equals("/danh-muc/delete")) {
            //xoa
            Integer id = Integer.parseInt(request.getParameter("id"));

            DanhMuc danhMuc = danhMucRepository.getDetail(id);
            danhMucRepository.delete(danhMuc);
            response.sendRedirect("/danh-Muc/hien-thi");
        } else if (uri.equals("/danh-muc/detail")) {
            Integer ma = Integer.parseInt(request.getParameter("id"));
            DanhMuc danhMuc = danhMucRepository.getDetail(ma);
            request.setAttribute("list", danhMuc);
            request.getRequestDispatcher("/detail/DetailDanhMuc.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/DanhMuc/Add")) {
            this.add(request, response);
        } else if (uri.equals("/DanhMuc/update")) {
            this.update(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String ma = request.getParameter("maDM");
        String ten = request.getParameter("tenDM");
        String trangthai = request.getParameter("trangthai");
        DanhMuc danhMuc = danhMucRepository.getDetail(id);
        if (danhMuc != null) {
            danhMuc.setId(id);
            danhMuc.setMaDanhMuc(ma);
            danhMuc.setTenDanhMuc(ten);
            danhMuc.setTrangThai(trangthai);
            danhMuc.setNgaySua(new Date());

            danhMucRepository.add(danhMuc);
        }
        response.sendRedirect("/danh-Muc/hien-thi");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ma = request.getParameter("maDM");
        String ten = request.getParameter("tenDM");
        String trangthai = request.getParameter("trangthai");
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setMaDanhMuc(ma);
        danhMuc.setTenDanhMuc(ten);
        danhMuc.setTrangThai(trangthai);
        danhMuc.setNgayTao(new Date());
        danhMuc.setNgaySua(new Date());
        //goi sang repo de luu
        danhMucRepository.add(danhMuc);
        response.sendRedirect("/danh-Muc/hien-thi");
    }
}
