package bo.custom;

import Model.Supplier;
import View.TM.SupplierTM;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBo {

    boolean saveSupplier(Supplier s) throws SQLException, ClassNotFoundException;

    boolean updateSupplier(Supplier s) throws SQLException, ClassNotFoundException;

    boolean deleteSupplier(String s) throws SQLException, ClassNotFoundException;

    ArrayList<Supplier> getAllSuppliers() throws SQLException, ClassNotFoundException;

    ArrayList<Supplier> getMatchingSuppliers(String search) throws SQLException, ClassNotFoundException;

}
