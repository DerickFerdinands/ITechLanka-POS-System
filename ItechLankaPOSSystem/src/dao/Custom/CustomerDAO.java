package dao.Custom;

import Model.CustomerDTO;
import dao.CrudDAO;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer, String> {

    ArrayList<String> getALlCustomerNICAndNames() throws Exception;

    Customer get(String id) throws Exception;

    long getCount() throws Exception;
}
