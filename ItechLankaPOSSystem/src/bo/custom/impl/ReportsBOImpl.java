package bo.custom.impl;

import bo.custom.ReportsBO;
import dao.Custom.OrderDAO;
import dao.DAOFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class ReportsBOImpl implements ReportsBO {
    private OrderDAO oDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    @Override
    public HashMap<String, Double> getDistinctSales() throws Exception {
        return oDAO.getDistinctSales();
    }

    @Override
    public HashMap<String, Double> getAnnualSales() throws Exception {
        return oDAO.getAnnualSales();
    }

    @Override
    public HashMap<String, Double> getMonthlySales(int year) throws Exception {
        return oDAO.getMonthlySales(year);
    }

    @Override
    public ArrayList<Integer> getDistinctYears() throws Exception {
        return oDAO.getDistinctYears();
    }
}
