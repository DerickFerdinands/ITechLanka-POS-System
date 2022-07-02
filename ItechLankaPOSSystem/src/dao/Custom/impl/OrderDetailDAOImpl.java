package dao.Custom.impl;

import Util.FactoryConfigurations;
import dao.Custom.OrderDetailDAO;
import entity.OrderDetail;
import entity.Orders;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public ArrayList<OrderDetail> getAll() throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<OrderDetail> list = session.createQuery("FROM OrderDetail ").list();
        transaction.commit();
        session.close();
        return new ArrayList<OrderDetail>(list);
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.load(OrderDetail.class,s));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean save(OrderDetail entity) throws Exception {
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
    public boolean update(OrderDetail entity) throws Exception {
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
    public ArrayList<OrderDetail> getMatchingResults(String search) throws Exception {
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
        List<String> id = session.createQuery("SELECT id FROM OrderDetail ORDER BY id DESC").setMaxResults(1).list();
        transaction.commit();
        session.close();

        return id.size()>0?String.format("#OD%05d",Integer.valueOf(id.get(0).replace("#OD",""))+1):"#OD00001";
    }

    @Override
    public double getTotalProfit() throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<BigDecimal> list = session.createQuery("SELECT SUM(sellingPrice-buyingPrice) AS TotalProfit FROM OrderDetail ").list();
        transaction.commit();
        session.close();
        return Double.valueOf(list.get(0)+"");
    }
}
