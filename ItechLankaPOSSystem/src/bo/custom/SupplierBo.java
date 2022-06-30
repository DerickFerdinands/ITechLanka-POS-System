package bo.custom;

import Model.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBo {

    boolean saveSupplier(SupplierDTO s) throws SQLException, ClassNotFoundException;

    boolean updateSupplier(SupplierDTO s) throws SQLException, ClassNotFoundException;

    boolean deleteSupplier(String s) throws SQLException, ClassNotFoundException;

    ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;

    ArrayList<SupplierDTO> getMatchingSuppliers(String search) throws SQLException, ClassNotFoundException;

}
