package bo.custom.impl;

import Model.ItemDTO;
import Model.PurchaseDTO;
import Model.SupplierDTO;
import View.TM.PurchaseTM;
import bo.custom.PurchaseManagementBO;
import dao.Custom.ItemDAO;
import dao.Custom.PurchaseDAO;
import dao.Custom.PurchaseDetailDAO;
import dao.Custom.SupplierDAO;
import dao.DAOFactory;
import entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PurchaseManagementBOImpl implements PurchaseManagementBO {
    private PurchaseDAO pDAO = (PurchaseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PURCHASE);
    private PurchaseDetailDAO pdDAO = (PurchaseDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PURCHASE_DETAIL);
    private final ItemDAO iDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
    private SupplierDAO sDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public boolean addPurchase(PurchaseDTO pDTO, List<PurchaseTM> cartList) throws Exception {
        SupplierDTO s = pDTO.getSupplier();
        Purchase p = new Purchase(pDTO.getID(), new Supplier(s.getId(),s.getName(),s.getEmail(),s.getAddress(),s.getMobile()), pDTO.getTotal(), pDTO.getDate());
        AtomicInteger num = new AtomicInteger();
        List<PurchaseDetail> pdList = cartList.stream().map(purchaseTM -> {
            try {
                Item item = iDAO.get(purchaseTM.getItemCode());
                return new PurchaseDetail(p.getID() + " - " + (num.incrementAndGet()), p,item , Double.valueOf(purchaseTM.getUnitPrice()+""),purchaseTM.getQty());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());

        if (pDAO.save(p)) {
            p.setPurchaseDetailList(pdList);
            p.getPurchaseDetailList().forEach(purchaseDEtail -> {
                try {
                    pdDAO.save(purchaseDEtail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            pdList.forEach(purchaseDetail -> {
                Item item = purchaseDetail.getItem();
                item.setQty(item.getQty() +purchaseDetail.getQty());
                item.setBuyingPrice(BigDecimal.valueOf(purchaseDetail.getBuyingPrice()));
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
    public String getPurchaseID() throws Exception {
        return pDAO.getNextId();
    }

    @Override
    public ArrayList<String> getItemsForComboBox() throws Exception {
        return iDAO.getAllItemCodesAndNames();
    }

    @Override
    public ArrayList<String> getSuppliersForComboBox() throws Exception {
        return sDAO.getALlSupplierIdAndNames();
    }

    @Override
    public SupplierDTO getSupplier(String id) throws Exception {
        Supplier supplier = sDAO.get(id);
        System.out.println(supplier);
        return new SupplierDTO(supplier.getId(), supplier.getName(), supplier.getEmail(), supplier.getAddress(), supplier.getMobile());
    }

    @Override
    public ItemDTO getItem(String code) throws Exception {
        Item item = iDAO.get(code);
        System.out.println(item);
        return new ItemDTO(item.getCode(), item.getName(),item.getCategory(),Double.valueOf(item.getBuyingPrice()+""),Double.valueOf(item.getSellingPrice()+""),item.getQty(),item.getDetails(),item.getImageLocation());
    }
}
