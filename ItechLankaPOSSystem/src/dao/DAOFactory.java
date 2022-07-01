package dao;

import dao.Custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public SuperDAO getDAO(DAOTypes type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerCRUDController();
            case DIRECTOR:
                return new DirectorCRUDController();
            case ITEM:
                return new ItemCRUDController();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();
            case SUPPLIER:
                return new SupplierCRUDController();
            case PURCHASE:
                return new PurchaseDAOImpl();
            case PURCHASE_DETAIL:
                return new PurchaseDetailDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        CUSTOMER, DIRECTOR, ITEM, ORDER, ORDER_DETAIL, SUPPLIER, PURCHASE, PURCHASE_DETAIL
    }
}
