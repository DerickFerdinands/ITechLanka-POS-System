package bo.custom;

import bo.SuperBO;

import java.util.HashMap;

public interface DashboardBO extends SuperBO {
    HashMap<String, Double> getDistinctSales() throws Exception;

    long getCustomerCount() throws Exception;

    long getItemCount() throws Exception;

    long getDirectorCount() throws Exception;
}
