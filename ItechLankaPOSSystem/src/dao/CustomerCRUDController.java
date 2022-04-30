package dao;

import Model.Customer;
import Util.CrudUtil;
import View.TM.CustomerTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerCRUDController {

    public static boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Customer VALUES (?,?,?,?,?)", customer.getId(), customer.getName(), customer.getNic(), customer.getMobile(), customer.getAddress());
    }

    public static ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> CuList = new ArrayList<>();

        while (result.next()) {
            CuList.add(new Customer(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5)));
        }

        return CuList;
    }

    public static boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Customer SET Name=?,nic=?,mobile=?,address=? WHERE id=?", c.getName(), c.getNic(), c.getMobile(), c.getAddress(), c.getId());
    }

    public static boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Customer WHERE id=?", id);
    }

    public static ArrayList<Customer> getMatching(String search) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * from Customer where id LIKE ? OR name LIKE ? OR nic LIKE ? OR mobile LIKE ? OR address LIKE ?", search,search,search,search,search);
        ArrayList<Customer> list = new ArrayList<>();

        while (result.next()) {
            list.add(new Customer(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5)));
        }
        return list;
    }
}