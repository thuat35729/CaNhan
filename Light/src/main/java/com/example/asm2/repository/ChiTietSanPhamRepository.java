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

public class ChiTietSanPhamRepository {

    public ArrayList<ChiTietSanPham> getList() {
        Session session = new HibernateUtils().getFACTORY().openSession();
        ArrayList<ChiTietSanPham> list = (ArrayList<ChiTietSanPham>) session.createQuery("from ChiTietSanPham ").list();
        session.close();
        return list;
    }

    public ChiTietSanPham detailhd(Integer id) {
        Session session = HibernateUtils.getFACTORY().openSession();
        ChiTietSanPham ctsp = (ChiTietSanPham) session.createQuery("from ChiTietSanPham where id=: id_1").setParameter("id_1", id).getSingleResult();
        session.close();
        return ctsp;
    }

    public void update(ChiTietSanPham chiTietSanPham) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(chiTietSanPham);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void add(ChiTietSanPham chiTietSanPham) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(chiTietSanPham);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }

    public ChiTietSanPham getDetal(Integer id) {
        Session session = HibernateUtils.getFACTORY().openSession();
        ChiTietSanPham chiTietSanPham = (ChiTietSanPham) session.createQuery("FROM ChiTietSanPham where id =: x ").setParameter("x", id).getSingleResult();
        session.close();
        return chiTietSanPham;
    }

    public void delete(ChiTietSanPham chiTietSanPham) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(chiTietSanPham);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }

    public static void main(String[] args) {
        ArrayList<ChiTietSanPham> listsp = new ChiTietSanPhamRepository().getList();
        for (ChiTietSanPham ctsp : listsp
        ) {
            System.out.println(ctsp.getId());
        }
    }
}
