package com.example.asm2.repository;

import com.example.asm2.connect.HibernateUtils;
import com.example.asm2.model.Size;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class SizeRepository {
    public ArrayList<Size>  getlist(){
        Session session = HibernateUtils.getFACTORY().openSession();
        ArrayList<Size> list = (ArrayList<Size>) session.createQuery("from Size ").list();
        session.close();
        return list;
    }
    public void add(Size sizeSp){
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(sizeSp);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }
    public Size getDetal(Integer id){
        Session session = HibernateUtils.getFACTORY().openSession();
        Size sizeSp = (Size) session.createQuery("FROM Size where id =: x ").setParameter("x",id).getSingleResult();
        session.close();
        return sizeSp;
    }
    public void delete(Size sizeSp) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(sizeSp);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }
}
