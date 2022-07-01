package bo.custom.impl;

import Model.OrderDTO;
import bo.custom.OrderHistoryBO;
import dao.Custom.OrderDAO;
import dao.DAOFactory;
import entity.OrderDetail;
import entity.Orders;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class OrderHistoryBOImpl implements OrderHistoryBO {

    private OrderDAO oDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    @Override
    public ArrayList<OrderDTO> getAll() throws Exception {
        ArrayList<Orders> all = oDAO.getAll();

        return new ArrayList<OrderDTO>(all.stream().map(orders -> {
            return new OrderDTO(orders.getId(),orders.getCustomer(),orders.getDate(),orders.getTotal(),orders.getPaymentStatus(),orders.getPayedAmount());
        }).collect(Collectors.toList()));
    }

    @Override
    public boolean updateOrder(OrderDTO order) throws Exception {
        return oDAO.update(new Orders(order.getId(),order.getCustomer(),order.getDate(),order.getTotal(),order.getPaymentStatus(),order.getPayedAmount()));
    }

    @Override
    public OrderDTO getOrder(String OrderId) throws Exception {
        Orders order = oDAO.get(OrderId);
        return new OrderDTO(order.getId(),order.getCustomer(),order.getDate(),order.getTotal(),order.getPaymentStatus(),order.getPayedAmount());
    }
}
