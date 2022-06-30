package bo.custom;

import Model.OrderDTO;
import View.TM.CartTM;
import bo.SuperBO;

import java.util.List;

public interface QuotationManagementBO extends SuperBO {

    boolean addOrder(OrderDTO o, List<CartTM> cartList) throws Exception;

    String getOrderId() throws Exception;


}
