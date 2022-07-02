package dao.Custom;

import Model.ItemDTO;
import dao.CrudDAO;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item, String> {
    ArrayList<String> getAllItemCodesAndNames() throws Exception;
    Item get(String code) throws Exception;

    long getCount() throws Exception;
}
