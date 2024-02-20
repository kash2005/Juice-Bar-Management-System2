package lk.ijse.layardproject.dao.custom;

import lk.ijse.layardproject.dao.CrudDAO;
import lk.ijse.layardproject.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {
    User getUser(String userName) throws SQLException;
}
