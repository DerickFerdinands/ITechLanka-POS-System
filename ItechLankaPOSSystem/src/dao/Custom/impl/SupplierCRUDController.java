package dao.Custom.impl;

import Model.Supplier;
import Util.CrudUtil;
import dao.Custom.SupplierDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierCRUDController implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Supplier");
        ArrayList<Supplier> SupLsit = new ArrayList<>();

        while (result.next()) {
            SupLsit.add(new Supplier(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5)));
        }

        return SupLsit;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Supplier WHERE id=?", s);
    }

    @Override
    public boolean save(Supplier d) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Supplier VALUES (?,?,?,?,?)", d.getId(), d.getName(), d.getEmail(), d.getAddress(), d.getMobile());
    }

    @Override
    public boolean update(Supplier d) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Supplier SET name=?, email=?, address=?, mobile=? WHERE id=?", d.getName(), d.getEmail(), d.getAddress(), d.getMobile(), d.getId());
    }

    @Override
    public ArrayList<Supplier> getMatchingResults(String search) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Supplier WHERE id LIKE ? OR name LIKE ? OR email LIKE ? OR mobile LIKE ? OR address LIKE ? ", search, search, search, search, search);
        ArrayList<Supplier> supList = new ArrayList<>();

        while (result.next()) {
            supList.add(new Supplier(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5)));
        }
        return supList;
    }

    @Override
    public String getLastId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT id FROM Supplier ORDER BY id DESC LIMIT 1");

        if (result.next()) {
            return result.getString(1);
        } else {
            return "S00-001";
        }
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String id = getLastId();

        if (id.equals("S00-001")) {
            return id;
        } else {
            String[] splitted = id.split("S00-");
            return String.format("S00-%03d", Integer.valueOf(splitted[1]) + 1);
        }
    }
}
