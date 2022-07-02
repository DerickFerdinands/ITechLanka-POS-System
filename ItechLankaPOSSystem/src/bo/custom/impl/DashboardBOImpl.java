package bo.custom.impl;

import bo.custom.DashboardBO;
import dao.Custom.CustomerDAO;
import dao.Custom.DirectorDAO;
import dao.Custom.ItemDAO;
import dao.Custom.OrderDAO;
import dao.DAOFactory;

import java.util.HashMap;

public class DashboardBOImpl implements DashboardBO {
    private OrderDAO oDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    private CustomerDAO cDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private ItemDAO iDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
    private DirectorDAO dDAO = (DirectorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DIRECTOR);
    @Override
    public HashMap<String, Double> getDistinctSales() throws Exception {
        return oDAO.getDistinctSales();
    }

    @Override
    public long getCustomerCount() throws Exception {
        return cDAO.getCount();
    }

    @Override
    public long getItemCount() throws Exception {
        return iDAO.getCount();
    }

    @Override
    public long getDirectorCount() throws Exception {
        return dDAO.getCount();
    }
}
