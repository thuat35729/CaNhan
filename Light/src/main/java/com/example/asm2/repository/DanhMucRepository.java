package com.example.asm2.repository;

import com.example.asm2.connect.HibernateUtils;
import com.example.asm2.model.DanhMuc;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class DanhMucRepository {
    public ArrayList<DanhMuc> getList() {
        Session session = HibernateUtils.getFACTORY().openSession();
        ArrayList<DanhMuc> list = (ArrayList<DanhMuc>) session.createQuery("from  DanhMuc order by ngayTao desc ").list();
        session.close();
        return list;
    }

    public void add(DanhMuc danhMuc) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(danhMuc);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }

    public DanhMuc getDetail(Integer id) {
        Session session = HibernateUtils.getFACTORY().openSession();
        DanhMuc danhMuc = (DanhMuc) session.createQuery("from  DanhMuc where id =:id_1").
                setParameter("id_1", id).getSingleResult();
        session.close();
        return danhMuc;
    }

    public void delete(DanhMuc danhMuc) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(danhMuc);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }
}
