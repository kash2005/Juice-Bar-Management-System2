package lk.ijse.layardproject.dao;

import lk.ijse.layardproject.entity.Customer;

import java.sql.SQLException;

public interface CrudDAO<T> extends SuperDAO {
    boolean save(T entity) throws SQLException;
}
