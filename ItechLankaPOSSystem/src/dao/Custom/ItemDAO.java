package dao.Custom;

import Model.Item;
import dao.CrudDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item, String> {
    ArrayList<String> getAllItemCodesAndNames() throws SQLException, ClassNotFoundException;
    Item get(String code) throws SQLException, ClassNotFoundException;
}
