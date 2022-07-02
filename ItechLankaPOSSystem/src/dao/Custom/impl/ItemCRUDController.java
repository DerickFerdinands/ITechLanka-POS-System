package dao.Custom.impl;


import Util.CrudUtil;
import Util.FactoryConfigurations;
import dao.Custom.ItemDAO;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemCRUDController implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List list = session.createQuery("FROM Item").list();
        transaction.commit();
        session.close();

        return new ArrayList<Item>(list);
    }

    @Override
    public boolean save(Item Item) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(Item);
        transaction.commit();
        session.close();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String code) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(Item.class, code));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Item Item) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(Item);
        transaction.commit();
        session.close();
        session.close();
        return true;    }

    @Override
    public ArrayList<Item> getMatchingResults(String search) throws Exception {
        ResultSet result = CrudUtil.execute("SELECT * from Item where code LIKE ? OR name LIKE ? OR category LIKE ? OR buyingPrice LIKE ? OR sellingPrice LIKE ? OR qty LIKE ? OR details LIKE ? OR imageLocation LIKE ?", search, search, search, search, search, search, search, search);
        ArrayList<Item> list = new ArrayList<>();

        while (result.next()) {
            list.add(new Item(result.getString(1), result.getString(2), result.getString(3), result.getBigDecimal(4), result.getBigDecimal(5), result.getInt(6), result.getString(7), result.getString(8)));
        }
        return list;
    }

    @Override
    public String getLastId() throws Exception {
        return null;
    }

    @Override
    public String getNextId() throws Exception {
        return null;
    }

    @Override
    public ArrayList<String> getAllItemCodesAndNames() throws Exception {
        ResultSet result = CrudUtil.execute("SELECT code, name FROM Item");
        ArrayList<String> itemDet = new ArrayList<>();
        while (result.next()) {
            itemDet.add(result.getString(1) + " - " + result.getString(2));
        }
        return itemDet;
    }

    @Override
    public Item get(String code) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Item item = session.get(Item.class, code);
        transaction.commit();
        session.close();
        return item;
    }

    @Override
    public long getCount() throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Long> list = session.createQuery("SELECT COUNT(code) FROM Item").list();
        transaction.commit();
        session.close();
        return list.get(0);
    }


}
