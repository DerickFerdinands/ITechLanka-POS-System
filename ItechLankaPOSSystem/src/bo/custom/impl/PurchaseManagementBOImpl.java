package bo.custom.impl;

import Model.PurchaseDTO;
import Model.SupplierDTO;
import View.TM.PurchaseTM;
import bo.custom.PurchaseManagementBO;
import dao.Custom.PurchaseDAO;
import dao.Custom.PurchaseDetailDAO;
import dao.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseManagementBOImpl implements PurchaseManagementBO {
    private PurchaseDAO pDAO = (PurchaseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PURCHASE);
    private PurchaseDetailDAO pdDAO = (PurchaseDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PURCHASE_DETAIL);

    @Override
    public boolean addPurchase(PurchaseDTO p, List<PurchaseTM> cartList) throws Exception {
        return false;
    }

    @Override
    public String getPurchaseID() throws Exception {
        return null;
    }
}
