package bo;

import bo.custom.impl.QuotationManagementBOImpl;
import bo.custom.impl.SupplierBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public SuperBO getBO(BOTypes type) {
        switch (type) {
            case QUOTATION:
                return new QuotationManagementBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            default:
                return null;
        }
    }

    public enum BOTypes {
        QUOTATION, SUPPLIER
    }
}
