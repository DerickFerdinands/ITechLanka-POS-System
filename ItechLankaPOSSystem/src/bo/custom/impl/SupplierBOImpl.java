package bo.custom.impl;

import Model.SupplierDTO;
import bo.custom.SupplierBo;
import dao.Custom.SupplierDAO;
import dao.Custom.impl.SupplierCRUDController;
import entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBo {
    SupplierDAO SupCrudOps = new SupplierCRUDController();

    @Override
    public boolean saveSupplier(SupplierDTO s) throws Exception {
        return SupCrudOps.save(new Supplier(s.getId(),s.getName(),s.getEmail(),s.getAddress(),s.getMobile()));
    }

    @Override
    public boolean updateSupplier(SupplierDTO s) throws Exception {
        return SupCrudOps.update(new Supplier(s.getId(),s.getName(),s.getEmail(),s.getAddress(),s.getMobile()));
    }

    @Override
    public boolean deleteSupplier(String s) throws Exception {
        return SupCrudOps.delete(s);
    }

    @Override
    public ArrayList<SupplierDTO> getAllSuppliers() throws Exception {
        ArrayList<Supplier> all = SupCrudOps.getAll();
        ArrayList<SupplierDTO> allSuppliers = new ArrayList<>();
        for (Supplier s : all){
            allSuppliers.add(new SupplierDTO(s.getId(),s.getName(),s.getEmail(),s.getAddress(),s.getMobile()));
        }
        return allSuppliers;
    }

    @Override
    public ArrayList<SupplierDTO> getMatchingSuppliers(String search) throws Exception {
        ArrayList<Supplier> matchingResults = SupCrudOps.getMatchingResults(search);
        ArrayList<SupplierDTO> matching = new ArrayList<>();
        for (Supplier s : matchingResults){
            matching.add(new SupplierDTO(s.getId(),s.getName(),s.getEmail(),s.getAddress(),s.getMobile()));
        }
        return matching;
    }


}
