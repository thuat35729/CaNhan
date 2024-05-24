package com.example.asm2.servlet;

import com.example.asm2.model.MauSac;
import com.example.asm2.repository.MauSacRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "MauSacServlet", value = {"/mausac/trangchu","/mausac/add","/mausac/delete","/mausac/detal","/mausac/update"})
public class MauSacServlet extends HttpServlet {
    MauSacRepository mauSacRepository = new MauSacRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/mausac/trangchu")) {
            ArrayList<MauSac> listms = mauSacRepository.getList();
            request.setAttribute("listms", listms);
            request.getRequestDispatcher("/view/MauSac.jsp").forward(request, response);
        } else if (uri.contains("/mausac/delete")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            MauSac mauSac = mauSacRepository.getDetal(id);
            mauSacRepository.delete(mauSac);
            response.sendRedirect("/mausac/trangchu");
        } else if (uri.equals("/mausac/detal")) {
            Integer id = Integer.parseInt(request.getParameter("id"));

            request.setAttribute("detal", mauSacRepository.getDetal(id));
            request.getRequestDispatcher("/detail/DetailMauSac.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/mausac/add")) {
            String ma = request.getParameter("mamau");
            String ten = request.getParameter("tenmau");
            String trangthai = request.getParameter("trangthai");
//         System.out.println(ten);

            MauSac ms = new MauSac();
            ms.setMaMau(ma);
            ms.setTenMau(ten);
            ms.setTrangThai(trangthai);
            ms.setNgayTao(new Date());
            ms.setNgaySua(new Date());
            mauSacRepository.add(ms);
            response.sendRedirect("/mausac/trangchu");
        } else if (uri.equals("/mausac/update")) {
            Integer id = Integer.parseInt(request.getParameter("id"));

            String ma = request.getParameter("mamau");
            String ten = request.getParameter("tenmau");
            String trangthai = request.getParameter("trangthai");
            MauSac mauSac = mauSacRepository.getDetal(id);
            mauSac.setMaMau(ma);
            mauSac.setTenMau(ten);
            mauSac.setTrangThai(trangthai);
            mauSacRepository.add(mauSac);


            response.sendRedirect("/mausac/trangchu");
        }
    }

}
