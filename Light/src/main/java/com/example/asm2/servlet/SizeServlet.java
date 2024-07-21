package com.example.asm2.servlet;

import com.example.asm2.model.Size;
import com.example.asm2.repository.SizeRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "SizeServlet", value = {"/size/trangchu", "/size/add", "/size/delete", "/size/detal", "/size/update"})
public class SizeServlet extends HttpServlet {
    SizeRepository sizeRepository = new SizeRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/size/trangchu")) {
            ArrayList<Size> listsz = sizeRepository.getlist();
            request.setAttribute("listsz", listsz);
            request.getRequestDispatcher("/view/Size.jsp").forward(request, response);
        } else if (uri.contains("/size/delete")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Size sizeSp = sizeRepository.getDetal(id);
            sizeRepository.delete(sizeSp);
            response.sendRedirect("/size/trangchu");
        } else if (uri.equals("/size/detal")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("detal", sizeRepository.getDetal(id));
            request.getRequestDispatcher("/detail/DetailSize.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/size/add")) {
            String ma = request.getParameter("masize");
            String ten = request.getParameter("tensize");
            String trangthai = request.getParameter("trangthai");
            Size s = new Size();
            s.setMaSize(ma);
            s.setTenSize(ten);
            s.setTrangThai(trangthai);
            s.setNgayTao(new Date());
            s.setNgaySua(new Date());
            sizeRepository.add(s);
            response.sendRedirect("/size/trangchu");
        } else if (uri.equals("/size/update")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String ma = request.getParameter("masize");
            String ten = request.getParameter("tensize");
            String trangthai = request.getParameter("trangthai");
            Size size = sizeRepository.getDetal(id);
            size.setMaSize(ma);
            size.setTenSize(ten);
            size.setTrangThai(trangthai);
            size.setNgaySua(new Date());
            sizeRepository.add(size);
            response.sendRedirect("/size/trangchu");
        }
    }
}
