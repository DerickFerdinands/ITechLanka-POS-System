package dao.Custom.impl;

import Util.CrudUtil;
import Util.FactoryConfigurations;
import dao.Custom.DirectorDAO;
import entity.Director;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectorCRUDController implements DirectorDAO {
    @Override
    public ArrayList<Director> getAll() throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Director> list = session.createQuery("From Director").list();
        transaction.commit();
        session.close();
        return new ArrayList<Director>(list);
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.load(Director.class,id));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean save(Director d) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(d);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Director d) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(d);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Director> getMatchingResults(String search) throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Director> list = session.createQuery(" from Director where id LIKE :ID OR name LIKE :Name OR nic LIKE :NIC OR mobile LIKE :Mobile OR address LIKE :Address OR profitMargin = :Margin")
                .setParameter("ID",search)
                .setParameter("Name",search)
                .setParameter("NIC",search)
                .setParameter("Mobile",search)
                .setParameter("Address",search)
                .setParameter("Margin",search).list();
        transaction.commit();
        session.close();
        return new ArrayList<Director>(list);
    }

    @Override
    public String getLastId() throws Exception {

        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<String> list = session.createQuery("SELECT id FROM Director ORDER BY id DESC").setMaxResults(1).list();
        transaction.commit();
        session.close();
        return list.size()>0? list.get(0):"D00-001";
    }

    @Override
    public String getNextId() throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<String> list = session.createQuery("SELECT id FROM Director ORDER BY id DESC").setMaxResults(1).list();
        transaction.commit();
        session.close();
        String id = list.size()>0? list.get(0):"";

        if (id.equals("")) {
            return "D00-001";
        } else {
            String[] splitted = id.split("D00-");
            return String.format("D00-%03d", Integer.valueOf(splitted[1]) + 1);
        }
    }


    @Override
    public long getCount() throws Exception {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Long> list = session.createQuery("SELECT COUNT(id) FROM Director").list();
        transaction.commit();
        session.close();
        return list.get(0);
    }
}
