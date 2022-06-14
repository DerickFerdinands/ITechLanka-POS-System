package dao.Custom.impl;

import Model.Customer;
import Model.Item;
import Util.CrudUtil;
import dao.Custom.ItemDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemCRUDController implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Item");
        ArrayList<Item> itemList = new ArrayList<>();

        while (result.next()) {
            itemList.add(new Item(result.getString(1), result.getString(2), result.getString(3), result.getDouble(4), result.getDouble(5), result.getDouble(6), result.getString(7), result.getString(8)));
        }
        return itemList;
    }

    @Override
    public boolean save(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Item VALUES (?,?,?,?,?,?,?,?)", item.getCode(), item.getName(), item.getCategory(), item.getBuyingPrice(), item.getSellingPrice(), item.getQty(), item.getDetail(), item.getImageLocation());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Item WHERE Code=?", code);
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Item SET name=?,category=?,buyingPrice=?,sellingPrice=?,qty=?,details=?,imageLocation=? WHERE code =?", item.getName(), item.getCategory(), item.getBuyingPrice(), item.getSellingPrice(), item.getQty(), item.getDetail(), item.getImageLocation(), item.getCode());
    }

    @Override
    public ArrayList<Item> getMatchingResults(String search) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * from Item where code LIKE ? OR name LIKE ? OR category LIKE ? OR buyingPrice LIKE ? OR sellingPrice LIKE ? OR qty LIKE ? OR details LIKE ? OR imageLocation LIKE ?", search, search, search, search, search, search, search, search);
        ArrayList<Item> list = new ArrayList<>();

        while (result.next()) {
            list.add(new Item(result.getString(1), result.getString(2), result.getString(3), result.getDouble(4), result.getDouble(5), result.getDouble(6), result.getString(7), result.getString(8)));
        }
        return list;
    }

    @Override
    public String getLastId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllItemCodesAndNames() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT code, name FROM Item");
        ArrayList<String> itemDet = new ArrayList<>();
        while (result.next()) {
            itemDet.add(result.getString(1) + " - " + result.getString(2));
        }
        return itemDet;
    }

    @Override
    public Item get(String code) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Item WHERE COde =?", code);
        return result.next() ? new Item(
                result.getString(1),
                result.getString(2),
                result.getString(3),
                result.getDouble(4),
                result.getDouble(5),
                result.getDouble(6),
                result.getString(7),
                result.getString(8)) : null;
    }


}
