package dao.Custom;


import Model.SupplierDTO;
import dao.CrudDAO;
import entity.Supplier;

import java.util.ArrayList;

public interface SupplierDAO extends CrudDAO<Supplier,String> {
    ArrayList<String> getALlSupplierIdAndNames() throws Exception;

    Supplier get(String id) throws Exception;
}
