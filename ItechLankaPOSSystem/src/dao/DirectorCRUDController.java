package dao;

import Model.Customer;
import Model.Director;
import Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DirectorCRUDController {

    public static ArrayList<Director> getAllDirectors() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Director");
        ArrayList<Director> DirList = new ArrayList<>();

        while (result.next()) {
            DirList.add(new Director(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getDouble(6)));
        }
        return DirList;
    }

    public static boolean deleteDirector(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Director WHERE id=?", id);
    }

    public static boolean saveDirector(Director d) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Director VALUES (?,?,?,?,?,?)", d.getId(), d.getName(), d.getNic(), d.getMobile(), d.getAddress(), d.getProfitMargin());
    }

    public static boolean updateDrirector(Director d) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Director SET name=?,nic=?,mobile=?,address=?,profitMargin=? WHERE id=?", d.getName(), d.getNic(), d.getMobile(), d.getAddress(), d.getProfitMargin(), d.getId());
    }

    public static ArrayList<Director> getMatching(String search) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * from Director where id LIKE ? OR name LIKE ? OR nic LIKE ? OR mobile LIKE ? OR address LIKE ? OR profitMargin LIKE ?", search, search, search, search, search, search);
        ArrayList<Director> list = new ArrayList<>();

        while (result.next()) {
            list.add(new Director(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getDouble(6)));
        }
        return list;
    }

    public static String getLastId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT id FROM Director ORDER BY id DESC LIMIT 1");

        if (result.next()) {
            return result.getString(1);
        } else {
            return "D00-001";
        }
    }

    public static String getNextDirId() throws SQLException, ClassNotFoundException {
        String id =  getLastId();

        if(id.equals("D00-001")){
            return id;
        }else{
            String[] splitted = id.split("D00-");
            return String.format("D00-%03d",Integer.valueOf(splitted[1])+1);
        }

    }

}
