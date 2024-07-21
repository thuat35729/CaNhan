package com.example.asm2.repository;

import com.example.asm2.connect.HibernateUtils;
import com.example.asm2.model.ChiTietSanPham;
import com.example.asm2.model.KhachHang;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class KhachHangRepository {
    public ArrayList<KhachHang> getList() {
        Session session = new HibernateUtils().getFACTORY().openSession();
        ArrayList<KhachHang> list = (ArrayList<KhachHang>) session.createQuery("from KhachHang ").list();
        session.close();
        return list;
    }
    public KhachHang detailkh(Integer idkh){
        Session session = HibernateUtils.getFACTORY().openSession();
        KhachHang kh = (KhachHang) session.createQuery("from KhachHang where id =: id_1").setParameter("id_1", idkh).getSingleResult();
        session.close();
        return kh;
    }
    public void add(KhachHang khachHang){
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(khachHang);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }
    public void delete(KhachHang khachHang) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(khachHang);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }
}
