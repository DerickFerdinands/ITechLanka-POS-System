package bo.custom.impl;

import Model.OrderDTO;
import View.TM.CartTM;
import bo.custom.QuotationManagementBO;
import dao.Custom.ItemDAO;
import dao.Custom.OrderDAO;
import dao.Custom.OrderDetailDAO;
import dao.DAOFactory;
import entity.Item;
import entity.OrderDetail;
import entity.Orders;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class QuotationManagementBOImpl implements QuotationManagementBO {

    private final OrderDAO oDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailDAO odDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);
    private final ItemDAO iDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean addOrder(OrderDTO oDTO, List<CartTM> cartList) throws Exception {
        Orders o = new Orders(oDTO.getId(), oDTO.getCustomer(), oDTO.getDate(), oDTO.getTotal(), oDTO.getPaymentStatus(), oDTO.getPayedAmount());
        AtomicInteger num = new AtomicInteger();
        List<OrderDetail> odList = cartList.stream().map(cartTM -> {
            try {
                Item item = iDAO.get(cartTM.getItemCode());
                return new OrderDetail(o.getId() + " - " + (num.incrementAndGet()), o,item , cartTM.getQty(),item.getBuyingPrice(),item.getSellingPrice());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());

        if (oDAO.save(o)) {
            o.setOrderDetailList(odList);
            o.getOrderDetailList().forEach(orderDetail -> {
                try {
                    odDAO.save(orderDetail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            odList.forEach(orderDetail -> {
                Item item = orderDetail.getItem();
                item.setQty(item.getQty() - orderDetail.getQty());
                try {
                    iDAO.update(item);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getOrderId() throws Exception {
        return oDAO.getNextId();
    }

}
