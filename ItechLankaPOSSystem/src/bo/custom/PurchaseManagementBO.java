package bo.custom;

import Model.OrderDTO;
import Model.PurchaseDTO;
import View.TM.CartTM;
import View.TM.PurchaseTM;
import bo.SuperBO;

import java.util.List;

public interface PurchaseManagementBO extends SuperBO {

    boolean addPurchase(PurchaseDTO p, List<PurchaseTM> cartList) throws Exception;

    String getPurchaseID() throws Exception;

}
