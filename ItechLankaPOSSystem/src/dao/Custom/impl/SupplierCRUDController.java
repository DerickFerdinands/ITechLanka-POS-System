package dao.Custom.impl;

import Model.SupplierDTO;
import Util.CrudUtil;
import Util.FactoryConfigurations;
import dao.Custom.SupplierDAO;
import entity.Item;
import entity.Supplier;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SupplierCRUDController implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Supplier> list = session.createQuery("FROM Supplier").list();
        transaction.commit();
        session.close();
        return new ArrayList<Supplier>(list);
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.load(Supplier.class, s));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean save(Supplier d) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(d);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean update(Supplier d) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(d);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Supplier> getMatchingResults(String search) throws Exception {
        ResultSet result = CrudUtil.execute("SELECT * FROM Supplier WHERE id LIKE ? OR name LIKE ? OR email LIKE ? OR mobile LIKE ? OR address LIKE ? ", search, search, search, search, search);
        ArrayList<Supplier> supList = new ArrayList<>();

        while (result.next()) {
            supList.add(new Supplier(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5)));
        }
        return supList;
    }

    @Override
    public String getLastId() throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<String> list = session.createQuery("SELECT id FROM Supplier ORDER BY id DESC").setMaxResults(1).list();
        transaction.commit();
        session.close();
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return "S00-001";
        }
    }

    @Override
    public String getNextId() throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<String> list = session.createQuery("SELECT id FROM Supplier ORDER BY id DESC").setMaxResults(1).list();
        transaction.commit();
        session.close();
        String id = list.size() > 0 ? list.get(0) : "";

        if (id.equals("")) {
            return id;
        } else {
            String[] splitted = id.split("S00-");
            return String.format("S00-%03d", Integer.valueOf(splitted[1]) + 1);
        }
    }

    @Override
    public ArrayList<String> getALlSupplierIdAndNames() throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Object[]> list = session.createQuery("SELECT Id, name FROM Supplier").list();
        transaction.commit();
        session.close();
        return new ArrayList<String>(list.stream().map(o -> {
            return o[0] + " - " + o[1];
        }).collect(Collectors.toList()));
    }

    @Override
    public Supplier get(String id) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Supplier supplier = session.get(Supplier.class, id);
        transaction.commit();
        session.close();
        return supplier;
    }
}
