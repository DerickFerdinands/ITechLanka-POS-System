package dao.Custom;

import dao.CrudDAO;
import entity.Orders;

public interface OrderDAO extends CrudDAO<Orders,String> {
    Orders get(String OrderID) throws Exception;
}
