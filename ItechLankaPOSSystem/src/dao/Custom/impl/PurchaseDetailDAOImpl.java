package dao.Custom.impl;

import Util.FactoryConfigurations;
import dao.Custom.PurchaseDetailDAO;
import entity.OrderDetail;
import entity.PurchaseDetail;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PurchaseDetailDAOImpl implements PurchaseDetailDAO {
    @Override
    public ArrayList<PurchaseDetail> getAll() throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<PurchaseDetail> list = session.createQuery("FROM PurchaseDetail ").list();
        transaction.commit();
        session.close();
        return new ArrayList<PurchaseDetail>(list);
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.load(PurchaseDetail.class,s));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean save(PurchaseDetail entity) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);
            transaction.commit();
            session.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }

    }

    @Override
    public boolean update(PurchaseDetail entity) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(entity);
            transaction.commit();
            session.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
            session.close();
            return false;
        }

    }

    @Override
    public ArrayList<PurchaseDetail> getMatchingResults(String search) throws Exception {
        return null;
    }

    @Override
    public String getLastId() throws Exception {
        return null;
    }

    @Override
    public String getNextId() throws Exception {
        return null;
    }
}
