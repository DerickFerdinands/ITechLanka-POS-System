package dao.Custom;

import dao.CrudDAO;
import entity.OrderDetail;

public interface OrderDetailDAO extends CrudDAO<OrderDetail,String> {

    double getTotalProfit() throws Exception;
}
