package bo;

import bo.custom.impl.*;

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
            case PURCHASE:
                return new PurchaseManagementBOImpl();
            case ORDER_HISTORY:
                return new OrderHistoryBOImpl();
            case DIRECTOR_PROFIT:
                return new ProfitBOImpl();
            case DASHBOARD:
                return new DashboardBOImpl();
            case REPORTS:
                return new ReportsBOImpl();
            default:
                return null;
        }
    }

    public enum BOTypes {
        QUOTATION, SUPPLIER, PURCHASE, ORDER_HISTORY, DIRECTOR_PROFIT, DASHBOARD, REPORTS
    }
}
