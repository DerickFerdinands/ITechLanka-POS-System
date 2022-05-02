package dao;

import Model.Director;
import Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DirectorCRUDController {

    public static ArrayList<Director> getAllDirectors() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Director");
        ArrayList<Director> DirList = new ArrayList<>();

        while(result.next()){
            DirList.add(new Director(result.getString(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getDouble(6)));
        }
        return DirList;
    }
}
