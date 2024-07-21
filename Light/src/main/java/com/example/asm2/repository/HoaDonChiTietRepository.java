package com.example.asm2.repository;

import com.example.asm2.connect.HibernateUtils;
import com.example.asm2.model.ChiTietSanPham;
import com.example.asm2.model.HoaDon;
import com.example.asm2.model.HoaDonChiTiet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietRepository {


    public ArrayList<HoaDonChiTiet> getList(Integer idHoaDon) {
        Session session = HibernateUtils.getFACTORY().openSession();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setId(idHoaDon);
        ArrayList<HoaDonChiTiet> list = (ArrayList<HoaDonChiTiet>) session.createQuery
                ("from HoaDonChiTiet where idHoaDon =: idHoaDon_1 and trangThai = 'Active'").setParameter("idHoaDon_1", hoaDon).list();
        session.close();
        return list;
    }
    public List<HoaDonChiTiet> getListHD(int pageNumber, int pageSize, Integer idHoaDon) {
        Session session = HibernateUtils.getFACTORY().openSession();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setId(idHoaDon);
        Query query = session.createQuery("from HoaDonChiTiet where idHoaDon =: id_hd").setParameter("id_hd", hoaDon);
        List<HoaDonChiTiet> list = query.list();
        session.close();
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, list.size());
        List<HoaDonChiTiet> subList = list.subList(startIndex, endIndex);
        ArrayList<HoaDonChiTiet> pageList = new ArrayList<>(subList);
        return pageList;
    }

    public ArrayList<HoaDonChiTiet> getAll() {
        Session session = HibernateUtils.getFACTORY().openSession();
        ArrayList<HoaDonChiTiet> list = (ArrayList<HoaDonChiTiet>) session.createQuery
                ("from HoaDonChiTiet").list();

        session.close();
        return list;
    }

    public HoaDonChiTiet detailhdspct(Integer id) {
        Session session = HibernateUtils.getFACTORY().openSession();

        HoaDonChiTiet hdct = (HoaDonChiTiet) session.createQuery
                        ("from HoaDonChiTiet where id = :id_1 ")
                .setParameter("id_1", id)
                .getSingleResult();
        session.close();
        return hdct;
    }

    public void add(HoaDonChiTiet hoaDonChiTiet) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(hoaDonChiTiet);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void update(HoaDonChiTiet hoaDonChiTiet) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(hoaDonChiTiet);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public HoaDonChiTiet findBySanPhamId(Integer idctsp) {
        Session session = HibernateUtils.getFACTORY().openSession();
        ArrayList<HoaDonChiTiet> results = (ArrayList<HoaDonChiTiet>) session.createQuery("from HoaDonChiTiet where idChiTietSanPham.id = :idSpct")
                .setParameter("idSpct", idctsp)
                .getResultList();
        session.close();
        if (!results.isEmpty()) {
            return results.get(0); // Trả về phần tử đầu tiên nếu danh sách kết quả không rỗng
        } else {
            return null; // Trả về null nếu không tìm thấy kết quả
        }
    }



//    public HoaDonChiTiet detailhdct(Integer idHoaDon) {
//        Session session = HibernateUtils.getFACTORY().openSession();
//        HoaDon hoaDon = new HoaDon();
//        hoaDon.setId(idHoaDon);
//        HoaDonChiTiet hdct = (HoaDonChiTiet) session.createQuery("from HoaDonChiTiet where id=: id_1").setParameter("id_1", idHoaDon).getSingleResult();
//        session.close();
//        return hdct;
//    }

}
