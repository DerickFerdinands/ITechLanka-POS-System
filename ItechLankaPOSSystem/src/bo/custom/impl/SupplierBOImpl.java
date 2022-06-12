package bo.custom.impl;

import Model.Supplier;
import View.TM.SupplierTM;
import bo.custom.SupplierBo;
import com.jfoenix.controls.JFXButton;
import dao.Custom.SupplierDAO;
import dao.Custom.impl.SupplierCRUDController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBo {
    SupplierDAO SupCrudOps = new SupplierCRUDController();

    @Override
    public boolean saveSupplier(Supplier s) throws SQLException, ClassNotFoundException {
        return SupCrudOps.save(s);
    }

    @Override
    public boolean updateSupplier(Supplier s) throws SQLException, ClassNotFoundException {
        return SupCrudOps.update(s);
    }

    @Override
    public boolean deleteSupplier(String s) throws SQLException, ClassNotFoundException {
        return SupCrudOps.delete(s);
    }

    @Override
    public ArrayList<Supplier> getAllSuppliers() throws SQLException, ClassNotFoundException {
        return SupCrudOps.getAll();
    }

    @Override
    public ArrayList<Supplier> getMatchingSuppliers(String search) throws SQLException, ClassNotFoundException {
        return SupCrudOps.getMatchingResults(search);
    }


}
