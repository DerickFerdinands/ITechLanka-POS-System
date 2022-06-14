package dao.Custom;

import Model.Customer;
import dao.CrudDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface CustomerDAO extends CrudDAO<Customer, String> {

    ArrayList<String> getALlCustomerNICAndNames() throws SQLException, ClassNotFoundException;

    Customer get(String id) throws SQLException, ClassNotFoundException;
}
