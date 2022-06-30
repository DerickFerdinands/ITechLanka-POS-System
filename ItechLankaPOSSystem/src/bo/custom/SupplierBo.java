package bo.custom;

import Model.SupplierDTO;
import bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBo extends SuperBO {

    boolean saveSupplier(SupplierDTO s) throws Exception;

    boolean updateSupplier(SupplierDTO s) throws SQLException, ClassNotFoundException, Exception;

    boolean deleteSupplier(String s) throws SQLException, ClassNotFoundException, Exception;

    ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException, Exception;

    ArrayList<SupplierDTO> getMatchingSuppliers(String search) throws SQLException, ClassNotFoundException, Exception;

}
