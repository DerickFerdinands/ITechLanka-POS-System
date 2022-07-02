package dao.Custom.impl;


import Util.CrudUtil;
import Util.FactoryConfigurations;
import dao.Custom.CustomerDAO;
import entity.Customer;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerCRUDController implements CustomerDAO {

    @Override
    public boolean save(Customer Customer) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(Customer);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Customer> from_customer = session.createQuery("FROM Customer").list();
        transaction.commit();
        session.close();
        return new ArrayList<Customer>(from_customer);
    }

    @Override
    public boolean update(Customer c) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(c);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.load(Customer.class, id));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Customer> getMatchingResults(String search) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Customer> from_customer = session.createQuery("FROM Customer where Id LIKE :ID OR name LIKE :Name OR nic LIKE :NIC OR mobile LIKE :Mobile OR address LIKE :Address")
                .setParameter("ID", search)
                .setParameter("Name", search)
                .setParameter("NIC", search)
                .setParameter("Mobile", search)
                .setParameter("Address", search).list();
        transaction.commit();
        session.close();
        return new ArrayList<Customer>(from_customer);
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
    public ArrayList<String> getALlCustomerNICAndNames() throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Object[]> list = session.createQuery("SELECT name, nic FROM Customer").list();
        transaction.commit();
        session.close();
        return new ArrayList<String>(list.stream().map(o -> {
            return o[0] + " - " + o[1];
        }).collect(Collectors.toList()));
    }

    @Override
    public Customer get(String nic) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Customer> Customer = session.createQuery("FROM Customer WHERE nic =:NIC").setParameter("NIC", nic).setMaxResults(1).list();
        transaction.commit();
        session.close();
        return Customer.size()>0?Customer.get(0):null;
    }

    @Override
    public long getCount() throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Long> list = session.createQuery("SELECT COUNT(Id) FROM Customer").list();
        transaction.commit();
        session.close();
        return list.get(0);
    }
}
