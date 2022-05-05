package dao;

import Model.Item;
import Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemCRUDController {
    public static ArrayList<Item> getAllItems() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Item");
        ArrayList<Item> itemList = new ArrayList<>();

        while (result.next()) {
            itemList.add(new Item(result.getString(1), result.getString(2), result.getString(3), result.getDouble(4), result.getDouble(5), result.getDouble(6), result.getString(7), result.getString(8)));
        }
        return itemList;
    }

    public static boolean saveItem(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Item VALUES (?,?,?,?,?,?,?,?)", item.getCode(), item.getName(), item.getCategory(), item.getBuyingPrice(), item.getSellingPrice(), item.getQty(), item.getDetail(), item.getImageLocation());
    }

    public static boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Item WHERE Code=?", code);
    }

    public static boolean updateItem(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Item SET name=?,category=?,buyingPrice=?,sellingPrice=?,qty=?,details=?,imageLocation=? WHERE code =?", item.getName(), item.getCategory(), item.getBuyingPrice(), item.getSellingPrice(), item.getQty(), item.getDetail(), item.getImageLocation(), item.getCode());
    }
}
