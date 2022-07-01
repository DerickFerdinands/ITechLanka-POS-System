package bo.custom;

import Model.OrderDTO;
import bo.SuperBO;

import java.util.ArrayList;

public interface OrderHistoryBO extends SuperBO {

    ArrayList<OrderDTO> getAll() throws Exception;

    boolean updateOrder(OrderDTO order) throws Exception;

    OrderDTO getOrder(String OrderId) throws Exception;
}
