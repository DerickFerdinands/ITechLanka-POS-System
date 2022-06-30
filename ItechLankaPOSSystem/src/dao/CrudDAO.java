package dao;

import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID> extends SuperDAO{

    ArrayList<T> getAll() throws Exception;

    boolean delete(ID id) throws Exception;

    boolean save(T entity) throws Exception;

    boolean update(T entity) throws Exception;

    ArrayList<T> getMatchingResults(ID search) throws Exception;

    ID getLastId() throws Exception;

    ID getNextId() throws Exception;

}
