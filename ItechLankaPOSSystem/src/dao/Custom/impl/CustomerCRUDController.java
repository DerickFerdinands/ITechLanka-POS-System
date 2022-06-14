package dao.Custom.impl;

import Model.Customer;
import Util.CrudUtil;
import View.TM.CustomerTM;
import dao.Custom.CustomerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomerCRUDController implements CustomerDAO {
    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Customer VALUES (?,?,?,?,?)", customer.getId(), customer.getName(), customer.getNic(), customer.getMobile(), customer.getAddress());
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> CuList = new ArrayList<>();

        while (result.next()) {
            CuList.add(new Customer(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5)));
        }

        return CuList;
    }

    @Override
    public boolean update(Customer c) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Customer SET Name=?,nic=?,mobile=?,address=? WHERE id=?", c.getName(), c.getNic(), c.getMobile(), c.getAddress(), c.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Customer WHERE id=?", id);
    }

    @Override
    public ArrayList<Customer> getMatchingResults(String search) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * from Customer where id LIKE ? OR name LIKE ? OR nic LIKE ? OR mobile LIKE ? OR address LIKE ?", search, search, search, search, search);
        ArrayList<Customer> list = new ArrayList<>();

        while (result.next()) {
            list.add(new Customer(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5)));
        }
        return list;
    }

    @Override
    public String getLastId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getALlCustomerNICAndNames() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT Name, NIC FROM Customer");
        ArrayList<String> cusDet = new ArrayList<>();

        while (result.next()) {
            cusDet.add(result.getString(1) + " - " + result.getString(2));
        }
        return cusDet;
    }

    @Override
    public Customer get(String nic) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Customer WHERE nic = ?", nic);
        return result.next() ? new Customer(
                result.getString(1),
                result.getString(2),
                result.getString(3),
                result.getString(4),
                result.getString(5)): null;
    }
}
