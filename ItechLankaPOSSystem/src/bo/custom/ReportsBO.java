package bo.custom;

import bo.SuperBO;

import java.util.ArrayList;
import java.util.HashMap;

public interface ReportsBO extends SuperBO {

    HashMap<String, Double> getDistinctSales() throws Exception;

    HashMap<String, Double> getAnnualSales() throws Exception;

    HashMap<String, Double> getMonthlySales(int year) throws Exception;

    ArrayList<Integer> getDistinctYears() throws Exception;
}
