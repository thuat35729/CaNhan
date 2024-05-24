package com.example.asm2.repository;

import com.example.asm2.connect.HibernateUtils;
import com.example.asm2.model.SanPham;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class SanPhamRepository {
    public ArrayList<SanPham> getList(){
        Session session = HibernateUtils.getFACTORY().openSession();
        ArrayList<SanPham> list = (ArrayList<SanPham>) session.createQuery("from SanPham ").list();
        session.close();
        return list;
    }
    public void add(SanPham sanPham) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(sanPham);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public SanPham getDetal(Integer id){
        Session session = HibernateUtils.getFACTORY().openSession();
        SanPham chiTietSanPham = (SanPham) session.createQuery("FROM SanPham where id =: x ").setParameter("x",id).getSingleResult();
        session.close();
        return chiTietSanPham;
    }
    public void delete(SanPham chiTietSanPham) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(chiTietSanPham);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }
}
