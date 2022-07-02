package dao.Custom;

import Model.DirectorDTO;
import dao.CrudDAO;
import entity.Director;

public interface DirectorDAO extends CrudDAO<Director,String> {
    long getCount() throws Exception;
}
