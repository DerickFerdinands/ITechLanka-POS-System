package bo.custom;

import Model.ItemDTO;
import Model.OrderDTO;
import Model.PurchaseDTO;
import Model.SupplierDTO;
import View.TM.CartTM;
import View.TM.PurchaseTM;
import bo.SuperBO;

import java.util.ArrayList;
import java.util.List;

public interface PurchaseManagementBO extends SuperBO {

    boolean addPurchase(PurchaseDTO p, List<PurchaseTM> cartList) throws Exception;

    String getPurchaseID() throws Exception;

    ArrayList<String> getItemsForComboBox() throws Exception;

    ArrayList<String> getSuppliersForComboBox() throws Exception;

    SupplierDTO getSupplier(String id) throws Exception;

    ItemDTO getItem(String code) throws Exception;
}
