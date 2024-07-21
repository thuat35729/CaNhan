package com.example.asm2.repository;

import com.example.asm2.connect.HibernateUtils;
import com.example.asm2.model.MauSac;
import com.example.asm2.model.Size;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class MauSacRepository {
    public ArrayList<MauSac> getList() {
        Session session = HibernateUtils.getFACTORY().openSession();
        ArrayList<MauSac> list = (ArrayList<MauSac>) session.createQuery("from MauSac ").list();
        session.close();
        return list;
    }

    public void add(MauSac mauSac) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(mauSac);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }

    public MauSac getDetal(Integer id) {
        Session session = HibernateUtils.getFACTORY().openSession();
        MauSac mauSac = (MauSac) session.createQuery("FROM MauSac where id =: x ").setParameter("x", id).getSingleResult();
        session.close();
        return mauSac;
    }

    public void delete(MauSac mauSac) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(mauSac);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }
}
