package dao.Custom.impl;

import Util.FactoryConfigurations;
import dao.Custom.OrderDAO;
import entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<Orders> getAll() throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Orders> list = session.createQuery("FROM Orders").list();
        transaction.commit();
        session.close();
        return new ArrayList<Orders>(list);
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.load(Orders.class,s));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean save(Orders entity) throws Exception {
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
    public boolean update(Orders entity) throws Exception {
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
    public ArrayList<Orders> getMatchingResults(String search) throws Exception {
        return null;
    }

    @Override
    public String getLastId() throws Exception {
        return null;
    }

    @Override
    public String getNextId() throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<String> id = session.createQuery("SELECT Id FROM Orders ORDER BY Id DESC").setMaxResults(1).list();
        transaction.commit();
        session.close();

        return id.size()>0?String.format("#O%05d",Integer.valueOf(id.get(0).replace("#O",""))+1):"#O00001";
    }
}
