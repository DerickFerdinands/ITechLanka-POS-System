package dao;

import Model.Director;
import Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID> {

    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(ID id) throws SQLException, ClassNotFoundException;

    boolean save(T d) throws SQLException, ClassNotFoundException;

    boolean update(T d) throws SQLException, ClassNotFoundException;

    ArrayList<T> getMatchingResults(ID search) throws SQLException, ClassNotFoundException;

    ID getLastId() throws SQLException, ClassNotFoundException;

    ID getNextId() throws SQLException, ClassNotFoundException;

}
