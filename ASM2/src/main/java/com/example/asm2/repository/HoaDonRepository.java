package com.example.asm2.repository;

import com.example.asm2.connect.HibernateUtils;
import com.example.asm2.model.HoaDon;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class HoaDonRepository {


    public ArrayList<HoaDon> getList() {
        Session session = HibernateUtils.getFACTORY().openSession();
        ArrayList<HoaDon> list = (ArrayList<HoaDon>) session.createQuery("from HoaDon ").list();
        session.close();
        return list;
    }

    public HoaDon detailhd(Integer idHoaDon) {
        Session session = HibernateUtils.getFACTORY().openSession();
        HoaDon hd = (HoaDon) session.createQuery("from HoaDon where id=: id_1").setParameter("id_1", idHoaDon).getSingleResult();
        session.close();
        return hd;
    }
    public ArrayList<HoaDon> Search(String sdt) {
        Session session = HibernateUtils.getFACTORY().openSession();
        ArrayList<HoaDon> list = (ArrayList<HoaDon>) session.createQuery("from HoaDon where idKhachHang.soDienThoai =: id_1").setParameter("id_1", sdt).list();
        session.close();
        return list;
    }
    public void add(HoaDon hd){
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(hd);
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
    }
    public void delete(HoaDon hoaDon) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(hoaDon);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }
}
